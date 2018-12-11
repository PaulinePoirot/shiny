package android.shiny.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.shiny.model.PokemonSpecies;
import android.support.annotation.NonNull;
import android.util.Log;


import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;


@Database(entities = Pokemon.class, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {

    private static PokemonDatabase instance;

    public abstract PokemonDAO pokemonDAO();

    public static synchronized PokemonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PokemonDatabase.class, "pokemon_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PokemonDAO pokemonDAO;

        private PopulateDbAsyncTask(PokemonDatabase db) {
            pokemonDAO = db.pokemonDAO();
        }


        /**
         * Cette méthode ne s'exécute qu'à la première instanciation de la BD
         * (la premiere fois que l'application se lance, en gros)
         */
        @Override
        protected Void doInBackground(Void... voids) {

            PokemonService pokemonService = new Retrofit.Builder()
                    .baseUrl(PokemonService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PokemonService.class);
            try {
                Response<PokemonSpecies> response = pokemonService.listPokemons().execute();
                List<PokemonSpecies.PokemonNames> list = response.body().getResults();

                for (PokemonSpecies.PokemonNames pName : list) {
                    pokemonDAO.insert(new Pokemon(list.indexOf(pName), "tamer", String.valueOf(list.indexOf(pName)+1)+" "+pName.getName()));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }
    }
}

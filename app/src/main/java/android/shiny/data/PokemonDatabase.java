package android.shiny.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.shiny.pojo.getFrName;
import android.shiny.pojo.getPokemonList;
import android.shiny.pojo.getPokemonStats;
import android.shiny.pojo.getPokemonTypes;
import android.support.annotation.NonNull;
import android.util.Log;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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

            Log.e(TAG, "doInBackground: LANCEMENT");

            final PokemonService pokemonService = new Retrofit.Builder()
                    .baseUrl(PokemonService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PokemonService.class);

            pokemonService.listPokemons().enqueue(new retrofit2.Callback<getPokemonList>() {
                @Override
                public void onResponse(Call<getPokemonList> call, final Response<getPokemonList> response) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            List<getPokemonList.PokemonNames> list = response.body().getResults();
                            for (getPokemonList.PokemonNames pName : list) {
                                int id = list.indexOf(pName) + 1;
                                Pokemon mPokemon = new Pokemon(id, pName.getName(), pName.getName());
                                Log.e(TAG, "run: insertion pokemon"+id);
                                pokemonDAO.insert(mPokemon);
                            }
                        }
                    }).start();
                }

                @Override
                public void onFailure(Call<getPokemonList> call, Throwable t) {
                    return;
                }
            });

            /*try {
                Response<getPokemonList> response = pokemonService.listPokemons().execute();
                List<getPokemonList.PokemonNames> list = response.body().getResults();

                for (getPokemonList.PokemonNames pName : list) {
                    int id = list.indexOf(pName) + 1;
                    Response<getFrName> response2 = pokemonService.pokemonFrName("" + id).execute();
                    List<getFrName.PokemonNamesWithID> nameList = response2.body().getNames();
                    String frName = nameList.get(6).getName();
                    Pokemon mPokemon = new Pokemon(id, pName.getName(), frName);
                    pokemonDAO.insert(mPokemon);

                    Response<getPokemonTypes> response3 = pokemonService.getPokemonTypes("" + id).execute();
                    String type1 = response3.body().getTypes().get(0).getTypeObject().getName();
                    String type2 = null;
                    if (response3.body().getTypes().size() > 1) {
                        type2 = response3.body().getTypes().get(1).getTypeObject().getName();
                    }
                    Log.e(TAG, "pokemon n°" + id + ": " + frName + " --> " + type1 + ", " + type2);

                    Response<getPokemonStats> response4 = pokemonService.getPokemonStats("" + id).execute();
                    int speed = response4.body().getStats().get(0).getBaseStat();
                    int defense_sp = response4.body().getStats().get(1).getBaseStat();
                    int attack_sp = response4.body().getStats().get(2).getBaseStat();
                    int defense = response4.body().getStats().get(3).getBaseStat();
                    int attack = response4.body().getStats().get(4).getBaseStat();
                    int health = response4.body().getStats().get(5).getBaseStat();

                    mPokemon.setType1(type1);
                    mPokemon.setType2(type2);

                    mPokemon.setSpeed(speed);
                    mPokemon.setAttack(attack);
                    mPokemon.setDefense(defense);
                    mPokemon.setAttack_sp(attack_sp);
                    mPokemon.setDefense_sp(defense_sp);
                    mPokemon.setHealth(health);

                    pokemonDAO.update(mPokemon);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }*/
            return null;
        }
    }
}

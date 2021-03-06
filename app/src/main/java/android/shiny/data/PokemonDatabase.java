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


import java.util.List;

import retrofit2.Call;
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

        final PokemonService pokemonService = new Retrofit.Builder()
                .baseUrl(PokemonService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);

        private PopulateDbAsyncTask(PokemonDatabase db) {
            pokemonDAO = db.pokemonDAO();
        }


        /**
         * Cette méthode ne s'exécute qu'à la première instanciation de la BD
         * (la premiere fois que l'application se lance, en gros)
         */
        @Override
        protected Void doInBackground(Void... voids) {
            Log.e(TAG, "doInBackground: INIT");
            pokemonService.listPokemons().enqueue(new retrofit2.Callback<getPokemonList>() {
                @Override
                public void onResponse(Call<getPokemonList> call, final Response<getPokemonList> response) {
                    Log.e(TAG, "doInBackground: THREAD 1");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            List<getPokemonList.PokemonNames> list = response.body().getResults();
                            for (final getPokemonList.PokemonNames pName : list) {
                                final int id = list.indexOf(pName) + 1;
                                final Pokemon mPoke = new Pokemon(id, pName.getName(), "###");

                                pokemonService.pokemonFrName("" + id).enqueue(new retrofit2.Callback<getFrName>() {
                                    @Override
                                    public void onResponse(Call<getFrName> call, final Response<getFrName> response) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                List<getFrName.PokemonNamesWithID> nameList = response.body().getNames();
                                                String frName = nameList.get(6).getName();
                                                Log.e(TAG, "run: insertion pokemon " + id);
                                                mPoke.setName_fr(frName);
                                                pokemonDAO.update(mPoke);
                                            }
                                        }).start();;
                                    }

                                    @Override
                                    public void onFailure(Call<getFrName> call, Throwable t) {

                                    }
                                });

                                pokemonService.getPokemonTypes("" + id).enqueue(new retrofit2.Callback<getPokemonTypes>() {
                                    @Override
                                    public void onResponse(Call<getPokemonTypes> call, final Response<getPokemonTypes> response) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String type1 = response.body().getTypes().get(0).getTypeObject().getName();
                                                String type2 = null;
                                                if (response.body().getTypes().size() > 1) {
                                                    type2 = response.body().getTypes().get(1).getTypeObject().getName();
                                                }
                                                mPoke.setType1(type1);
                                                mPoke.setType2(type2);
                                                pokemonDAO.update(mPoke);
                                            }
                                        }).start();
                                    }

                                    @Override
                                    public void onFailure(Call<getPokemonTypes> call, Throwable t) {

                                    }
                                });

                                pokemonService.getPokemonStats(""+id).enqueue(new retrofit2.Callback<getPokemonStats>() {
                                    @Override
                                    public void onResponse(Call<getPokemonStats> call, final Response<getPokemonStats> response) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                int speed = response.body().getStats().get(0).getBaseStat();
                                                int defense_sp = response.body().getStats().get(1).getBaseStat();
                                                int attack_sp = response.body().getStats().get(2).getBaseStat();
                                                int defense = response.body().getStats().get(3).getBaseStat();
                                                int attack = response.body().getStats().get(4).getBaseStat();
                                                int health = response.body().getStats().get(5).getBaseStat();

                                                mPoke.setSpeed(speed);
                                                mPoke.setAttack(attack);
                                                mPoke.setDefense(defense);
                                                mPoke.setAttack_sp(attack_sp);
                                                mPoke.setDefense_sp(defense_sp);
                                                mPoke.setHealth(health);
                                                pokemonDAO.update(mPoke);
                                            }
                                        }).start();
                                    }

                                    @Override
                                    public void onFailure(Call<getPokemonStats> call, Throwable t) {

                                    }
                                });

                                pokemonDAO.insert(mPoke);

                            }
                        }
                    }).start();
                }

                @Override
                public void onFailure(Call<getPokemonList> call, Throwable t) {
                    return;
                }
            });
            return null;
        }

    }
}

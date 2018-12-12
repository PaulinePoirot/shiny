package android.shiny.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PokemonRepository {
    private PokemonDAO pokemonDAO;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonRepository(Application application) {
        PokemonDatabase database = PokemonDatabase.getInstance(application);
        pokemonDAO = database.pokemonDAO();
        allPokemons = pokemonDAO.getAllPokemons();
    }

    public void insert(Pokemon pokemon) {
        new InsertPokemonAsyncTask(pokemonDAO).execute(pokemon);
    }

    public void update(Pokemon pokemon) {
        new UpdatePokemonAsyncTask(pokemonDAO).execute(pokemon);
    }

    public void delete(Pokemon pokemon) {
        new DeletePokemonAsyncTask(pokemonDAO).execute(pokemon);
    }

    public void deleteAllPokemons() {
        new DeleteAllPokemonAsyncTask(pokemonDAO).execute();
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return allPokemons;
    }

    private static class InsertPokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private InsertPokemonAsyncTask(PokemonDAO pokemonDAO){
            this.pokemonDAO = pokemonDAO;
        }
        @Override
        protected Void doInBackground(Pokemon... pokemonEntities) {
            pokemonDAO.insert(pokemonEntities[0]);
            return null;
        }
    }

    private static class UpdatePokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private UpdatePokemonAsyncTask(PokemonDAO pokemonDAO){
            this.pokemonDAO = pokemonDAO;
        }
        @Override
        protected Void doInBackground(Pokemon... pokemonEntities) {
            pokemonDAO.update(pokemonEntities[0]);
            return null;
        }
    }

    private static class DeletePokemonAsyncTask extends AsyncTask<Pokemon, Void, Void> {
        private PokemonDAO pokemonDAO;

        private DeletePokemonAsyncTask(PokemonDAO pokemonDAO){
            this.pokemonDAO = pokemonDAO;
        }
        @Override
        protected Void doInBackground(Pokemon... pokemonEntities) {
            pokemonDAO.delete(pokemonEntities[0]);
            return null;
        }
    }

    private static class DeleteAllPokemonAsyncTask extends AsyncTask<Void, Void, Void> {
        private PokemonDAO pokemonDAO;

        private DeleteAllPokemonAsyncTask(PokemonDAO pokemonDAO){
            this.pokemonDAO = pokemonDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            pokemonDAO.deleteAllPokemons();
            return null;
        }
    }
}
package android.shiny.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {
    private PokemonRepository repository;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        repository = new PokemonRepository(application);
        allPokemons = repository.getAllPokemons();
    }

    public void insert(Pokemon pokemon) {
        repository.insert(pokemon);
    }

    public void update(Pokemon pokemon) {
        repository.update(pokemon);
    }

    public void delete(Pokemon pokemon) {
        repository.delete(pokemon);
    }

    public void deleteAllPokemons() {
        repository.deleteAllPokemons();
    }

    public Pokemon getPokemon(int id) {
        return repository.getPokemon(id);
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return repository.getAllPokemons();
    }

}

package android.shiny.data;

import android.shiny.api.PokemonService;
import android.shiny.api.PokemonSpecies;
import android.shiny.view.PokemonAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Data implements DataInterface {

    private RoomInstance room = new RoomInstance();

    public Data() {

    }

    public Hashtable getPokemonById(int pokemonId) {
        return room.getPokemonById(pokemonId);
    }

    public ArrayList getPokemonsByFamilyId(int pokemonFamilyId) {
        return room.getPokemonsByFamilyId(pokemonFamilyId);
    }

    public ArrayList getPokemonForms(int pokemonId) {
        return room.getPokemonForms(pokemonId);
    }

    public ArrayList getPokemonVarieties(int pokemonId) {
        return room.getPokemonVarieties(pokemonId);
    }

    public ArrayList getPokemonSpecies() {
        return room.getPokemonsSpecies();
    }

    public void updatePokemonsList() {
        // TODO : Implémentation de l'API.

        final PokemonAdapter adapter = new PokemonAdapter();

        PokemonService pokemonService = new Retrofit.Builder()
                .baseUrl(PokemonService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);

        pokemonService.listPokemons().enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                room.setPokemonSpecies(response);

            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                Log.e("Retrofit", "onFailure: " + t.getMessage());
                Log.e("Retrofit", "onFailure: " + t.getCause());
            }
        });

    }
}

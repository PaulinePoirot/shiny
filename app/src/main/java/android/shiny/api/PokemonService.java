package android.shiny.api;

import android.shiny.api.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonService {
     String ENDPOINT = "https://pokeapi.co/api/v2/";

    @GET("pokemon-species")
    Call<PokemonSpecies> listPokemons();



    /*@GET("pokemon-species/{id}")
    Call<List<PokemonSpecies>> pokemonData(@Path("id") String id);

    @GET("pokemon/{id}")
    Call<List<PokemonSpecies>> pokemonSpecies(@Path("id") String id);
    */
}

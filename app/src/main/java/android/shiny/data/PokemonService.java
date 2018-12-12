package android.shiny.data;

import android.shiny.pojo.getPokemonList;
import android.shiny.pojo.getFrName;
import android.shiny.pojo.getPokemonStats;
import android.shiny.pojo.getPokemonTypes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {
     String ENDPOINT = "https://pokeapi.co/api/v2/";

    @GET("pokemon-species")
    Call<getPokemonList> listPokemons();

    @GET("pokemon-species/{id}")
    Call<getFrName> pokemonFrName(@Path("id") String id);

    @GET("pokemon/{id}")
    Call<getPokemonStats> getPokemonStats(@Path("id") String id);

    @GET("pokemon/{id}")
    Call<getPokemonTypes> getPokemonTypes(@Path("id") String id);

}

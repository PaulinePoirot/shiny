package android.shiny.data;

import android.os.Bundle;
import android.shiny.api.PokemonSpecies;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Response;

public interface PersistantData {

    Hashtable getPokemonById(int pokemonId);

    ArrayList getPokemonsByFamilyId(int pokemonFamilyId);

    ArrayList getPokemonForms(int pokemonId);

    ArrayList getPokemonVarieties(int pokemonId);

    ArrayList getPokemonsSpecies();

    void setPokemonSpecies(List<PokemonSpecies.PokemonNames> pokemonSpeciesList);
//    void setPokemonSpecies(Response<PokemonSpecies> pokemonSpeciesList);

}

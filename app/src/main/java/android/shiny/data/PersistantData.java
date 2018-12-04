package android.shiny.data;

import android.shiny.api.PokemonSpecies;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public interface PersistantData {

    Hashtable getPokemonById(int pokemonId);

    ArrayList getPokemonsByFamilyId(int pokemonFamilyId);

    ArrayList getPokemonForms(int pokemonId);

    ArrayList getPokemonVarieties(int pokemonId);

    ArrayList getPokemonsSpecies();

    void setPokemonSpecies(List<PokemonSpecies.PokemonNames> pokemonSpeciesList);

}

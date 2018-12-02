package data;

import java.util.ArrayList;
import java.util.Hashtable;

public interface persistantData {

    Hashtable getPokemonById(int pokemonId);

    ArrayList getPokemonsByFamilyId(int pokemonFamilyId);

    ArrayList getPokemonForms(int pokemonId);

    ArrayList getPokemonVarieties(int pokemonId);

    ArrayList getPokemonsPreview();

}

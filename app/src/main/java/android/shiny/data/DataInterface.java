package android.shiny.data;

import java.util.ArrayList;
import java.util.Hashtable;

public interface DataInterface {

    Hashtable getPokemonById(int pokemonId);

    ArrayList getPokemonsByFamilyId(int pokemonFamilyId);

    ArrayList getPokemonForms(int pokemonId);

    ArrayList getPokemonVarieties(int pokemonId);

    ArrayList getPokemonsPreview();

    void updatePokemonsList();

}

package android.shiny.data;

import java.util.ArrayList;
import java.util.Hashtable;

public class data implements DataInterface {

    private Room room = new Room();
    private PokeApi pokeApi = new PokeApi();

    public data() {
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

    public ArrayList getPokemonsPreview() {
        return room.getPokemonsPreview();
    }

    public void updatePokemonsList() {
        // TODO : Implémentation de l'API.
        pokeApi.getClass();
    }
}

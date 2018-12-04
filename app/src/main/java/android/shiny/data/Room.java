package android.shiny.data;


import java.util.ArrayList;
import java.util.Hashtable;

class Room implements PersistantData {

    public Room() {

    }

    @Override
    public Hashtable getPokemonById(int pokemonId) {
        return null;
    }

    @Override
    public ArrayList getPokemonsByFamilyId(int pokemonFamilyId) {
        return null;
    }

    @Override
    public ArrayList getPokemonForms(int pokemonId) {
        return null;
    }

    @Override
    public ArrayList getPokemonVarieties(int pokemonId) {
        return null;
    }

    @Override
    public ArrayList getPokemonsPreview() {
        return null;
    }
}

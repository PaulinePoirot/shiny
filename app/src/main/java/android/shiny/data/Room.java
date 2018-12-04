package android.shiny.data;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.shiny.api.PokemonSpecies;


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
    public ArrayList getPokemonsSpecies() {
        return null;
    }

    @Override
    public void setPokemonSpecies(List<PokemonSpecies.PokemonNames> pokemonSpeciesList) {
        //TODO: Insertion de la liste dans la base de données
    }


}
*/
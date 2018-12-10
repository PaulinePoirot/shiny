package android.shiny.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Hashtable;

import android.arch.persistence.room.Room;
import android.shiny.api.PokemonSpecies;

import retrofit2.Response;




class RoomInstance extends AppCompatActivity implements PersistantData {

    private Room species;
    private static final String TAG = RoomInstance.class.getName();


    public RoomInstance() {
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
    public void setPokemonSpecies(Response<PokemonSpecies> pokemonSpeciesList) {
        //TODO: Insertion de la liste dans la base de données
        RoomDatabaseInitializer.populateAsync(RoomPokemonDatabase.getRoomPokemonDatabase(this));

    }

}
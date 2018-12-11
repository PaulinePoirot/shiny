package android.shiny.data;

import android.annotation.SuppressLint;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.arch.persistence.room.Room;
import android.shiny.api.PokemonSpecies;

import retrofit2.Response;




@SuppressLint("Registered")
class RoomInstance extends AppCompatActivity implements PersistantData {

    private Room species;
    private static final String TAG = RoomInstance.class.getName();
    private Context context;

    public RoomInstance(){
        RoomDatabaseInitializer.populateAsync(RoomPokemonDatabase.getRoomPokemonDatabase(this));
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoomDatabaseInitializer.populateAsync(RoomPokemonDatabase.getRoomPokemonDatabase(this));
    }

    @Override
    protected void onDestroy() {
        RoomPokemonDatabase.destroyInstance();
        super.onDestroy();
    }
*/

/*
    private final RoomPokemonSpeciesDao roomPokemonSpeciesDao = new RoomPokemonSpeciesDao() {
        @Override
        public void setPokemonSpecies(ArrayList<RoomPokemonSpecies> roomPokemonSpeciesArrayLists) {
            roomPokemonSpeciesDao.setPokemonSpecies(roomPokemonSpeciesArrayLists);
        }

        @Override
        public List<RoomPokemonSpecies> getPokemonSpeciesList() {
            return null;
//            return roomPokemonSpeciesDao.getPokemonSpeciesList();
        }

    };
*/
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
    public ArrayList<PokemonSpecies.PokemonNames> getPokemonsSpecies() {
        return null;
//        return roomPokemonSpeciesDao.getPokemonSpeciesList();
    }

    @Override
    public void setPokemonSpecies(List<PokemonSpecies.PokemonNames> pokemonSpeciesList) {
//      public void setPokemonSpecies(Response<PokemonSpecies> pokemonSpeciesList) {
        RoomPokemonSpecies roomPokemonSpecies = new RoomPokemonSpecies();
        ArrayList<RoomPokemonSpecies> roomPokemonSpeciesArrayList = new ArrayList<RoomPokemonSpecies>();

        for (PokemonSpecies.PokemonNames pokemonSpecie : pokemonSpeciesList) {
            roomPokemonSpecies.setName(pokemonSpecie.getName());
            roomPokemonSpecies.setUrl(pokemonSpecie.getUrl());

            roomPokemonSpeciesArrayList.add(roomPokemonSpecies);
        }
//        roomPokemonSpeciesDao.setPokemonSpecies(roomPokemonSpeciesArrayList);
        RoomPokemonDatabase roomPokemonDatabase = RoomPokemonDatabase.getRoomPokemonDatabase(this);
        roomPokemonDatabase.roomPokemonSpeciesDao().setPokemonSpecies(roomPokemonSpeciesArrayList);
    }

}
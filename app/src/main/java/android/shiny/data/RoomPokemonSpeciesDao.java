package android.shiny.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.ClipData;
import android.shiny.api.PokemonSpecies;
import android.shiny.data.RoomPokemonSpecies;

import java.util.List;

@Dao
public interface RoomPokemonSpeciesDao {

    @Insert
    void setPokemonSpecies(RoomPokemonSpecies roomPokemonSpecies);

    /*
    @Query("SELECT * FROM pokemonSpecies")
    PokemonSpecies getPokemonSpeciesList();
    */
}

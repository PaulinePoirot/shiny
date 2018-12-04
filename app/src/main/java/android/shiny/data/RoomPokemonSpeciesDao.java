package android.shiny.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.shiny.api.PokemonSpecies;

import java.util.List;

@Dao
public interface RoomPokemonSpeciesDao extends PersistantData {

    @Insert
    void setPokemonSpecies(List<PokemonSpecies.PokemonNames> pokemonSpeciesList);
}

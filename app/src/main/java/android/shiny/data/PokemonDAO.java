package android.shiny.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PokemonDAO {

    @Insert
    void insert(Pokemon pokemon);

    @Update
    void update(Pokemon pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    void deleteAllPokemons();

    @Query("SELECT * from pokemon_table ORDER BY id ASC")
    LiveData<List<Pokemon>> getAllPokemons();

    @Query("SELECT * from pokemon_table ORDER BY id ASC")
    List<Pokemon> getPokemons();
}
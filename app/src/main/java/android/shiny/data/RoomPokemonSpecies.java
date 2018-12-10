package android.shiny.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.PrimaryKey;
import android.shiny.api.PokemonSpecies;
import android.support.annotation.NonNull;

import java.util.List;


@Entity(tableName = "pokemonSpecies")
public class RoomPokemonSpecies implements RoomPokemonSpeciesDao {

    public RoomPokemonSpecies() {
    }

    @PrimaryKey
    @ColumnInfo(name = "pokemonId")
    private int pokemonId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "url")
    private String url;

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PokemonSpecies getPokemonSpeciesList() {
        return null;
    }

    @Override
    public void setPokemonSpecies(RoomPokemonSpecies roomPokemonSpecies) {
    }
}



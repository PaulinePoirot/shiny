package android.shiny.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.shiny.api.PokemonSpecies;

@Entity(tableName = "pokemonSpecies")
public class RoomPokemonSpecies {
//    public class RoomPokemonSpecies implements RoomPokemonSpeciesDao {

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

 /*   @Override
    public PokemonSpecies getPokemonSpeciesList() {
        return null;
    }

    @Override
    public void setPokemonSpecies(RoomPokemonSpecies roomPokemonSpecies) {
    }   */
}



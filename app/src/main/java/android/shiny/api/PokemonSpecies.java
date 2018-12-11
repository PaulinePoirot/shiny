package android.shiny.api;

import android.arch.persistence.room.Entity;

import java.util.List;

public class PokemonSpecies {

//    private int count;

    private List<PokemonNames> results;

    /*
    public int getCount() {
        return count;
    }
    */

    public List<PokemonNames> getResults() {
        return results;
    }

    public class PokemonNames {
        String name;
        String url;


        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}

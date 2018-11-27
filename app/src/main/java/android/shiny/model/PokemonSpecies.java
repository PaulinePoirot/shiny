package android.shiny.model;

import java.util.List;

public class PokemonSpecies {
    int count;

    List<PokemonNames> results;

    public int getCount() {
        return count;
    }

    public List<PokemonNames> getResults() {
        return results;
    }

    public class PokemonNames {
        String name;
        String url;

        public String getName() {
            return name;
        }
    }
}

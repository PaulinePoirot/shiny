package android.shiny.pojo;

import java.util.List;

public class getPokemonList {
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

        public String getName() {
            return name;
        }
    }

}

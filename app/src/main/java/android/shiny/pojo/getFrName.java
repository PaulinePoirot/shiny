package android.shiny.pojo;

import java.util.List;

public class getFrName {
    List<PokemonNamesWithID> names;

    public List<PokemonNamesWithID> getNames() {
        return names;
    }

    public class PokemonNamesWithID {
        LanguageObject language;
        String name;

        public String getName() {
            return name;
        }

        public LanguageObject getLanguage() {
            return language;
        }

        class LanguageObject {
            String name;
        }
    }

}

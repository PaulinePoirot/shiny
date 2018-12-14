package android.shiny.pojo;

import java.util.List;

public class getPokemonTypes {
    List<Type> types;

    public List<Type> getTypes() {
        return types;
    }

    public class Type {
        TypeObject type;

        public TypeObject getTypeObject() {
            return type;
        }

        public class TypeObject {
            String name;

            public String getName() {
                return name;
            }
        }
    }
}

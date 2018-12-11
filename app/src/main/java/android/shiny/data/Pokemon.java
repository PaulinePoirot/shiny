package android.shiny.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pokemon_table")
public class Pokemon {

    @PrimaryKey
    private int id;

    private String name_en;

    private String name_fr;

    public Pokemon(int id, String name_en, String name_fr) {
        this.id = id;
        this.name_en = name_en;
        this.name_fr = name_fr;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName_en() {
        return name_en;
    }

    public String getName_fr() {
        return name_fr;
    }
}

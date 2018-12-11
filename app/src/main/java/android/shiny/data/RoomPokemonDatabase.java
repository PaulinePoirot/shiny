package android.shiny.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;

@Database(entities = {RoomPokemonSpecies.class}, version = 1)
public abstract class RoomPokemonDatabase extends RoomDatabase {

    private static RoomPokemonDatabase INSTANCE;
    public abstract RoomPokemonSpeciesDao roomPokemonSpeciesDao();


    public static RoomPokemonDatabase getRoomPokemonDatabase(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, RoomPokemonDatabase.class, "pokemonSpecies")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
/*
    public void setPokemonSpecies(ArrayList<RoomPokemonSpecies> roomPokemonSpeciesArrayLists) {
        INSTANCE.roomPokemonSpeciesDao().setPokemonSpecies(roomPokemonSpeciesArrayLists);
    }
*/
}

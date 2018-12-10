package android.shiny.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RoomPokemonSpecies.class}, version = 1)
public abstract class RoomPokemonDatabase extends RoomDatabase {

    private static RoomPokemonDatabase INSTANCE;
    public abstract RoomPokemonSpeciesDao roomPokemonSpeciesDao();


    static RoomPokemonDatabase getRoomPokemonDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomPokemonDatabase.class, "pokemonSpecies")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}

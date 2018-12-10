package android.shiny.data;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import android.shiny.data.RoomPokemonDatabase;
import android.shiny.data.RoomPokemonSpecies;

import java.util.List;

public class RoomDatabaseInitializer {

    private static final String TAG = RoomDatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final RoomPokemonDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final RoomPokemonDatabase db) {
        populateWithTestData(db);
    }

 /*   private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }   */

    private static void populateWithTestData(RoomPokemonDatabase db) {

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RoomPokemonDatabase mDb;

        PopulateDbAsync(RoomPokemonDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}

package com.ahsan.a52_roompersistenceapi28.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase{

    private static final String TAG = WordRoomDatabase.class.getSimpleName();
    public abstract WordDao wordDao();

    //We only need one instance of this class, so we make it singleton
    private static WordRoomDatabase INSTANCE;

    //Singleton pattern method
    public static WordRoomDatabase getDatabase (final Context context){
        if (INSTANCE == null){
            Log.d(TAG, "getDatabase: INSTANCE == null, Build new Database INSTANCE");
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null){
                    //create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };



    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time. Not needed if you only populate on creation.
            mDao.deleteAll();

            //These are the initial words for our app, because above deleteAll method deletes all the data at each exit.
            Word word = new Word(1,"Ahsan", "Taqveem", "Android Developer", "Software Development");
            mDao.insert(word);
            return null;
        }
    }
}

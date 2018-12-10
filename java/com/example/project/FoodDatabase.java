package com.example.project;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Food.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase
{
    private static FoodDatabase instance;

    public abstract FoodDao foodDao();

    //synchronised avoid multiple threads accessing this
    public static synchronized FoodDatabase getInstance(Context context)
    {
        if (instance==null)
        {
            //create the db
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    FoodDatabase.class, "food_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        //else just use the existing one.
        return instance;
    }//end sync

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedDbAsyncTask(instance).execute();
        }
    };

    private static class PopulatedDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private FoodDao mDao;

        public PopulatedDbAsyncTask(FoodDatabase db) {
            mDao =db.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.insert(new Food("pork","pork","chopped the pork"));
            return null;
        }
    }

}

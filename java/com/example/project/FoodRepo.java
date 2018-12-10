package com.example.project;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FoodRepo {
    private FoodDao foodDao;
    private LiveData<List<Food>> allFood;

    public FoodRepo(Application application) {
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
        allFood = foodDao.getAllFoods();
    }

    public void insert(Food food) {
        new InsertFoodAsyncTask(foodDao).execute(food);
    }


    public LiveData<List<Food>> getAllFood() {
        return allFood;
    }


    private static class InsertFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        //constructor
        private InsertFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {

            foodDao.insert(foods[0]);

            return null;
        }
    }
}


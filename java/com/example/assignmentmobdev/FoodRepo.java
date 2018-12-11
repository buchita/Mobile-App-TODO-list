/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com
 *   Purpose: AsyncTask for insert, delete, select and update
 *
 */



package com.example.assignmentmobdev;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FoodRepo
{

    private FoodDao foodDao;
    private LiveData<List<Food>> allFood;

    //constructor
    public FoodRepo(Application application) {
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
        allFood = foodDao.getAllFoods();
    }


    //display all
    public LiveData<List<Food>> getAllFood() {
        return allFood;
    }


    //insert
    public void insert(Food food) {
        new InsertFoodAsyncTask(foodDao).execute(food);
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

    //delete
    public void delete(Food food)
    {
        new DeleteFoodAsyncTask(foodDao).execute(food);
    }

    private static class DeleteFoodAsyncTask extends AsyncTask<Food, Void, Void>
    {
        private FoodDao foodDao;

        //constructor
        public DeleteFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDao.delete(foods[0]);

            return null;
        }
    }




    //UPDATE
    public void update(Food food) {
        new UpdateFoodAsyncTask(foodDao).execute(food);
    }


    private static class UpdateFoodAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao foodDao;

        //constructor
        private UpdateFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {

            foodDao.update(foods[0]);

            return null;
        }
    }
}


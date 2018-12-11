/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com
 *   Purpose: Create View Model and functions from FoodRepo(AsyncTask)
 *
 */



package com.example.assignmentmobdev;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;


public class FoodViewModel extends AndroidViewModel
{

    private FoodRepo repo;
    private LiveData<List<Food>> allFoods;

    //constructor
    public FoodViewModel(@NonNull Application application) {
        super(application);

        repo = new FoodRepo(application);
        //get the food from db
        allFoods = repo.getAllFood();
    }

    //insert method
    public void insert(Food food)
    {
        //method from repo which is using AsyncTask
        repo.insert(food);
    }

    public void update(Food food)
    {
        //method from repo which is using AsyncTask
        repo.update(food);
    }

    public LiveData<List<Food>> getAllFoods() {
        return allFoods;
    }

    public void delete(Food food)
    {
        //method from repo which is using AsyncTask
        repo.delete(food);
    }
}



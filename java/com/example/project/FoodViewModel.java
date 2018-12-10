package com.example.project;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FoodViewModel extends AndroidViewModel
{

    private FoodRepo repo;
    private LiveData<List<Food>> allFoods;

    public FoodViewModel(@NonNull Application application) {
        super(application);

        repo = new FoodRepo(application);
        allFoods = repo.getAllFood();
    }

    public void insert(Food food)
    {
        repo.insert(food);
    }

    public LiveData<List<Food>> getAllFoods() {
        return allFoods;
    }
}

package com.example.project;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FoodDao
{
    @Insert
    void insert(Food food);

    @Query("DELETE FROM food_table")
    void deleteAll();

    @Query("SELECT * FROM food_table")
    LiveData<List<Food>> getAllFoods();
}

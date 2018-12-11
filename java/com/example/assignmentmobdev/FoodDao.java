/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *
 *   Purpose: Declare insert, delete, select and update DAO
 *
 */



package com.example.assignmentmobdev;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface FoodDao
{
    @Insert
    void insert(Food food);

    @Delete
    void delete(Food food);

    @Update
    void update(Food food);

    //select everything from food table
    @Query("SELECT * FROM food_table")
    LiveData<List<Food>> getAllFoods();
}


/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com
 *   Purpose: Table for our db with necessary attributes
 *
 */



package com.example.assignmentmobdev;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


//declare table name
@Entity(tableName = "food_table")
public class Food
{
    //primary key with auto generate
    @PrimaryKey(autoGenerate = true)
    private int id;


    //attributes for table
    private String name;

    private String ingredient;

    private String instruction;


    //constructor
    public Food(String name, String ingredient, String instruction) {
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getInstruction() {
        return instruction;
    }


    //setter and getter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

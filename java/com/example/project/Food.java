package com.example.project;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "food_table")
public class Food
{
    @PrimaryKey
    @NonNull
    private String name;

    private String ingredient;

    private String instruction;

    public Food(@NonNull String name, String ingredient, String instruction) {
        this.name = name;
        this.ingredient = ingredient;
        this.instruction = instruction;
    }

    //getters
    @NonNull
    public String getName() {
        return name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getInstruction() {
        return instruction;
    }



}

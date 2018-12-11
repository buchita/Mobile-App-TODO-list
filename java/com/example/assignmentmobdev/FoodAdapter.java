/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com, DIT Lecture notes
 *   Purpose: Adapters provide a binding from an app-specific data set to views that are displayed within a Recycler View
 *            Adapter for long press- fetch the data from db. Create the View Holder and data
 *
 */




package com.example.assignmentmobdev;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    //variables
    private List<Food> foods = new ArrayList<>();
    private ActionCallBack mActionCallBack;


    //FOR LONG CLICK UPDATING
    //interface for callbacks
    interface ActionCallBack
    {
        void onLongClickListener(Food food);
    }

    //for update
    void addActionCallback(ActionCallBack actionCallbacks) {
        mActionCallBack = actionCallbacks;
    }


    //declare the ViewHolder class for recyclerView for LONG PRESS - UPDATE
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {
        private TextView name, ingredient, instruction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //long press for edit
            itemView.setOnLongClickListener(this);

            //loads the data from db
            name = itemView.findViewById(R.id.name);
            ingredient = itemView.findViewById(R.id.ingredient);
            instruction = itemView.findViewById(R.id.instruction);


        }

        //for update
        @Override
        public boolean onLongClick(View v) {
            if (mActionCallBack != null)
            {
                //get the position
                mActionCallBack.onLongClickListener(foods.get(getAdapterPosition()));
            }

            return true;
        }//end onLongClick

    }//end ViewHolder implements LongPress

    //setter
    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    //get the position of the food item
    public Food getFoodAt(int position)
    {
        //pass the position back
        return foods.get(position);
    }


   //inflate the view with the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //inflate the itemview using the layout from food item
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item,parent,false );

        return new ViewHolder(itemview);
    }

    @Override
    public int getItemCount()
    {
        //the size of food
        return foods.size();

    }

    //loads the data in the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Food current = foods.get(position);

        holder.name.setText(current.getName());
        holder.ingredient.setText(current.getIngredient());
        holder.instruction.setText(current.getInstruction());

    }




}//end FoodAdapter


package com.example.project;

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
    private List<Food> foods = new ArrayList<>();



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name, ingredient, instruction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            ingredient = itemView.findViewById(R.id.ingredient);
            instruction = itemView.findViewById(R.id.instruction);
        }
    }


    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item,parent,false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Food current = foods.get(position);

        holder.name.setText(current.getName());
        holder.ingredient.setText(current.getIngredient());
        holder.instruction.setText(current.getInstruction());

    }

    @Override
    public int getItemCount() {
        return foods.size();

    }
}

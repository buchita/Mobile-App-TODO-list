package com.example.project;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FoodViewModel foodViewModel;
    public static final int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floating = findViewById(R.id.fab);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFood.class);
                startActivityForResult(intent,NEW_FOOD_ACTIVITY_REQUEST_CODE );
            }
        });


        //set adapter and recyclerview
        final FoodAdapter adapter = new FoodAdapter();
        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        recycler.setAdapter(adapter);

        //get new or existing viewmodel
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getAllFoods().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                //update the copy of the food in the adp
                adapter.setFoods(foods);
            }
        });


        //------------

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == NEW_FOOD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
            {
                String name = data.getStringExtra(AddFood.EXTRA_NAME);
                String ingredient = data.getStringExtra(AddFood.EXTRA_INGREDIENT);
                String instruction = data.getStringExtra(AddFood.EXTRA_INSTRUCTION);

                Food food = new Food(name, ingredient, instruction);
                foodViewModel.insert(food);

                Toast.makeText(this,"Food added" ,Toast.LENGTH_SHORT ).show();
            }
            else {
                Toast.makeText(this,"Food not added" ,Toast.LENGTH_SHORT ).show();

            }

    }
}

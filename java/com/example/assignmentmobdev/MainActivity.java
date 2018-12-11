/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com, androidhive.info,
  *         https://en.proft.me/2018/01/2/how-filter-recyclerview-searchview-toolbar/ ,
 *          https://developer.android.com/reference/android/widget/SearchView, https://abhiandroid.com/ui/searchview,
  *         https://coderwall.com/p/zpwrsg/add-search-function-to-list-view-in-android
 *
 *   Purpose: Main Activity to control the flow
 *      - contains floating button for add new food
 *      - Display the list of item using recyclerview, adapter and food view model
 *      - swipe left to delete
 *      - long press to edit
 *
 */





package com.example.assignmentmobdev;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{

    //variables
    private FoodViewModel foodViewModel;
    public static final int NEW_FOOD_ACTIVITY_REQUEST_CODE = 1; //using for request code for adding
    public static final int EDIT_FOOD_ACTIVITY_REQUEST_CODE = 2;    //using for request code for editing

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //using the layout of activity main
        setContentView(R.layout.activity_main);

        //set title for the toolbar on the main activity
        getSupportActionBar().setTitle("Receipt");

        //for add with floating button
        FloatingActionButton floating = findViewById(R.id.fab);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //brings to add page
                Intent intent = new Intent(MainActivity.this, AddEditFood.class);
                startActivityForResult(intent,NEW_FOOD_ACTIVITY_REQUEST_CODE );
            }
        });


        //set adapter and recycler view
        final FoodAdapter adapter = new FoodAdapter();
        RecyclerView recycler = findViewById(R.id.recycler_view);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        //get new or existing view model
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getAllFoods().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                //update the copy of the food in the adp
                adapter.setFoods(foods);
            }
        });


        //for swiping to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //get the food position to be able to pass to sql delete
                foodViewModel.delete(adapter.getFoodAt(viewHolder.getAdapterPosition()));

                Toast.makeText(MainActivity.this,"Item Deleted" ,Toast.LENGTH_SHORT ).show();
            }
            //add in the recycler view
        }).attachToRecyclerView(recycler);


        //UPDATE/EDIT if the user long press
        adapter.addActionCallback(new FoodAdapter.ActionCallBack() {
            @Override
            public void onLongClickListener(Food food) {
                Intent intent = new Intent(MainActivity.this, AddEditFood.class);

                //loading these through intent
                intent.putExtra(AddEditFood.EXTRA_ID,food.getId() );
                intent.putExtra(AddEditFood.EXTRA_NAME,food.getName() );
                intent.putExtra(AddEditFood.EXTRA_INGREDIENT,food.getIngredient() );
                intent.putExtra(AddEditFood.EXTRA_INSTRUCTION,food.getInstruction() );

                //passing these through intent
                startActivityForResult(intent, EDIT_FOOD_ACTIVITY_REQUEST_CODE);
            }
        });


    }

    //ADD/EDIT in the food to db
    @Override   //when the intent is done
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //for adding in the db
        if (requestCode == NEW_FOOD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            //getting the data from AddEditFood using EXTRA_s
            String name = data.getStringExtra(AddEditFood.EXTRA_NAME);
            String ingredient = data.getStringExtra(AddEditFood.EXTRA_INGREDIENT);
            String instruction = data.getStringExtra(AddEditFood.EXTRA_INSTRUCTION);

            //instantiate Food passing the values we got
            Food food = new Food(name, ingredient, instruction);

            //insert method from dao
            foodViewModel.insert(food);

            //display toast
            Toast.makeText(this,"Food added" ,Toast.LENGTH_SHORT ).show();
        }
        //for updating
        else if (requestCode == EDIT_FOOD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            //get id from the passed intent
            int id = data.getIntExtra(AddEditFood.EXTRA_ID,-1 );

            //if id is -1 there is something wrong- cant be updated
            if (id == -1)
            {
                Toast.makeText(this,"Food not updated" ,Toast.LENGTH_SHORT ).show();
                return;
            }
            //else proceed to update
            String name = data.getStringExtra(AddEditFood.EXTRA_NAME);
            String ingredient = data.getStringExtra(AddEditFood.EXTRA_INGREDIENT);
            String instruction = data.getStringExtra(AddEditFood.EXTRA_INSTRUCTION);

            //instantiate food and add the data in
            Food food = new Food(name, ingredient, instruction);

            //set id
            food.setId(id);

            //update the data
            foodViewModel.update(food);

            Toast.makeText(this,"Food updated" ,Toast.LENGTH_SHORT ).show();

        }
        //failed to add
        else {
            Toast.makeText(this,"Food not added" ,Toast.LENGTH_SHORT ).show();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //get icon search at the top
        getMenuInflater().inflate(R.menu.seach_menu,menu );

        //create menu and search view
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();

        //add listener to the search
        searchView.setOnQueryTextListener(this);

        return true;

    }

    //search view methods needed
     @Override
    public boolean onQueryTextSubmit(String query)
     {
         //display toast for the typed word
         Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();

         return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        //display toast containing letters thats been typed
        Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();

        return false;
    }


}


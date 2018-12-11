/*
*   Author: Buchita Gitchamnan
*   Student Number: C16348651
*   Project: Simple Food Receipt
*   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com
*   Purpose: For adding and editing user inputs from the xml file
*
 */




package com.example.assignmentmobdev;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditFood extends AppCompatActivity
{
    //declare necessary extras
    public static final String EXTRA_ID = "com.example.project.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.project.EXTRA_NAME";
    public static final String EXTRA_INGREDIENT = "com.example.project.EXTRA_INGREDIENT";
    public static final String EXTRA_INSTRUCTION = "com.example.project.EXTRA_INSTRUCTION";


    private EditText edit_name, edit_ingr, edit_instr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //using the layout new item
        //form for prompt user input for food
        setContentView(R.layout.new_item);

        //fetching the data from the inputs
        edit_name = findViewById(R.id.ed_name);
        edit_ingr = findViewById(R.id.ed_ingr);
        edit_instr = findViewById(R.id.ed_instr);

        //add the close icon to actionbar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        //declare intent for the intent from main activity
        Intent intent = getIntent();
        //if the user chooses to long press and the extra_id has passed in with the intent
        if (intent.hasExtra(EXTRA_ID))
        {
            //EDIT
            setTitle("Edit Receipt");
            //set the data in
            edit_name.setText((intent.getStringExtra(EXTRA_NAME)));
            edit_ingr.setText(intent.getStringExtra(EXTRA_INGREDIENT) );
            edit_instr.setText(intent.getStringExtra(EXTRA_INSTRUCTION) );


        }
        //just add
        else {
            setTitle("Add Receipt");
        }

    }//end on create

    private void saveFood()
    {
        //loads data in these to be used in passing back to through the intent
        String name = edit_name.getText().toString();
        String ingredient = edit_ingr.getText().toString();
        String instruction = edit_instr.getText().toString();


        //check if is empty ie blank space or space
        if (name.trim().isEmpty() || ingredient.trim().isEmpty() || instruction.trim().isEmpty())
        {
            Toast.makeText(this,"Please insert the empty space" ,Toast.LENGTH_SHORT ).show();
            return;
        }

        //declare intent and loads data to intent
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME,name );
        intent.putExtra(EXTRA_INGREDIENT,ingredient );
        intent.putExtra(EXTRA_INSTRUCTION,instruction );


        //assign id for checking if its being edit with default value of -1
        //we will never get the id = -1
        int id = getIntent().getIntExtra(EXTRA_ID,-1 );

        //check if the id is not -1 = everything is ok
        if (id != -1)
        {
            //assign the id to id
            intent.putExtra(EXTRA_ID,id );
        }
        //pass the result back with intent
        setResult(RESULT_OK, intent);
        finish();

    }

    //loads the save icon to the bar at the top
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;//display the menu
    }

    //if the icon is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //checking which icon is clicked
        switch (item.getItemId())
        {
            //the save icon
            case R.id.save_icon:
                //call saveFood()
                saveFood();
                return true;
            //default option
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

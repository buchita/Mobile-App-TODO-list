package com.example.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddFood extends AppCompatActivity
{

    public static final String EXTRA_NAME = "com.example.project.EXTRA_NAME";
    public static final String EXTRA_INGREDIENT = "com.example.project.EXTRA_INGREDIENT";
    public static final String EXTRA_INSTRUCTION = "com.example.project.EXTRA_INSTRUCTION";


    public static final String name = "food-name";
    public static final String ingredient = "ingredients";
    public static final String instruction = "instructions";

    private EditText edit_name, edit_ingr, edit_instr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.new_item);

        edit_name = findViewById(R.id.ed_name);
        edit_ingr = findViewById(R.id.ed_ingr);
        edit_instr = findViewById(R.id.ed_instr);

        //add the close icon to actionbar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Receipt");

    }//end oncreate

    private void saveFood()
    {
        String name = edit_name.getText().toString();
        String ingredient = edit_ingr.getText().toString();
        String instruction = edit_instr.getText().toString();


        if (name.trim().isEmpty() || ingredient.trim().isEmpty() || instruction.trim().isEmpty())
        {
            Toast.makeText(this,"Please insert the empty space" ,Toast.LENGTH_SHORT ).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME,name );
        intent.putExtra(EXTRA_INGREDIENT,ingredient );
        intent.putExtra(EXTRA_INSTRUCTION,instruction );

        setResult(RESULT_OK, intent);

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;//display the menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_icon:
                saveFood();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}//end add food

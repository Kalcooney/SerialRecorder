package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    //declare the views we are going to use
    EditText itemNameInput;
    EditText itemDescriptionInput;
    EditText serialNumberInput;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //reference the views we are going to use
        itemNameInput = (EditText) findViewById(R.id.itemNameInput);
        itemDescriptionInput = (EditText) findViewById(R.id.itemDescriptionInput);
        serialNumberInput = (EditText) findViewById(R.id.serialNumberInput);
        dbHandler = new MyDBHandler(this, null, null, 1);
    }

    //onClick method for the addButton
    public void addButtonClicked(View view){
        Items item = new Items(itemDescriptionInput.getText().toString(),
                itemNameInput.getText().toString(), serialNumberInput.getText().toString());
        dbHandler.addItem(item);
        Intent mainMenu = new Intent(this, MainMenu.class);
        startActivity(mainMenu);
    }
}

package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

    //add actions to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_item_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //if action is selected perform an action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.action_tick):
                Items userItem = new Items(itemDescriptionInput.getText().toString(),
                        itemNameInput.getText().toString(), serialNumberInput.getText().toString());
                dbHandler.addItem(userItem);
                Intent mainMenu = new Intent(this, MainMenu.class);
                startActivity(mainMenu);
                Toast.makeText(this, "Successfully added", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

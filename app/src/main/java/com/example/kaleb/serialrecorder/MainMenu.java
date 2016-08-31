package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    //onClick method for the New Item Button. When clicked goes to the New Item Activity
    public void newItemClicked(View view){
        //Intent to switch screen to newItemActivity.
        Intent newItemActivity = new Intent(this, NewItemActivity.class);
        startActivity(newItemActivity);

    }

    //onClick method for the View Items Button. When clicked goes to the View Items Activity
    public void viewItemsClicked(View view){
        Intent viewItemsActivity = new Intent(this, ViewItemsActivity.class);
        startActivity(viewItemsActivity);
    }

    //onClick method for the Help Button. When clicked goes to the Help Activity
    public void helpClicked(View view){
        //Intent to switch screen to helpActivity
        Intent helpActivity = new Intent(this, HelpActivity.class);
        startActivity(helpActivity);
    }

    //onClick method for the About Activity. When clicked goes to the About Activity
    public void aboutClicked(View view){
        //Intent to switch screen to aboutActivity
        Intent aboutActivity = new Intent(this, AboutActivity.class);
        startActivity(aboutActivity);
    }
}

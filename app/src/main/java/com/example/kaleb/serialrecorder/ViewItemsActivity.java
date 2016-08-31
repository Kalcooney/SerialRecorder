package com.example.kaleb.serialrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewItemsActivity extends AppCompatActivity {

    //declare the views we are going to use
    TextView item;
    MyDBHandler dbHandlerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        //reference the views we are going to use
        item = (TextView) findViewById(R.id.item);
        dbHandlerview = new MyDBHandler(this, null, null, 1);
        printDatabase();

    }

    //method to print out database
    public void printDatabase(){
        String dbString = dbHandlerview.databaseToString();
        item.setText(dbString);
    }
}

package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class SingleItemActivity extends AppCompatActivity {

    ImageView itemImage;
    MyDBHandler dbHandler;
    private String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getId = getIntent().getExtras().getString("ID");
        int id = Integer.parseInt(getId);

        //reference the text and image views
        TextView itemName = (TextView) findViewById(R.id.itemName);
        TextView itemDescription = (TextView) findViewById(R.id.itemDescription);
        TextView itemSerialNumber = (TextView) findViewById(R.id.itemSerialNumber);
        TextView itemDatePurchased = (TextView) findViewById(R.id.itemDatePurchased);
        itemImage = (ImageView) findViewById(R.id.itemImage);

        dbHandler = new MyDBHandler(this, null, null, 1);
        getImageUri(id);

        //set text for TextViews
        itemName.setText("Name: " + dbHandler.getItemName(id));
        itemDescription.setText("Description: " + dbHandler.getItemDescription(id));
        itemSerialNumber.setText("Serial Number: " + dbHandler.getSerialNumber(id));
        itemDatePurchased.setText("Date Purchased: " + dbHandler.getDatePurchased(id));

    }
    //set the image to the ImageView
    public void getImageUri(int id){
        String uriString = dbHandler.getImageUri(id);
        Uri imageUri = Uri.parse(uriString);
        itemImage.setImageURI(imageUri);
    }

    //add actions to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_item_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //if action is selected, perform an action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(android.R.id.home):
                finish();
                break;
            case(R.id.edit_item):
                Intent editItemActivity = new Intent(this, EditItemActivity.class);
                editItemActivity.putExtra("ID", getId);
                startActivity(editItemActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

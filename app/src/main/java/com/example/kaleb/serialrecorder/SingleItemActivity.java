package com.example.kaleb.serialrecorder;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class SingleItemActivity extends AppCompatActivity {

    ImageView itemImage;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        String getId = getIntent().getExtras().getString("ID");
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

}

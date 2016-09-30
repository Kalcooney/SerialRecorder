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
        TextView itemNameText = (TextView) findViewById(R.id.itemNameText);
        TextView itemDescriptionText = (TextView) findViewById(R.id.itemDescriptionText);
        TextView itemSerialNumberText = (TextView) findViewById(R.id.itemSerialNumberText);
        TextView itemDatePurchasedText = (TextView) findViewById(R.id.itemDatePurchasedText);
        itemImage = (ImageView) findViewById(R.id.itemImage);

        dbHandler = new MyDBHandler(this, null, null, 1);
        getImageUri(id);

        //set text for TextViews
        itemNameText.setText(dbHandler.getItemName(id));
        itemDescriptionText.setText(dbHandler.getItemDescription(id));
        itemSerialNumberText.setText(dbHandler.getSerialNumber(id));
        itemDatePurchasedText.setText(dbHandler.getDatePurchased(id));

    }
    //set the image to the ImageView
    public void getImageUri(int id){
        String uriString = dbHandler.getImageUri(id);
        Uri imageUri = Uri.parse(uriString);
        itemImage.setImageURI(imageUri);
    }

}

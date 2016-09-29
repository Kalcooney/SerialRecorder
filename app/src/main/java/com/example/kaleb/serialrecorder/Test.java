package com.example.kaleb.serialrecorder;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    ImageView itemImage;
    TextView uriText;
    MyDBHandler dbHandlerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        itemImage = (ImageView) findViewById(R.id.itemImage);
        uriText = (TextView) findViewById(R.id.uriText);
        dbHandlerView = new MyDBHandler(this, null, null, 1);
        getImageUri();

    }

    public void getImageUri(){
        String uriString = dbHandlerView.getImageUri();
        Uri imageUri = Uri.parse(uriString);
        uriText.setText(uriString);
        itemImage.setImageURI(imageUri);

    }
}

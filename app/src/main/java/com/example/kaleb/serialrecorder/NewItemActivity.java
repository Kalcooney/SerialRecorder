package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NewItemActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_PHOTO = 2;

    //declare the views we are going to use
    EditText itemNameInput;
    EditText itemDescriptionInput;
    EditText serialNumberInput;
    ImageView itemImageView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //reference the views we are going to use
        Button takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        itemNameInput = (EditText) findViewById(R.id.itemNameInput);
        itemDescriptionInput = (EditText) findViewById(R.id.itemDescriptionInput);
        serialNumberInput = (EditText) findViewById(R.id.serialNumberInput);
        itemImageView = (ImageView) findViewById(R.id.itemImageView);
        dbHandler = new MyDBHandler(this, null, null, 1);

        //disable the buttons if the user doesn't have a camera
        if(!hasCamera())
            takePhotoButton.setEnabled(false);

        }

    //check if user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take picture and pass it on to onActivityResult
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    //if add to gallery button is clicked
    public void galleryClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, SELECT_PHOTO);
    }

    //return image taken or image chosen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            //if take photo button clicked then request image capture
            case(REQUEST_IMAGE_CAPTURE):
                if (resultCode == RESULT_OK) {
                    //get the photo
                    Bundle extras = data.getExtras();
                    Bitmap photo = (Bitmap) extras.get("data");
                    itemImageView.setImageBitmap(photo);
                    break;
                }
             //if add from gallery chosen then select photo from gallery
            case(SELECT_PHOTO):
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try{
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    itemImageView.setImageURI(selectedImage);
                    break;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

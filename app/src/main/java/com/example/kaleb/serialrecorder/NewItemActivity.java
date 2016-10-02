package com.example.kaleb.serialrecorder;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewItemActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_PHOTO = 2;
    private File output = null; //filepath for user taken image
    private String path = null; //filepath for user taken image to place in database

    //declare the views we are going to use
    EditText itemNameInput;
    EditText itemDescriptionInput;
    EditText serialNumberInput;
    EditText datePurchasedInput;
    ImageView itemImageView;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("New Item");

        //reference the views we are going to use
        itemNameInput = (EditText) findViewById(R.id.itemNameInput);
        itemDescriptionInput = (EditText) findViewById(R.id.itemDescriptionInput);
        serialNumberInput = (EditText) findViewById(R.id.serialNumberInput);
        datePurchasedInput = (EditText) findViewById(R.id.datePurchasedInput);
        itemImageView = (ImageView) findViewById(R.id.itemImageView);
        dbHandler = new MyDBHandler(this, null, null, 1);

        //disable the buttons if the user doesn't have a camera
       if(!hasCamera())
            findViewById(R.id.takePhotoButton).setEnabled(false);
    }

    //create a new calendar object and set it to a DatePicker
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel();
        }
    };

    //onClick method for DatePurchased
    public void datePurchasedClicked(View view){
        new DatePickerDialog(NewItemActivity.this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH
        ), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //formats the date text
    private void updateLabel(){
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        datePurchasedInput.setText(sdf.format(myCalendar.getTime()));
    }

    //check if user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launch the camera for user to take photo
    public void launchCamera(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM); //get the directory of which to store the images taken
        output = new File(dir, TimeStamp() + ".jpeg"); //creates a filename with path for the image. (Timestamped to make each file have a unique name)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output)); //pass on Uri to onActivityResult
        //take picture and pass it on to onActivityResult
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    /**if add to gallery button is clicked
    public void galleryClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, SELECT_PHOTO);
    } **/

    //return image taken or image chosen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            //if take photo button clicked then request image capture
            case(REQUEST_IMAGE_CAPTURE):
                if (resultCode == RESULT_OK) {
                    Uri.fromFile(output); //get the uri from the file we made in the output string
                    path = Uri.fromFile(output).toString(); //set the filepath from Uri to a string (This is to pass on to the database entry)
                    itemImageView.setImageURI(Uri.fromFile(output)); //displays image in imageview via the Uri.
                    break;
                }

            /**if add from gallery chosen then select photo from gallery
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
                    break; **/
                }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //method for getting current time to stamp on the image Filename
    public static String TimeStamp(){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());

            return currentDateTime;
        } catch (Exception e){
            e.printStackTrace();

            return null;
        }
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
           case(android.R.id.home):
               finish();
               break;
           case(R.id.action_tick):
               //alert box to make sure user wants to confirm the item
               new AlertDialog.Builder(this)
                       .setTitle("Confirm entry")
                       .setMessage("Are you sure you want to confirm this entry?")
                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               Items userItem = new Items(datePurchasedInput.getText().toString(),
                                       itemDescriptionInput.getText().toString(), itemNameInput.getText().toString(),
                                       serialNumberInput.getText().toString(), path);
                               dbHandler.addItem(userItem);
                               finish();
                               Toast.makeText(NewItemActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                           }
                       })
                       .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               // do nothing
                           }
                       })
                       .show();

        }

        return super.onOptionsItemSelected(item);
    }
}
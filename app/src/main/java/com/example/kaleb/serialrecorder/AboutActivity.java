package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    //reference views we are going to use
    TextView versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        versionText = (TextView) findViewById(R.id.versionText);
        getVersionNumber(); //call method to set TextView text equal to the version number of the app
    }

    //method that retrieves the apps version number and sets it to a TextView
    private void getVersionNumber(){
        String version = "";
        //try catch method that retrieves version name from the package manager
        try{
            PackageInfo packageInformation = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = packageInformation.versionName;
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        versionText.setText(version); //set Textview text
    }

    //goes to website when button is clicked
    public void webButtonClicked(View view){
        String url = "http://kalebcooper.work/";
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));
        startActivity(webIntent);
    }

    //to handle up arrow event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(android.R.id.home):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

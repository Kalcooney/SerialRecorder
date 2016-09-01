package com.example.kaleb.serialrecorder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hides the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_splash);

        Runnable r = new Runnable(){

            @Override
            public void run() {
                long futureTime = System.currentTimeMillis() + 3000;
                while(System.currentTimeMillis() < futureTime){
                    synchronized (this){
                        try{
                            wait(futureTime - System.currentTimeMillis());
                            Intent mainMenu = new Intent(getBaseContext(), MainMenu.class);
                            startActivity(mainMenu);
                            finish();
                        }catch(Exception e){}
                    }
                }
            }
        };
        Thread waitThread = new Thread(r);
        waitThread.start();
    }
}

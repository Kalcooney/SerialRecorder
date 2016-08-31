package com.example.kaleb.serialrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //reference the Textview containing the helpText
        TextView helpText = (TextView) findViewById(R.id.helpText);
        //set a whole bunch of sample text
        helpText.setText("This is some sample text. This section will eventually contain " +
                "information on how to use the app. Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Mauris laoreet scelerisque vulputate. Nunc ut magna a nulla " +
                "imperdiet vehicula et vel magna. Sed ac ligula turpis. Nulla a sapien venenatis " +
                "dui pellentesque facilisis hendrerit vitae tortor. Phasellus iaculis velit sed " +
                "erat iaculis, nec porttitor magna hendrerit. Morbi porttitor velit nisi, non " +
                "ullamcorper lectus commodo et. Vestibulum convallis urna sed augue iaculis " +
                "maximus. Donec a turpis vulputate urna faucibus vehicula. Quisque fermentum " +
                "suscipit augue lobortis congue. Fusce interdum, orci et elementum maximus, orci " +
                "ipsum lacinia nulla, in fringilla mi nibh id neque. Aenean quam nulla, cursus " +
                "non nibh a, porta lobortis elit. Nulla condimentum luctus libero. Sed aliquam " +
                "purus orci.");
    }
}

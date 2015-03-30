package com.androidexample.hiveintown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by sowmiya on 3/23/2015.
 */
public class Hivein extends Activity {

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hivein);
            Button button1=(Button)findViewById(R.id.button1);
            Button button2=(Button)findViewById(R.id.button2);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),MainActivity.class);
                    startActivity(i);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent=new Intent(view.getContext(),Login.class);
                    startActivity(myIntent);
                }
            });
    }
}

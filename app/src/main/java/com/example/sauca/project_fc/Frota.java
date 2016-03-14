package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Frota extends AppCompatActivity {

//    public final static String EXTRA_MESSAGE = "com.example.sauca.project_fc.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frota);

        TextView txt= (TextView) findViewById(R.id.txt_user);

        // Get the message from the intent
        Intent intent = getIntent();
        String  msg = intent.getStringExtra(Login.EXTRA_MESSAGE);
        StringTokenizer st = new StringTokenizer(msg,".@");
        String s[]=new String[2];
//        s[]=msg.split(".|@");
        s[0]=st.nextToken().toUpperCase();
        s[1]=st.nextToken();
        Log.i("Frota",""+msg);
        Log.i("Frota", "USER-" + s[0]+" "+s[1]);
        txt.setText(s[0]+" "+s[1]);
    }
}

package com.example.sauca.project_fc;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Frota extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.sauca.project_fc.MESSAGE";
    StringTokenizer st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frota);

        TextView txt= (TextView) findViewById(R.id.txt_user);

        // Get the message from the intent
        Intent intent = getIntent();
        String  msg = intent.getStringExtra(EXTRA_MESSAGE);
        showMessage("DATA",msg);
//        st= new StringTokenizer(msg,".@");
//        String s[]=new String[2];
////        s[]=msg.split(".|@");
//        s[0]=st.nextToken();
//        char[] stringArray = s[0].trim().toCharArray();
//        stringArray[0] = Character.toUpperCase(stringArray[0]);
//        s[0]=new String(stringArray);
//        s[1]=st.nextToken();
//        stringArray = s[1].trim().toCharArray();
//        stringArray[0] = Character.toUpperCase(stringArray[0]);
//        s[1]=new String(stringArray);
//        Log.i("Frota",""+msg);
//        Log.i("Frota", "USER-" + s[0]+" "+s[1]);
//        txt.setText(s[0]+" "+s[1]);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

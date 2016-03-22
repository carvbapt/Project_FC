package com.example.sauca.project_fc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sauca.project_fc.Login.Login;

public class Menu extends AppCompatActivity {

    Intent intent;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the message from the intent
        intent = getIntent();
//        message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        Log.i("Menu", "MSG-" + message);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.i.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View v){

        Intent inte;

        if(v.getId()==R.id.BT_Frota){
            // Launching the login activity
            inte = new Intent(this, Frota.class);
//            inte.putExtra(Login.EXTRA_MESSAGE,message);
            startActivity(inte);
        }
    }
}

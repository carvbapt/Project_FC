package com.example.sauca.project_fc;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sauca.project_fc.Login.Login;

import java.util.logging.Handler;

public class Splash extends AppCompatActivity implements  View.OnClickListener{

    Intent intent;
    ImageButton bts;
    View vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        bts = (ImageButton) findViewById(R.id.BT_Splash);
        bts.setOnClickListener(this);

        bts.postDelayed(new Runnable() {
            @Override
            public void run() {
                bts.performClick();
            }
        }, 1000); // DELAY
    }

    public void  onClick(View v){
        if(v==findViewById(R.id.BT_Splash)){
            intent=new Intent(this,Login.class);
            startActivity(intent);
        }
    }
}

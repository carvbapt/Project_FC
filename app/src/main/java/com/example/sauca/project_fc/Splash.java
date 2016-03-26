package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sauca.project_fc.Login.Login;

public class Splash extends AppCompatActivity implements  View.OnClickListener{

    Intent intent;
    ImageButton bts;
    View vi;
    Boolean clk=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        clk=false;

        bts = (ImageButton) findViewById(R.id.BT_Splash);
        bts.setOnClickListener(this);

        bts.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!clk)
                    bts.performClick();
            }
        }, 5000); // DELAY
    }

    public void  onClick(View v){
        if(v==findViewById(R.id.BT_Splash)){
            intent=new Intent(getApplicationContext(), Login.class);
            clk=true;
            startActivity(intent);
        }
    }
}

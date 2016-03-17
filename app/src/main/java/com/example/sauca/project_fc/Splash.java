package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sauca.project_fc.Login.Login;

public class Splash extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        ImageButton bts = (ImageButton) findViewById(R.id.BT_Splash);

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
            }
        });
    }


    public void  onClick(View v){
        if(v.getId()==R.id.BT_Splash){
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
            intent=new Intent(this, com.example.sauca.project_fc.Menu.class);
            startActivity(intent);

        }
    }
}

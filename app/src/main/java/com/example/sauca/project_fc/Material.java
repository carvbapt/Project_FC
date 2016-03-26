package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Material extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);

        ibtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.BTI_Back)) {
            startActivity(new Intent(this,Registo.class));
        }
    }
}

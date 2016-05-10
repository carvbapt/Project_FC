package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Diario extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack;
    TextView  tvData;

    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);



        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtBack.setOnClickListener(this);

        tvData=(TextView)findViewById(R.id.TV_Data);

        // Get the message from the intent
        it = getIntent();

        if(it.getExtras().getString("activity").equals("agenda")){
            tvData.setText(it.getExtras().getString("dia"));
        }else{
            final SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
            tvData.setText(formatDate.format(new Date()));
        }
    }

    @Override
    public void onClick(View v) {
            onBackPressed();
    }
}

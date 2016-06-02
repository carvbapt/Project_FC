package com.example.sauca.appfc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sauca.appfc.Registo.Material;
import com.example.sauca.appfc.Registo.Registo;

public class Configurar extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack;
    Button btRegisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        btRegisto=(Button)findViewById(R.id.BT_Registo);

        ibtBack.setOnClickListener(this);
        btRegisto.setOnClickListener(this);

        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {

        if(v==findViewById(R.id.BTI_Back))
            onBackPressed();
        else if(v==findViewById(R.id.BT_Registo))
            startActivity(new Intent(this, Registo.class));

    }
}

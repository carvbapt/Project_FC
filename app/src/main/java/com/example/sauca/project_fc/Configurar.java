package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.sauca.project_fc.Intervencao.Intervencao;

public class Configurar extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);

        ibtBack.setOnClickListener(this);

        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {
           onBackPressed();
    }
}

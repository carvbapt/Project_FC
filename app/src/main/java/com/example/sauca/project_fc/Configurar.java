package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sauca.project_fc.Intervencao.Intervencao;
import com.example.sauca.project_fc.Registo.Registo;

public class Configurar extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack;
    Button btMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        btMaterial=(Button)findViewById(R.id.BT_Mate);

        ibtBack.setOnClickListener(this);
        btMaterial.setOnClickListener(this);


        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {

        if(v==this.findViewById(R.id.BTI_Back)) {
            onBackPressed();
        }else if(v==this.findViewById(R.id.BT_Mate)){
            startActivity(new Intent(this, Registo.class));
        }
    }
}

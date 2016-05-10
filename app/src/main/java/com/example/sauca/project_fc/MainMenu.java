package com.example.sauca.project_fc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sauca.project_fc.Agenda.Agenda;
import com.example.sauca.project_fc.Intervencao.Intervencao;
import com.example.sauca.project_fc.Login.Login;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack,ibtConf;
    Button btInterv,btFrota,btAgenda,btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtConf=(ImageButton)findViewById(R.id.BTI_Back);
        btInterv=(Button)findViewById(R.id.BT_Intervencao);
        btFrota=(Button)findViewById(R.id.BT_Frota);
        btAgenda=(Button)findViewById(R.id.BT_Agenda);
        btSair=(Button)findViewById(R.id.BT_Sair);

        ibtBack.setOnClickListener(this);
        ibtConf.setOnClickListener(this);
        btInterv.setOnClickListener(this);
        btFrota.setOnClickListener(this);
        btAgenda.setOnClickListener(this);
        btSair.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v==findViewById(R.id.BTI_Back))
            startActivity(new Intent(this,Login.class));
        else if(v==findViewById(R.id.BTI_Conf))
            startActivity(new Intent(this,Intervencao.class));
        else if(v==findViewById(R.id.BT_Intervencao))
            startActivity(new Intent(this,Intervencao.class));
        else if(v==findViewById(R.id.BT_Frota))
            startActivity(new Intent(this,Frota.class));
        else if(v==findViewById(R.id.BT_Agenda))
            startActivity(new Intent(this,Agenda.class));
        else if(v==findViewById(R.id.BT_Sair)){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }

    }
}

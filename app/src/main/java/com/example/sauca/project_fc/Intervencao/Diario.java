package com.example.sauca.project_fc.Intervencao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Adapter.DiarAdapter;
import com.example.sauca.project_fc.DB.Dados;
import com.example.sauca.project_fc.DB.Model.Diaria;
import com.example.sauca.project_fc.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diario extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtBack;
    TextView  tvData;

    Intent it;

    ListView lvDiario;
    DiarAdapter adptDiario;

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

        lvDiario=(ListView)findViewById(R.id.LV_Diario);


        loadData();
    }

    private void loadData() {


        adptDiario=new DiarAdapter(getBaseContext(),R.layout.activity_diario_row);
        lvDiario.setAdapter(adptDiario);

        for(int r=0;r< Dados.Ordens.length;r++){
            Diaria dtprov= new Diaria(Dados.Ordens[r],Dados.Janela[r][1],Dados.Janela[r][2]);
            adptDiario.add(dtprov);
        }

        lvDiario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position=position+1;
                Toast.makeText(getBaseContext(),"Linha - "+ position,Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(),Intervencao.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
            onBackPressed();
    }
}

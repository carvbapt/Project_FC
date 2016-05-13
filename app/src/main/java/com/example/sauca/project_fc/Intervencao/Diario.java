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
    String dt;
    String[] str;

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
            dt=it.getExtras().getString("data");
        }else{
            final SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
            tvData.setText(formatDate.format(new Date()));
            dt = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
        }

        lvDiario=(ListView)findViewById(R.id.LV_Diario);

        loadData();
    }

    private void loadData() {

        adptDiario=new DiarAdapter(getBaseContext(),R.layout.activity_diario_row);
        lvDiario.setAdapter(adptDiario);

//        Toast.makeText(getBaseContext(),"Data - "+ dt,Toast.LENGTH_LONG).show();
        str=new  String[Dados.Ordens.length];

        int i=0;

        for(int r=0;r< Dados.Ordens.length;r++){
            if(Dados.Janela[r][1].toString().contentEquals(dt.toString())) {
                Diaria dtprov = new Diaria(Dados.Ordens[r], Dados.Janela[r][2], Dados.Janela[r][3]);
                adptDiario.add(dtprov);
                str[i]=Dados.Ordens[r];
                i++;
            }
        }

        lvDiario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                position=position+1;
//                Toast.makeText(getBaseContext(),"Linha - "+ position,Toast.LENGTH_LONG).show();
//                intent.putExtra(EXTRA_MESSAGE, dados.Empresas[position]);
                //    Toast.makeText(EmpresaActivity.this, Dados.Empresas[position], Toast.LENGTH_SHORT).show
                it=new Intent(getBaseContext(),Intervencao.class);
                it.putExtra("ot",str[position]);
                startActivity(it);
            }
        });

    }

    @Override
    public void onClick(View v) {
            onBackPressed();
    }
}

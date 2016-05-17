package com.example.sauca.appfc.Intervencao;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sauca.appfc.DB.Adapter.DiarAdapter;
import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Funcionario;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.appfc.R;

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

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }


    private void loadData() {

        DiarioRepo myDB;
        Cursor pnt;

        myDB= new DiarioRepo(this);
        pnt= myDB.getAllData();

        if(pnt.getCount()==0){
            // show message
            if(!myDB.DatabaseExist(getBaseContext(),"DBFastcall.db")) {
                findViewById(R.id.Ll_Search).setVisibility(LinearLayout.GONE);
                showMessage("Base de Dados", "Não existe");
            }
            return;
        }

        adptDiario=new DiarAdapter(getBaseContext(),R.layout.activity_diario_row);
        lvDiario.setAdapter(adptDiario);

//        Toast.makeText(getBaseContext(),"Data - "+ dt,Toast.LENGTH_LONG).show();
        str=new  String[Dados.Ordens.length];

        int i=0;

        while(pnt.moveToNext()){
            if(pnt.getString(2).contentEquals(dt.toString())) {
                Diaria dtprov = new Diaria(pnt.getString(1),pnt.getString(3),pnt.getString(4));
                adptDiario.add(dtprov);
                str[i]=pnt.getString(1);
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*     MENSAGENS  ALERT DIALOG   */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

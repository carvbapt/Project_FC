package com.example.sauca.appfc.Agenda;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Agenda extends AppCompatActivity implements View.OnClickListener {

    // List

    ListView list;
    ArrayAdapter<String> adapter;
    String[][] dados= new String[Dados.det_empresa.length][2];
    String[] dados_emp;
    String[] dados_val;
    Date data_sys,data_manut;
    int i,ind,cont=0;

    //S@C
    private ImageButton ibtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtBack.setOnClickListener(this);


        //List
        for(i=0; i<Dados.det_empresa.length;i++){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // here set the pattern as you date in string was containing like date/month/year
            data_sys = new Date();
            try {
                data_manut = sdf.parse(Dados.det_empresa[i][5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("", "DTS - " + sdf.format(data_sys) + "->" + sdf.format(data_manut));
            if(data_sys.after(data_manut) || data_sys.equals(data_manut)){
                ind=Integer.parseInt(Dados.det_empresa[i][0]);
                dados[cont][0]=Dados.Empresas[ind].substring(0, (Dados.Empresas[ind].length() >= 22) ? 22 : Dados.Empresas[ind].length()) + ((Dados.Empresas[ind].length() >= 22) ? "..." : "");
                dados[cont][1]=sdf.format(data_manut);
                cont++;
            }
        }

        dados_emp= new String[cont];
        dados_val= new String[cont];

        for(i=0;i<cont;i++){
            dados_emp[i]=dados[i][0];
            dados_val[i]=dados[i][1];
            Log.i("","empval - "+dados_emp[i] + " " + dados_val[i] + cont);
        }

        list = (ListView)findViewById(R.id.lv_ag);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_agendaitem, dados_emp);
        list.setAdapter(adapter);

        agenda();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"Data: " + dados_val[position],Toast.LENGTH_LONG).show();
            }
        });
    }

    public void agenda(){
        ViewPager pager = (ViewPager) findViewById(R.id.viewPagerAgenda);
        pager.setAdapter(new MyPagerAdapterSubZones(getSupportFragmentManager()));

    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.BTI_Back)) {
            onBackPressed();
        }
    }

    private class MyPagerAdapterSubZones extends FragmentPagerAdapter {

        public MyPagerAdapterSubZones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return FragAgenda.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}

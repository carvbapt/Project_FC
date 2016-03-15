package com.example.sauca.project_fc.DBLogin;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

public class Login_List extends AppCompatActivity {

    ListView listView;
    DataBaseLogin myDB;
    TypedArray imgs;
    Cursor res;
    LogAdapter adapter;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        listView=(ListView)findViewById(R.id.LV_Util);
        myDB= new DataBaseLogin(this);
        imgs = getResources().obtainTypedArray(R.array.logo);
        res= myDB.getAllData();

        if(res.getCount()==0){
            // show message
            showMessage("ERRO","Base de Dados vazia");
            return;
        }

        adapter = new LogAdapter(getApplicationContext(), R.layout.activity_login_list_row);
        listView.setAdapter(adapter);

        while(res.moveToNext()){
            if(res.getString(4).contains("fastcall"))
                i=0;
            if(res.getString(4).contains("fieldservice"))
                i=1;

            LogDataProvider dtprovider= new LogDataProvider(imgs.getResourceId(i,-1),res.getString(0),res.getString(1),res.getString(2));
            adapter.add(dtprovider);
            i++;
        }

        // Seleciona linha
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int lin;
                lin= position+1;
                res.moveToPosition(position);
                Toast.makeText(getBaseContext(),"LINHA "+lin+" "+res.getString(1)+" "+res.getString(2), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

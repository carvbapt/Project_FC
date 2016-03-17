package com.example.sauca.project_fc.Login;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Adapter.LogAdapter;
import com.example.sauca.project_fc.DB.Adapter.LogDataProvider;
import com.example.sauca.project_fc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.project_fc.R;

public class Login_List extends AppCompatActivity {

    ListView listView;
    FuncionarioRepo myDB;
    TypedArray imgs;
    Cursor pnt;
    LogAdapter adapter;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        listView=(ListView)findViewById(R.id.LV_Util);
        myDB= new FuncionarioRepo(this);
        imgs = getResources().obtainTypedArray(R.array.logo);
        pnt= myDB.getAllData();

        if(pnt.getCount()==0){
            // show message
            showMessage("ERRO","Base de Dados vazia");
            return;
        }

        adapter = new LogAdapter(getApplicationContext(), R.layout.activity_login_list_row);
        listView.setAdapter(adapter);

        while(pnt.moveToNext()){

            if(pnt.getString(5).contains("Fastcall"))
                i=0;
            if(pnt.getString(5).contains("Fieldservices"))
                i=1;

            LogDataProvider dtprovider= new LogDataProvider(imgs.getResourceId(i,-1),pnt.getString(0),pnt.getString(1),pnt.getString(2));
            adapter.add(dtprovider);
        }

        // Seleciona linha
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int lin;
                lin= position+1;
                pnt.moveToPosition(position);
                Toast.makeText(getBaseContext(),"LINHA "+lin+" "+pnt.getString(1)+" "+pnt.getString(2), Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}

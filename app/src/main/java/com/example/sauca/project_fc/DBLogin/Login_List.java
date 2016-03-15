package com.example.sauca.project_fc.DBLogin;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

import android.database.sqlite.*;
import java.sql.SQLData;

public class Login_List extends AppCompatActivity {

    ListView listView;
    DataBaseLogin myDB;
    SQLiteDatabase conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        listView=(ListView)findViewById(R.id.LV_Util);
        myDB= new DataBaseLogin(this);

        Cursor res= myDB.getAllData();
        if(res.getCount()==0){
            // show message
            showMessage("ERRO","Base de Dados vazia");
            return;

        }
        StringBuffer buff=new StringBuffer();
        while(res.moveToNext()){
            buff.append("Id:"+res.getString(0)+"\n");
            buff.append("Nome:"+res.getString(1)+"\n");
            buff.append("Apelido:"+res.getString(2)+"\n");
            buff.append("Email:"+res.getString(4)+"\n");
        }
        showMessage("Data",buff.toString());

        Toast.makeText(this, "LISTAR DB", Toast.LENGTH_LONG).show();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

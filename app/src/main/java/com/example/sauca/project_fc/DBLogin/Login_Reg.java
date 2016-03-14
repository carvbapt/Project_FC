package com.example.sauca.project_fc.DBLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

public class Login_Reg extends AppCompatActivity {

    DataBaseLogin myDB;
    EditText etNome,etApelido,etPassword,etEmail;
    Button btnAdd,btnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__reg);

        myDB= new DataBaseLogin(this);
        etNome=(EditText)findViewById(R.id.ET_Nome);
        etApelido=(EditText)findViewById(R.id.ET_Apelido);
        etPassword=(EditText)findViewById(R.id.ET_Password);
        etEmail=(EditText)findViewById(R.id.ET_Email);

        btnAdd=(Button)findViewById(R.id.BT_Add);
        btnList=(Button)findViewById(R.id.BT_List);

        addData();

        Toast.makeText(this, "INICIO DB", Toast.LENGTH_LONG).show();
    }

    public void addData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Login_Reg","Registo - "+etNome.getText()+" "+etApelido.getText()+" "+etPassword+" "+etEmail);

               boolean isInserted=myDB.insertData(etNome.getText().toString(),etApelido.getText().toString(),etPassword.getText().toString(),etEmail.getText().toString());

                if(isInserted==true)
                    Toast.makeText(Login_Reg.this, "Registo Gravado na BD", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Login_Reg.this, "Registo Não Gravado na BD", Toast.LENGTH_LONG).show();
            }
        });
    }
}

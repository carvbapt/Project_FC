package com.example.sauca.project_fc.DBLogin;

import android.app.AlertDialog;
import android.content.Intent;
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
        setContentView(R.layout.activity_login_reg);

        myDB= new DataBaseLogin(this);
        etNome=(EditText)findViewById(R.id.ET_Nome);
        etApelido=(EditText)findViewById(R.id.ET_Apelido);
        etPassword=(EditText)findViewById(R.id.ET_Password);
        etEmail=(EditText)findViewById(R.id.ET_Email);

        btnAdd=(Button)findViewById(R.id.BT_Add);
        btnList=(Button)findViewById(R.id.BT_List);

        addData();
        viewAll();
    }

    public void addData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Login_Reg", "Registo - " + etNome.getText() + " " + etApelido.getText() + " " + etPassword + " " + etEmail);

                boolean isInserted = myDB.insertData(etNome.getText().toString(), etApelido.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString());

                if (isInserted)
                    Toast.makeText(Login_Reg.this, "Registro Gravado na BD", Toast.LENGTH_LONG).show();
                else
                    showMessage("Base de Dados", "Registro Não Gravado na BD");
                resetCampos();
            }
        });
    }

    public void viewAll(){
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Reg.this,Login_List.class);
                startActivity(intent);
            }
        });
    }

    public  void resetCampos(){

        etNome.setText(null);
        etNome.requestFocus();
        etApelido.setText(null);
        etEmail.setText(null);
        etPassword.setText(null);

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("OK",null);
        builder.show();
    }
}

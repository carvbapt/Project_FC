package com.example.sauca.project_fc.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Model.Funcionario;
import com.example.sauca.project_fc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.project_fc.R;

public class Login_Reg extends AppCompatActivity implements View.OnClickListener {

    FuncionarioRepo repofunc;
    Funcionario func;
    EditText etNome,etApelido,etPassword,etEmail;
    Spinner spEmpresa;
    ArrayAdapter spadapter;

    Button btnAdd,btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        func=new Funcionario();
        repofunc= new FuncionarioRepo(this);

        etNome=(EditText)findViewById(R.id.ET_Nome);
        etApelido=(EditText)findViewById(R.id.ET_Apelido);
        etPassword=(EditText)findViewById(R.id.ET_Password);
        etEmail=(EditText)findViewById(R.id.ET_Email);
        spEmpresa=(Spinner)findViewById(R.id.SP_Empresa);

        spadapter = ArrayAdapter.createFromResource (this,R.array.empresa,android.R.layout.simple_spinner_item);
        spEmpresa.setAdapter(spadapter);
        spEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                func.f_empresa=getResources().getStringArray(R.array.empresa)[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                func.f_empresa=getResources().getStringArray(R.array.empresa)[0].toString();
            }
        });

        btnAdd=(Button)findViewById(R.id.BT_Add);
        btnAdd.setOnClickListener(this);

        btnList=(Button)findViewById(R.id.BT_List);
        btnList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v==findViewById(R.id.BT_Add)){
            addData();
        }else if(v==findViewById(R.id.BT_List)){
            viewAll();
        }
    }

    public void addData(){

                Toast.makeText(Login_Reg.this, "Botão Clicado", Toast.LENGTH_LONG).show();
                func.f_nome=etNome.getText().toString();
                func.f_apelido=etApelido.getText().toString();
                func.f_password=etPassword.getText().toString();
                func.f_email=etEmail.getText().toString();

                int isInserted = repofunc.insert(func);

                if (isInserted>0)
                    Toast.makeText(Login_Reg.this, "Registro Gravado na BD", Toast.LENGTH_LONG).show();
                else
                    showMessage("Base de Dados", "Registro Não Gravado na BD");

                resetCampos();
    }

    public void viewAll(){
                Intent intent=new Intent(Login_Reg.this,Login_List.class);
                startActivity(intent);
    }

    public  void resetCampos(){

        etNome.setText(null);
        etNome.requestFocus();
        etApelido.setText(null);
        etEmail.setText(null);
        etPassword.setText(null);
        spEmpresa.setAdapter(spadapter);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("OK",null);
        builder.show();
    }
}

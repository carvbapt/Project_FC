package com.example.sauca.project_fc.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Model.Funcionario;
import com.example.sauca.project_fc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.project_fc.R;

public class Login_Reg extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MSG = "com.example.sauca.project_fc.MSG_UPD";
    Intent it;
    String  nom;
    int ind=0;


    FuncionarioRepo repofunc;
    Funcionario func;
    EditText etNome,etApelido,etPassword,etEmail;
    Spinner spEmpresa;
    ArrayAdapter spadapter;

    Button btnAdd,btnList;
    ImageButton btiAdd,btiSave,btiList,btiDel,btiCle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        btiAdd=(ImageButton)findViewById(R.id.BTI_Ladd);
        btiSave=(ImageButton)findViewById(R.id.BTI_Lupd);
        btiList=(ImageButton)findViewById(R.id.BTI_Llis);
        btiCle=(ImageButton)findViewById(R.id.BTI_Lcle);
        btiDel=(ImageButton)findViewById(R.id.BTI_Ldel);

        // Get the message from the intent
        it = getIntent();
        ind=it .getIntExtra(EXTRA_MSG, ind);
        Log.i("Login_Reg", "MSG-" + ind);

        func=new Funcionario();
        repofunc= new FuncionarioRepo(this);

        etNome=(EditText)findViewById(R.id.ET_Nome);
        etApelido=(EditText)findViewById(R.id.ET_Apelido);
        etPassword=(EditText)findViewById(R.id.ET_Password);
        etEmail=(EditText)findViewById(R.id.ET_Email);
        spEmpresa=(Spinner)findViewById(R.id.SP_Empresa);

        spadapter = ArrayAdapter.createFromResource (this,R.array.empresa,android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spEmpresa.setAdapter(spadapter);

        if(ind==0) {
            btiSave.setVisibility(View.GONE);
            btiDel.setVisibility(View.GONE);

            spEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                    ((TextView) parent.getChildAt(0)).setTextSize(20);
                    func.f_empresa = getResources().getStringArray(R.array.empresa)[position].toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    func.f_empresa = getResources().getStringArray(R.array.empresa)[0].toString();
                }
            });
        }else
        {
            btiAdd.setVisibility(View.GONE);
            btiCle.setVisibility(View.GONE);

            func = repofunc.searchDataID(ind);
            etNome.setText(func.f_nome);
            etApelido.setText(func.f_apelido);
            etPassword.setText(func.f_password);
            etEmail.setText(func.f_email);
            if(func.f_empresa.toUpperCase().equals("FASTCALL"))
                spEmpresa.setSelection(0);
            else
                spEmpresa.setSelection(1);
//            Toast.makeText(this, "MSG - "+ind+"  ->"+curs.getString(1)+" - "+curs.getString(2), Toast.LENGTH_SHORT).show();
        }
        btiAdd.setOnClickListener(this);
        btiCle.setOnClickListener(this);
        btiList.setOnClickListener(this);
        btiSave.setOnClickListener(this);
        btiDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==findViewById(R.id.BTI_Ladd)) {
            addData();
        }else if(v==findViewById(R.id.BTI_Lcle)){
            resetCampos();
        }else if(v==findViewById(R.id.BTI_Lupd)){
            Log.i("Login_Reg","CLICK - "+ind);
            Toast.makeText(this, " ACTUALIZAR EM FALTA ", Toast.LENGTH_SHORT).show();
            ind=0;
        }else if(v==findViewById(R.id.BTI_Ldel)){
            showConfirm(ind, etNome.getText().toString());
            ind=0;
        }else if(v==findViewById(R.id.BTI_Llis)) {
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
        builder.setNeutralButton("OK", null);
        builder.show();
    }

    public void showConfirm(final int pos,final String nom){
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle("Eliminar registo " + nom);
        bld.setMessage("Confirma Eliminação?");
        bld.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res=repofunc.delete(pos);
                if(res==1)
                    Toast.makeText(getApplicationContext(), "Registo eliminado com sucesso", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Registo eliminado sem sucesso", Toast.LENGTH_LONG).show();
            }
        });
        bld.setPositiveButton("Não", null);
        bld.show();
    }
}

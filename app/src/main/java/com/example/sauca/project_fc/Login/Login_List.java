package com.example.sauca.project_fc.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Adapter.FuncAdapter;
import com.example.sauca.project_fc.DB.Model.Funcionario;
import com.example.sauca.project_fc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.project_fc.R;

public class Login_List extends AppCompatActivity implements  View.OnClickListener {

    public final static String EXTRA_MSG = "com.example.sauca.project_fc.MSG_UPD";
    Intent it;

    // LISTAR
    ListView listView;
    FuncionarioRepo myDB;
    TypedArray imgs;
    Cursor pnt;
    FuncAdapter adapter;

    // PROCURAR
    EditText etSearch;
    ImageButton btiReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        // Definitions
        btiReset=(ImageButton)findViewById(R.id.BTI_Reset);
        listView=(ListView)findViewById(R.id.LV_Util);
        etSearch=(EditText)findViewById(R.id.ET_Search);
        imgs = getResources().obtainTypedArray(R.array.logo);

        myDB= new FuncionarioRepo(this);

        // Listeners
        btiReset.setOnClickListener(this);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pnt = myDB.searchData("nomeapelido", etSearch.getText().toString());
                outputData();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pnt= myDB.getAllData();
        outputData();
    }

    @Override
    public void onClick(View v) {
            etSearch.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public  void outputData(){

        if(pnt.getCount()==0){
            // show message
            showMessage("Base de Dados", "Não existe");
            etSearch.setText("");
            return;
        }

        adapter = new FuncAdapter(getApplicationContext(), R.layout.activity_login_list_row);
        listView.setAdapter(adapter);

        int i=0;
        while(pnt.moveToNext()){
            if(pnt.getString(5).contains("Fastcall"))
                i=0;
            if(pnt.getString(5).contains("Fieldservices"))
                i=1;

            Funcionario dtprovider= new Funcionario(imgs.getResourceId(i,-1),Integer.parseInt(pnt.getString(0)),pnt.getString(1),pnt.getString(2));
            adapter.add(dtprovider);
        }

        // Seleciona linha
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int lin=position+1;
                pnt.moveToPosition(position);
//                Toast.makeText(getBaseContext(), "LINHA " + lin + " " + pnt.getString(0) + " " + pnt.getString(1), Toast.LENGTH_LONG).show();
                showEdit(Integer.parseInt(pnt.getString(0)), pnt.getString(1));
//                Toast.makeText(getBaseContext(), "LINHA " + lin + " " + pnt.getString(1) + " " + pnt.getString(2), Toast.LEN/GTH_LONG).show();
            }
        });
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

    public void showEdit(final int pos,final String nom){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Registo de " + nom);
        builder.setMessage("Deseja Editar? ");
        builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "EDITAR Registo" + pos, Toast.LENGTH_LONG).show();
                // code to edit
                it = new Intent(getBaseContext(), Login_Reg.class);
                it.putExtra(EXTRA_MSG, pos);
                startActivity(it);
            }
        });
        builder.setPositiveButton("Cancelar", null);
        builder.show();
    }

}

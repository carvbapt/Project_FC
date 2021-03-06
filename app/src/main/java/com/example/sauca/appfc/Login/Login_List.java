package com.example.sauca.appfc.Login;

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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.sauca.appfc.DB.Adapter.FuncAdapter;
import com.example.sauca.appfc.DB.Model.Funcionario;
import com.example.sauca.appfc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.appfc.R;

public class Login_List extends AppCompatActivity implements  View.OnClickListener, TextWatcher {

    public final static String EXTRA_MSG = "com.example.sauca.project_fc.MESSAGE";

    // LISTAR
    ListView listView;

    TypedArray imgs;
    Cursor pnt;
    FuncAdapter adapter;

    // PROCURAR
    EditText etSearch;
    ImageButton ibtLlback,ibtReset;
    Intent it;

    FuncionarioRepo myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);

        // Definitions
        ibtReset=(ImageButton)findViewById(R.id.BTI_Reset);
        ibtLlback=(ImageButton)findViewById(R.id.BTI_Back);
        listView=(ListView)findViewById(R.id.LV_Util);
        etSearch=(EditText)findViewById(R.id.ET_fSearch);
        imgs = getResources().obtainTypedArray(R.array.logo);

        myDB= new FuncionarioRepo(this);

        // Listeners
        ibtReset.setOnClickListener(this);
        ibtLlback.setOnClickListener(this);

        etSearch.setFocusable(false);
//        etSearch.setFocusableInTouchMode(true);
        etSearch.setOnClickListener(this);
        etSearch.addTextChangedListener(this);
        pnt= myDB.getAllData();
        outputData();

        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.BTI_Back)) {
           finish();
        }else if(v==findViewById(R.id.ET_fSearch)){
            etSearch.setFocusable(true);
            etSearch.setFocusableInTouchMode(true);
        }else if(v==findViewById(R.id.BTI_Reset)){
           etSearch.setText("");
           etSearch.setFocusable(false);
        }
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }



    public  void outputData(){

        if(pnt.getCount()==0){
            // show message
            if(!myDB.DatabaseExist(getBaseContext(),"DBFastcall.db")) {
                findViewById(R.id.Ll_Search).setVisibility(LinearLayout.GONE);
                showMessage("Base de Dados", "Não existe");
            }else{
                showMessage("Base de Dados", "Não existe Funcionário");
                etSearch.setText("");
            }
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

package com.example.sauca.appfc.Intervencao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.sauca.appfc.DB.Adapter.DiarAdapter;
import com.example.sauca.appfc.DB.Adapter.MateAdapter;
import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.Registo.Material;

public class Diario_List extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    // LISTAR
    ListView listView;

    Cursor pnt;
    DiarAdapter adapter;
    DiarioRepo myDB;
    String[] str;

    // PROCURAR
    EditText etSearch;
    ImageButton ibtLlback,ibtReset;
    Intent it;

    RadioButton rbtOt,rbtHora,rbtEsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario_list);

        // Definitions
        ibtReset=(ImageButton)findViewById(R.id.BTI_Reset);
        ibtLlback=(ImageButton)findViewById(R.id.BTI_Back);
        listView=(ListView)findViewById(R.id.LV_Dialist);
        etSearch=(EditText)findViewById(R.id.ET_dSearch);
        rbtOt=(RadioButton)findViewById(R.id.RBM_Ot);
        rbtHora=(RadioButton)findViewById(R.id.RBM_Hora);
        rbtEsta=(RadioButton)findViewById(R.id.RBM_Estad);

        myDB= new DiarioRepo(this);

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
        }else if(v==findViewById(R.id.ET_dSearch)){
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
        if(rbtOt.isChecked())
            pnt = myDB.searchData("ot", etSearch.getText().toString());
        else if(rbtHora.isChecked())
            pnt = myDB.searchData("hora", etSearch.getText().toString());
        else
            pnt = myDB.searchData("estado", etSearch.getText().toString());

        outputData();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public  void outputData(){

        if(pnt.getCount()==0){
            // show message
            if(!myDB.DatabaseExist(getBaseContext(),"DBFastcall.db")) {
                findViewById(R.id.LL_DSearch).setVisibility(LinearLayout.GONE);
                showMessage("Base de Dados", "Não existe");
            }else{
                if(rbtOt.isChecked())
                    showMessage("Base de Dados", "Não existe OT");
                else if(rbtHora.isChecked())
                    showMessage("Base de Dados", "Não existe Hora");
                else
                    showMessage("Base de Dados", "Não existe Estado");

                etSearch.setText("");
            }
            return;
        }

        adapter = new DiarAdapter(getApplicationContext(), R.layout.activity_diario_row);
        listView.setAdapter(adapter);

        str=new  String[pnt.getCount()];

        int i=0;
        while(pnt.moveToNext()){
            Diaria dtprov = new Diaria(pnt.getString(1),pnt.getString(3),pnt.getString(4));
            adapter.add(dtprov);
            str[i]=pnt.getString(0);
            i++;
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

        final AlertDialog builder=new AlertDialog.Builder(Diario_List.this).create();
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
                it = new Intent(getBaseContext(), Material.class);
                it.putExtra("EXTRA_MSG", pos);
                startActivity(it);
            }
        });
        builder.setPositiveButton("Cancelar", null);
        builder.show();
    }
}

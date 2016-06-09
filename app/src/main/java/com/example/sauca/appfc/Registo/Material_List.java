package com.example.sauca.appfc.Registo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sauca.appfc.DB.Adapter.MateAdapter;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.DB.RepoQuery.MateriaRepo;
import com.example.sauca.appfc.R;

public class Material_List extends AppCompatActivity implements View.OnClickListener,TextWatcher {

    // LISTAR
    ListView listView;

    Cursor pnt;
    MateAdapter adapter;
    MateriaRepo myDB;

    // PROCURAR
    EditText etSearch;
    ImageButton ibtLlback,ibtReset;
    Intent it;

    RadioButton rbtMate,rbtMode,rbtEsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        // Definitions
        ibtReset=(ImageButton)findViewById(R.id.BTI_Reset);
        ibtLlback=(ImageButton)findViewById(R.id.BTI_Back);
        listView=(ListView)findViewById(R.id.LV_Matlist);
        etSearch=(EditText)findViewById(R.id.ET_mSearch);
        rbtMate=(RadioButton)findViewById(R.id.RBM_Material);
        rbtMode=(RadioButton)findViewById(R.id.RBM_Modelo);
        rbtEsta=(RadioButton)findViewById(R.id.RBM_Estado);

        myDB= new MateriaRepo(this);

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
        }else if(v==findViewById(R.id.ET_mSearch)){
            etSearch.setFocusable(true);
            etSearch.setFocusableInTouchMode(true);
        }else if(v==findViewById(R.id.BTI_Reset)) {
            etSearch.setText("");
            etSearch.setFocusable(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(rbtMate.isChecked())
            pnt = myDB.searchData("material", etSearch.getText().toString());
        else if(rbtMode.isChecked())
            pnt = myDB.searchData("modelo", etSearch.getText().toString());
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
                findViewById(R.id.Ll_Search).setVisibility(LinearLayout.GONE);
                showMessage("Base de Dados", "Não existe");
            }else{
                if(rbtMate.isChecked())
                    showMessage("Base de Dados", "Não existe Material");
                else if(rbtMode.isChecked())
                    showMessage("Base de Dados", "Não existe Modelo");
                else
                    showMessage("Base de Dados", "Não existe Estado");

                etSearch.setText("");
            }
            return;
        }

        adapter = new MateAdapter(getApplicationContext(), R.layout.fragment_material_row);
        listView.setAdapter(adapter);

        while(pnt.moveToNext()){
             Materia dtprovider= new Materia(pnt.getString(2),pnt.getString(4),pnt.getString(10));
             adapter.add(dtprovider);
        }

        // Seleciona linha
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pnt.moveToPosition(position);
                showEdit(String.valueOf(pnt.getInt(0)),pnt.getString(1),pnt.getString(2));
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*     MENSAGENS  ALERT DIALOG   */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showMessage(String title, String message){

        final AlertDialog builder=new AlertDialog.Builder(Material_List.this).create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showEdit(final String pos,final String ot,String mat){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Registo de " + mat);
        builder.setMessage("Deseja Editar? ");
        builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code to edit
                it = new Intent(getBaseContext(), Material.class);
                it.putExtra("otM", ot);
                Log.i("POSICAO","Lista - "+pos);
                it.putExtra("IndM",pos);
                startActivity(it);
            }
        });
        builder.setPositiveButton("Cancelar", null);
        builder.show();
    }
}

package com.example.sauca.appfc.Registo;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;


import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;
import com.example.sauca.appfc.zxing.android.IntentResult;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Material extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,View.OnFocusChangeListener {

    private Intent it;

    private ImageButton ibtBack;
    private TextView tvOt;

    private RadioButton rbLeitor;
    private ImageButton ibSav,ibDel;

    private EditText etMaterial,etMarca,etModelo,etSerial,etMac,etImei,etIccid,etCartao;

    private Spinner spEstado;
    private ArrayAdapter spadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        it=getIntent();
        Toast.makeText(this,""+it.getStringExtra("otM")+" Material -"+ it.getStringExtra("Material"), Toast.LENGTH_SHORT).show();

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);

        tvOt=(TextView)findViewById(R.id.TV_Ot);

        rbLeitor = (RadioButton)findViewById(R.id.RB_Leitor);
        ibSav=(ImageButton)findViewById(R.id.IB_Salvar);
        ibDel=(ImageButton)findViewById(R.id.IB_Elimina);

        etMaterial=(EditText)findViewById(R.id.ET_Material);
        etMarca=(EditText)findViewById(R.id.ET_Marca);
        etModelo=(EditText)findViewById(R.id.ET_Modelo);
        etSerial = (EditText)findViewById(R.id.ET_Serial);
        etMac = (EditText)findViewById(R.id.ET_Mac);
        etImei=(EditText)findViewById(R.id.ET_Imei);
        etIccid=(EditText)findViewById(R.id.ET_Iccid);
        etCartao=(EditText)findViewById(R.id.ET_Cartao);

        spEstado=(Spinner)findViewById(R.id.SP_Estado);
        spadapter = ArrayAdapter.createFromResource (this,R.array.estado,android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spEstado.setAdapter(spadapter);

        tvOt.setText(getIntent().getStringExtra("otM"));

        ibtBack.setOnClickListener(this);
//        rbLeitor.setOnClickListener(this);
//        etMaterial.setOnClickListener(this);
//        etMarca.setOnClickListener(this);
//        etModelo.setOnClickListener(this);

        etSerial.setOnClickListener(this);
        etSerial.setOnFocusChangeListener(this);
        etMac.setOnClickListener(this);
        etMac.setOnFocusChangeListener(this);
        etImei.setOnClickListener(this);
        etImei.setOnFocusChangeListener(this);
        etIccid.setOnClickListener(this);
        etIccid.setOnFocusChangeListener(this);
        etCartao.setOnClickListener(this);
        etCartao.setOnFocusChangeListener(this);

        spEstado.setOnItemSelectedListener(this);

        ibSav.setOnClickListener(this);
        ibDel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.BTI_Back))
            onBackPressed();
        else if(v==findViewById(R.id.IB_Salvar))
            gravar();
        else if(v==findViewById(R.id.IB_Elimina))
            elimina();
        else
            insData(v,true);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        insData(v,hasFocus);

    }

    private void insData(View v,boolean focus) {
        if(!(v==findViewById(R.id.ET_Material))||!(v==findViewById(R.id.ET_Marca))||!(v==findViewById(R.id.ET_Modelo))||!(v==findViewById(R.id.SP_Estado))) {
            if (rbLeitor.isChecked()) {
                // esconder teclado
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                if (v == findViewById(R.id.ET_Serial))
                    it.putExtra("Campo", "Serial");
                else if (v == findViewById(R.id.ET_Mac))
                    it.putExtra("Campo", "Mac");
                else if (v == findViewById(R.id.ET_Imei))
                    it.putExtra("Campo", "Imei");
                else if (v == findViewById(R.id.ET_Iccid))
                    it.putExtra("Campo", "Iccid");
                else if (v == findViewById(R.id.ET_Cartao))
                    it.putExtra("Campo", "Cartao");

                setResult(1, it);
                scanIntegrator.initiateScan();
            }else if(!rbLeitor.isChecked()&&!focus){
                if (v == findViewById(R.id.ET_Serial))
                    chkData(etSerial.getText().toString());
                else if (v == findViewById(R.id.ET_Mac))
                    chkData(etMac.getText().toString());
                else if (v == findViewById(R.id.ET_Imei))
                    chkData(etImei.getText().toString());
                else if (v == findViewById(R.id.ET_Iccid))
                    chkData(etIccid.getText().toString());
                else if (v == findViewById(R.id.ET_Cartao))
                    chkData(etCartao.getText().toString());
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
//                                                   ((TextView) parent.getChildAt(0)).setTextSize(20);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (scanningResult != null) {
//             scanContent = scanningResult.getContents();
            if(it.getStringExtra("Campo").contentEquals("Serial"))
                etSerial.setText(scanningResult.getContents());
            else if(it.getStringExtra("Campo").contentEquals("Mac"))
                etMac.setText(scanningResult.getContents());
            else if(it.getStringExtra("Campo").contentEquals("Imei"))
                etImei.setText(scanningResult.getContents());
            else if(it.getStringExtra("Campo").contentEquals("Iccid"))
                etIccid.setText(scanningResult.getContents());
            else if(it.getStringExtra("Campo").contentEquals("Cartao"))
                etCartao.setText(scanningResult.getContents());
            chkData(scanningResult.getContents());
//            Toast.makeText(this," Codigo - "+it.getStringExtra("Campo"),Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }

    private void chkData(String content) {
        String res=null;

        Log.i("Codigo-",content);

        for(int c=4;c<5;c++){
            for(int r=0;r< Dados.Material.length;r++){
                if(content.contentEquals(Dados.Material[r][c]))
                    res="Existe";
            }
        }

        if(res==null)
            res="Não Existe";

        Toast.makeText(this, ""+res, Toast.LENGTH_SHORT).show();

    }

    private void gravar() {
        String msg=null;

        msg= " Material - "+etMaterial.getText();
        msg=msg+"\n Marca - "+etMarca.getText();
        msg=msg+"\n Modelo - "+etModelo.getText();
        msg=msg+"\n Serial - "+etSerial.getText();
        msg=msg+"\n Mac - "+etMac.getText();
        msg=msg+"\n Imei - "+etImei.getText();
        msg=msg+"\n Iccid - "+etIccid.getText();
        msg=msg+"\n Cartão - "+etCartao.getText();
        msg=msg+"\n Estado - "+spEstado.getSelectedItem();

        showMessage("Guardar",msg);
    }

    private void elimina() {
        String msg=null;

        msg= " Material - "+etMaterial.getText();
        msg=msg+"\n Marca - "+etMarca.getText();
        msg=msg+"\n Modelo - "+etModelo.getText();
        msg=msg+"\n Serial - "+etSerial.getText();
        msg=msg+"\n Mac - "+etMac.getText();
        msg=msg+"\n Imei - "+etImei.getText();
        msg=msg+"\n Iccid - "+etIccid.getText();
        msg=msg+"\n Cartão - "+etCartao.getText();
        msg=msg+"\n Estado - "+spEstado.getSelectedItem();

        showMessage("Eliminar",msg);
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


}

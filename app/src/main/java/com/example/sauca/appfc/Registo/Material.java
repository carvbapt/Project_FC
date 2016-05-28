package com.example.sauca.appfc.Registo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;


import com.example.sauca.appfc.R;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;
import com.example.sauca.appfc.zxing.android.IntentResult;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Material extends AppCompatActivity implements View.OnClickListener {

    private Intent it;
    private ImageButton ibtBack;
    private TextView tvOt;

    Spinner spEstado;
    ArrayAdapter spadapter;

    private RadioButton rbLeitor;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);

        it=getIntent();
        Toast.makeText(this,""+it.getStringExtra("otM")+" Material -"+ it.getStringExtra("Material"), Toast.LENGTH_SHORT).show();

        tvOt=(TextView)findViewById(R.id.TV_Ot);
        tvOt.setText(getIntent().getStringExtra("otM"));

        rbLeitor = (RadioButton)findViewById(R.id.RB_Leitor);

//        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.TV_Serial);

        spEstado=(Spinner)findViewById(R.id.SP_Estado);

        spadapter = ArrayAdapter.createFromResource (this,R.array.estado,android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spEstado.setAdapter(spadapter);

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
//                                                   ((TextView) parent.getChildAt(0)).setTextSize(20);
//                func.f_empresa = getResources().getStringArray(R.array.empresa)[position];
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });
        ibtBack.setOnClickListener(this);
        rbLeitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.BTI_Back)) {
            onBackPressed();
        }else if(v==findViewById(R.id.RB_Leitor)){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
//            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("" + scanContent);
        }
        else{
            Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.sauca.appfc.Registo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;


import com.example.sauca.appfc.R;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;
import com.example.sauca.appfc.zxing.android.IntentResult;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Material extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibtBack;
    private TextView tvOt;

    Spinner spEstado;
    ArrayAdapter spadapter;

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);

        tvOt=(TextView)findViewById(R.id.TV_Ot);
        tvOt.setText(getIntent().getStringExtra("otM"));

        scanBtn = (Button)findViewById(R.id.BT_Leitor);
//        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.TV_Serial);

        spEstado=(Spinner)findViewById(R.id.SP_Estado);

        spadapter = ArrayAdapter.createFromResource (this,R.array.estado,android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spEstado.setAdapter(spadapter);

        scanBtn.setOnClickListener(this);
        ibtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.BTI_Back)) {
            onBackPressed();
        }else if(v==findViewById(R.id.BT_Leitor)){
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
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

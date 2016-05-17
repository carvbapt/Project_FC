package com.example.sauca.appfc.Registo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sauca.appfc.Login.Login_List;
import com.example.sauca.appfc.Login.Login_Reg;
import com.example.sauca.appfc.R;

public class Registo extends AppCompatActivity implements View.OnClickListener{

    Intent it;
    Button btSign,btList;
    ImageButton ibtBack;
    private RadioGroup rgbtGen;
    RadioButton rbtFunc,rbtCar,rbtMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        btSign = (Button) findViewById(R.id.BT_Reg);
        btList = (Button) findViewById(R.id.BT_List);
        ibtBack = (ImageButton)findViewById(R.id.BT_Back);
        rgbtGen = (RadioGroup)findViewById(R.id.rgbt);
        rbtFunc =(RadioButton)findViewById(R.id.RBT_Func);
        rbtCar =(RadioButton)findViewById(R.id.RBT_Car);
        rbtMat =(RadioButton)findViewById(R.id.RBT_Mater);

        // Set Listeners
        btSign.setOnClickListener(this);
        btList.setOnClickListener(this);
        ibtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        it =null;

        if(v==findViewById(R.id.BT_Reg) && rbtFunc.isChecked()) {
            it = new Intent(this, Login_Reg.class);
        }else if(v==findViewById(R.id.BT_Reg) && rbtCar.isChecked()) {
            Toast.makeText(Registo.this, "ACTIVIDADE REGISTO CARRO", Toast.LENGTH_SHORT).show();
        }else if(v==findViewById(R.id.BT_Reg) && rbtMat.isChecked()){
            it = new Intent(this, Material.class);
        }else if(v==findViewById(R.id.BT_List) && rbtFunc.isChecked()) {
            it = new Intent(this, Login_List.class);
        }else if(v==findViewById(R.id.BT_List) && rbtCar.isChecked()) {
            Toast.makeText(Registo.this, "ACTIVIDADE LISTAGEM CARRO", Toast.LENGTH_SHORT).show();
        }else if(v==findViewById(R.id.BT_List) && rbtMat.isChecked()){
            Toast.makeText(this," ACTIVIDADE LISTAGEM MATERIAL",Toast.LENGTH_LONG).show();
        }else if(v==findViewById(R.id.BT_Back)){
            onBackPressed();
        }

        if(it != null)
            startActivity(it);
    }
}

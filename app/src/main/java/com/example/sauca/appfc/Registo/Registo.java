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

import com.example.sauca.appfc.Intervencao.Diario;
import com.example.sauca.appfc.Intervencao.Diario_List;
import com.example.sauca.appfc.Login.Login_List;
import com.example.sauca.appfc.Login.Login_Reg;
import com.example.sauca.appfc.R;

public class Registo extends AppCompatActivity implements View.OnClickListener{

    Intent it;
    Button btRegis,btList;
    ImageButton ibtBack;
    private RadioGroup rgbtGen;
    RadioButton rbtFunc,rbtJan,rbtMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        btRegis = (Button) findViewById(R.id.BT_Reg);
        btList = (Button) findViewById(R.id.BT_List);
        ibtBack = (ImageButton)findViewById(R.id.BT_Back);
        rgbtGen = (RadioGroup)findViewById(R.id.rgbt);
        rbtFunc =(RadioButton)findViewById(R.id.RBT_Func);
        rbtJan =(RadioButton)findViewById(R.id.RBT_Jane);
        rbtMat =(RadioButton)findViewById(R.id.RBT_Mater);

        if(rbtFunc.isChecked()||rbtJan.isChecked()){
            btRegis.setVisibility(View.GONE);
        }

        // Set Listeners
        rbtFunc.setOnClickListener(this);
        rbtJan.setOnClickListener(this);
        rbtMat.setOnClickListener(this);
        btRegis.setOnClickListener(this);
        btList.setOnClickListener(this);
        ibtBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        it =null;

        if(v==findViewById(R.id.BT_Back))
            onBackPressed();
        else if(rbtMat.isChecked()) {
            btRegis.setVisibility(View.VISIBLE);
            if(v==findViewById(R.id.BT_List))
                it = new Intent(this, Material_List.class);
//                Toast.makeText(this, " ACTIVIDADE LISTAGEM MATERIAL", Toast.LENGTH_LONG).show();
            else if (v==findViewById(R.id.BT_Reg)){
                it = new Intent(this, Material.class);
                it.putExtra("otM","");
            }
        }else{
            btRegis.setVisibility(View.GONE);
            if(rbtFunc.isChecked()){
                if(v==findViewById(R.id.BT_List))
                    it = new Intent(this, Login_List.class);
                else if(v==findViewById(R.id.BT_Reg))
                    it = new Intent(this, Login_Reg.class);
            }
            if(rbtJan.isChecked()) {
                if (v == findViewById(R.id.BT_List)) {
                    it = new Intent(this, Diario_List.class);
//                    it.putExtra("activity","registo");
                }else if(v==findViewById(R.id.BT_Reg))
                    it = new Intent(this, Login_Reg.class);
            }
        }

        if(it != null)
            startActivity(it);
    }
}

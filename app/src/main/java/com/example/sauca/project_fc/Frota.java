package com.example.sauca.project_fc;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauca.project_fc.Intervencao.Intervencao;
import com.example.sauca.project_fc.Login.Login;

import java.util.StringTokenizer;

public class Frota extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.example.sauca.project_fc.MESSAGE";
    StringTokenizer st;

    ImageButton ibtBack,ibtConf;
    EditText  etKms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frota);

        etKms= (EditText) findViewById(R.id.editText);
        etKms.setSelected(false);

        // Get the message from the intent
        Intent intent = getIntent();
        String  str = intent.getStringExtra(EXTRA_MESSAGE);
//        showMessage("DATA",str);
//        st= new StringTokenizer(msg,".@");
//        String s[]=new String[2];
////        s[]=msg.split(".|@");
//        s[0]=st.nextToken();
//        char[] stringArray = s[0].trim().toCharArray();
//        stringArray[0] = Character.toUpperCase(stringArray[0]);
//        s[0]=new String(stringArray);
//        s[1]=st.nextToken();
//        stringArray = s[1].trim().toCharArray();
//        stringArray[0] = Character.toUpperCase(stringArray[0]);
//        s[1]=new String(stringArray);
//        Log.i("Frota",""+msg);
//        Log.i("Frota", "USER-" + s[0]+" "+s[1]);
//        txt.setText(s[0]+" "+s[1]);

        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtConf=(ImageButton)findViewById(R.id.BTI_Conf);

        ibtBack.setOnClickListener(this);
        ibtConf.setOnClickListener(this);

        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.BTI_Back))
            onBackPressed();
//            startActivity(new Intent(this, Login.class));
        else if (v == findViewById(R.id.BTI_Conf))
            startActivity(new Intent(this, Configurar.class));
    }
}

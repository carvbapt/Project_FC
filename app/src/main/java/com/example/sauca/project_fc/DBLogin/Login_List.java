package com.example.sauca.project_fc.DBLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

public class Login_List extends AppCompatActivity {

    DataBaseLogin myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__list);

        myDB= new DataBaseLogin(this);

        Toast.makeText(this, "LISTAR DB", Toast.LENGTH_LONG).show();
    }
}

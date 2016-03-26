package com.example.sauca.project_fc.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.sauca.project_fc.DB.Model.Funcionario;
import com.example.sauca.project_fc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.project_fc.Menu;
import com.example.sauca.project_fc.R;
import com.example.sauca.project_fc.Registo;
import com.example.sauca.project_fc.Splash;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements OnClickListener {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    // S@C
    Button btLogin,btQuit;
    ImageButton  ibtBack,ibtConf;
    Funcionario func;
    FuncionarioRepo myDB;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDB=new FuncionarioRepo(this);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.BT_Login);
        btQuit=(Button)findViewById(R.id.BT_Quit);
        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtConf=(ImageButton)findViewById(R.id.BTI_Regist);

        btLogin.setOnClickListener(this);
        btQuit.setOnClickListener(this);
        ibtBack.setOnClickListener(this);
        ibtConf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==findViewById(R.id.BT_Login)) {
            if (attemptLogin()) {
                startActivity(new Intent(this, Menu.class));
//                final ProgressDialog dialog = new ProgressDialog(this);
//                dialog.setMessage("Loading...");
//                dialog.show();
                mPasswordView.setText("");
                mEmailView.setText("");
                mEmailView.requestFocus();
            }
        }else if(v==findViewById(R.id.BT_Quit)) {
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }else if(v==findViewById(R.id.BTI_Back)){
            finish();
            startActivity(new Intent(this, Splash.class));
        }else if(v==findViewById(R.id.BTI_Regist)){
            startActivity(new Intent(this, Registo.class));
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public Boolean attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            mPasswordView.setText("");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            mEmailView.setText("");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            func= myDB.searchDataSingle("EMAIL",email);

            if(func.f_email != null && func.f_email.intern() == email.intern()  ){
                // Account exists, return true if the password matches.
                if (func.f_password.intern()!=null && func.f_password.intern()==password.intern())
                    return true;
                else {
                    Toast.makeText(this,getString(R.string.error_password), Toast.LENGTH_LONG).show();
                    mPasswordView.setText("");
                    focusView=mPasswordView;
                    focusView.requestFocus();
                }
            }else {
                Toast.makeText(this,getString(R.string.error_user) , Toast.LENGTH_LONG).show();
                mEmailView.setText("");
                mPasswordView.setText("");
                focusView=mEmailView;
                focusView.requestFocus();
            }
        }
        return false;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}


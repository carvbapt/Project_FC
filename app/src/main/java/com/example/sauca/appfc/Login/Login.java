package com.example.sauca.appfc.Login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sauca.appfc.Configurar;
import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Funcionario;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.DB.RepoQuery.FuncionarioRepo;
import com.example.sauca.appfc.DB.RepoQuery.MateriaRepo;
import com.example.sauca.appfc.MainMenu;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.Registo.Registo;
import com.example.sauca.appfc.Splash;

import android.view.inputmethod.InputMethodManager;


/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements OnClickListener {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    // S@C
    Button btLogin,btQuit,btLoad;
    ImageButton  ibtBack,ibtConf;
    Funcionario func;
    FuncionarioRepo myDB;
    Intent it;
    public final static String EXTRA_MESSAGE = "com.example.sauca.project_fc.MESSAGE";

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
        ibtConf=(ImageButton)findViewById(R.id.BTI_Conf);

        btLoad=(Button)findViewById(R.id.BT_Load);

        mEmailView.setText("sandro.carvalho@fastcall.pt");
        mPasswordView.setText("sandro");

        btLogin.setOnClickListener(this);
        btQuit.setOnClickListener(this);
        ibtBack.setOnClickListener(this);
        ibtConf.setOnClickListener(this);

        if(this.getDatabasePath("DBFastcall.db").exists())
            btLoad.setVisibility(View.INVISIBLE);
        else
            btLoad.setOnClickListener(this);

        Log.i("DTBASE",""+getBaseContext().getDatabasePath("DBFastcall.db").exists());


        // esconder teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        if(v==findViewById(R.id.BT_Login)) {
            if (attemptLogin()) {
                it=new Intent(this,Login.class);
                it.putExtra("EXTRA_MESSAGE",mEmailView.toString());
                startActivity(new Intent(this, MainMenu.class));
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
        }else if(v==findViewById(R.id.BTI_Conf)){
            startActivity(new Intent(this, Configurar.class));
//            startActivity(new Intent(this, Registo.class));
        }else if(v==findViewById(R.id.BT_Load))
            loadData();
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

    boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    // Carrega dados a partir de String´s Array
    private void loadData() {

        FuncionarioRepo repofunc;
        Funcionario func;
        DiarioRepo repodia;
        Diaria diar;
        MateriaRepo repomat;
        Materia mate;
        int isInserted =0;
        int count=0;
        String str=null;

        func=new Funcionario();
        repofunc= new FuncionarioRepo(this);
        diar=new Diaria();
        repodia=new DiarioRepo(this);
        mate=new Materia();
        repomat= new MateriaRepo(this);

        for(int r=0;r<Dados.Utilizador.length;r++) {
            func.f_nome = Dados.Utilizador[r][1];
            func.f_apelido = Dados.Utilizador[r][2];
            func.f_password = Dados.Utilizador[r][3];
            func.f_email = Dados.Utilizador[r][4];
            func.f_empresa=Dados.Utilizador[r][5];
            isInserted=repofunc.insert(func);
            count=r+1;
//            Log.i("IN- ","\n"+isInserted +" "+func.f_nome);
        }

        if (isInserted>0) {
            str = "Users-"+count;
//            Toast.makeText(Login.this, "Sucesso de " + count + " utilizadores", Toast.LENGTH_LONG).show();
        }else
            showMessage("Base de Dados", "Users Não Gravados");

        isInserted=0;

        for(int r=0;r<Dados.Janela.length;r++) {
            diar.d_ot = Dados.Ordens[r];
            diar.d_empresa=Dados.Janela[r][0];
            diar.d_data= Dados.Janela[r][1];
            diar.d_hora = Dados.Janela[r][2];
            diar.d_estado = Dados.Janela[r][3];
            diar.d_inicio=Dados.Janela[r][4];
            diar.d_fim=Dados.Janela[r][5];
            diar.d_tarefa=Dados.Janela[r][6];
            isInserted=repodia.insert(diar);
            count=r+1;
            Log.i("IND- "," Dados "+ diar.d_ot+" " +diar.d_estado+ " "+diar.d_data + " "+Dados.Ordens[Integer.parseInt(diar.d_empresa)]+" "+diar.d_tarefa);
        }

        if (isInserted>0) {
            str=str+" Ot´s-"+count;
//            Toast.makeText(Login.this, "Sucesso de " + count + " OT", Toast.LENGTH_LONG).show();
            btLoad.setEnabled(false);
            btLoad.setVisibility(View.INVISIBLE);
        }
        else
            showMessage("Base de Dados", "Janelas Não Gravadas");

        isInserted=0;

        for(int r=0;r<Dados.Material.length;r++) {
            if(Dados.Material[r][0]=="")
                mate.m_ot=Dados.Material[r][0];
            else
                mate.m_ot = Dados.Ordens[r];
            mate.m_material= Dados.Material[r][1];
            mate.m_marca= Dados.Material[r][2];
            mate.m_modelo = Dados.Material[r][3];
            mate.m_serial=Dados.Material[r][4];
            mate.m_mac=Dados.Material[r][5];
            mate.m_imei=Dados.Material[r][6];
            mate.m_iccid=Dados.Material[r][7];
            mate.m_cartao=Dados.Material[r][8];
            mate.m_estado=Dados.Material[r][9];

            isInserted=repomat.insert(mate);
            count=r+1;
            Log.i("IND- "," Material "+ mate.m_material+" "+mate.m_ot +" "+mate.m_estado);
        }

        if (isInserted>0) {
            str=str+" Materiais-"+count;
//            Toast.makeText(Login.this, "Sucesso de " + count + " Materiais", Toast.LENGTH_LONG).show();
            btLoad.setEnabled(false);
            btLoad.setVisibility(View.INVISIBLE);
        }
        else
            showMessage("Base de Dados", "Materiais Não Gravados");

        if(str.contains("materiais"))
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
    /*********************************************************************************************************************************************************************
     FUNÇÕES AUXILIARES
     /********************************************************************************************************************************************************************/

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("OK", null);
        builder.show();
    }
}


package com.example.sauca.appfc.DB.Model;

/**
 * Created by Sauca on 16-03-2016.
 */
public class Funcionario {

    // Table Name
    public static final String TABLE="Funcionario";

    // Columns Labels
    public static final String COL_CAMP1="ID";
    public static final String COL_CAMP2="NOME";
    public static final String COL_CAMP3="APELIDO";
    public static final String COL_CAMP4="PASSWORD";
    public static final String COL_CAMP5="EMAIL";
    public static final String COL_CAMP6="EMPRESA";

    // Property Data
    public int f_id;
    public String f_nome;
    public String f_apelido;
    public String f_password;
    public String f_email;
    public String f_empresa;

    public static String createTable(){
        return "CREATE TABLE " + Funcionario.TABLE  + "("
                + Funcionario.COL_CAMP1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Funcionario.COL_CAMP2 + " TEXT, " + Funcionario.COL_CAMP3 + " TEXT, " + Funcionario.COL_CAMP4 + " TEXT, "
                + Funcionario.COL_CAMP5 + " TEXT, " + Funcionario.COL_CAMP6 + " TEXT )";
    }

    // Property Data for Adapter Provider
    public int emp_logo;

    // Adapter Provider

    public Funcionario() {
    }

    public Funcionario(int emp_logo, int f_id, String f_nome, String f_apelido) {
        this.emp_logo = emp_logo;
        this.f_id = f_id;
        this.f_nome = f_nome;
        this.f_apelido = f_apelido;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_nome() {
        return f_nome;
    }

    public void setF_nome(String f_nome) {
        this.f_nome = f_nome;
    }

    public String getF_apelido() {
        return f_apelido;
    }

    public void setF_apelido(String f_apelido) {
        this.f_apelido = f_apelido;
    }

    public String getF_password() {
        return f_password;
    }

    public void setF_password(String f_password) {
        this.f_password = f_password;
    }

    public String getF_email() {
        return f_email;
    }

    public void setF_email(String f_email) {
        this.f_email = f_email;
    }

    public String getF_empresa() {
        return f_empresa;
    }

    public void setF_empresa(String f_empresa) {
        this.f_empresa = f_empresa;
    }

    public int getEmp_logo() {
        return emp_logo;
    }

    public void setEmp_logo(int emp_logo) {
        this.emp_logo = emp_logo;
    }
}

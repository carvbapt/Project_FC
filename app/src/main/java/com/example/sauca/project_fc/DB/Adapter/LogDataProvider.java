package com.example.sauca.project_fc.DB.Adapter;

/**
 * Created by Sauca on 15-03-2016.
 */
public class LogDataProvider {
    private int emp_logo;
    private String emp_id;
    private String emp_nome;
    private String emp_apelido;

    public LogDataProvider(int emp_logo,String emp_id, String emp_nome, String emp_apelido) {
        this.setEmp_logo(emp_logo);
        this.setEmp_id(emp_id);
        this.setEmp_nome(emp_nome);
        this.setEmp_apelido(emp_apelido);
    }

    public int getEmp_logo() {
        return emp_logo;
    }

    public void setEmp_logo(int emp_logo) {
        this.emp_logo = emp_logo;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_nome() {
        return emp_nome;
    }

    public void setEmp_nome(String emp_nome) {
        this.emp_nome = emp_nome;
    }

    public String getEmp_apelido() {
        return emp_apelido;
    }

    public void setEmp_apelido(String emp_apelido) {
        this.emp_apelido = emp_apelido;
    }
}

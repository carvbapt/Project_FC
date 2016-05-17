package com.example.sauca.appfc.DB.Model;

/**
 * Created by Sauca on 10-05-2016.
 */
public class Diaria {

    // Table Name
    public static final String TABLE="Diario";

    // Columns Labels
    public static final String DIA_EMP1="ID";
    public static final String DIA_EMP2="OT";
    public static final String DIA_EMP3="DATA";
    public static final String DIA_EMP4="HORA";
    public static final String DIA_EMP5="ESTADO";
    public static final String DIA_EMP6="INICIO";
    public static final String DIA_EMP7="FIM";
    public static final String DIA_EMP8="EMPRESA"        ;

    // Property Data
    public int d_id;
    public String d_ot;
    public String d_data;
    public String d_hora;
    public String d_estado;
    public String d_inicio;
    public String d_fim;
    public String d_empresa;

    public static String createTable(){
        return "CREATE TABLE " + Diaria.TABLE  + "("
                + Diaria.DIA_EMP1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Diaria.DIA_EMP2 + " TEXT, " + Diaria.DIA_EMP3 + " TEXT, " + Diaria.DIA_EMP4 + " TEXT, "
                + Diaria.DIA_EMP5 + " TEXT, " + Diaria.DIA_EMP6 + " TEXT, " + Diaria.DIA_EMP7 + " TEXT, " + Diaria.DIA_EMP8 + " TEXT )";
    }

    public Diaria() {
    }

    public Diaria(String d_ot, String d_hora, String d_estado) {
        this.d_ot=d_ot;
        this.d_hora=d_hora;
        this.d_estado=d_estado;
    }

    public Diaria(String d_ot, String d_data, String d_hora, String d_estado, String d_inicio, String d_fim, String d_empresa) {
        this.d_ot = d_ot;
        this.d_data=d_data;
        this.d_hora = d_hora;
        this.d_estado = d_estado;
        this.d_inicio =d_inicio;
        this.d_fim = d_fim;
        this.d_empresa=d_empresa;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_ot() {
        return d_ot;
    }

    public void setD_ot(String d_ot) {
        this.d_ot = d_ot;
    }

    public String getD_data() {
        return d_data;
    }

    public void setD_data(String d_data) {
        this.d_data = d_data;
    }

    public String getD_hora() {
        return d_hora;
    }

    public void setD_hora(String d_hora) {
        this.d_hora = d_hora;
    }

    public String getD_estado() {
        return d_estado;
    }

    public void setD_estado(String d_estado) {
        this.d_estado = d_estado;
    }

    public String getD_inicio() {
        return d_inicio;
    }

    public void setD_inicio(String d_inicio) {
        this.d_inicio = d_inicio;
    }

    public String getD_fim() {
        return d_fim;
    }

    public void setD_fim(String d_fim) {
        this.d_fim = d_fim;
    }

    public String getD_empresa() { return d_empresa; }

    public void setD_empresa(String d_empresa) {
        this.d_empresa = d_empresa;
    }


}

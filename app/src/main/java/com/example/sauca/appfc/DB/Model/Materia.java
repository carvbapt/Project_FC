package com.example.sauca.appfc.DB.Model;

import com.example.sauca.appfc.Registo.Material;

/**
 * Created by Sauca on 24-05-2016.
 */
public class Materia {

    // Table Name
    public static final String TABLE="Materiais";

    // Columns Labels
    public static final String MAT_CAMP1="ID";
    public static final String MAT_CAMP2="OT";
    public static final String MAT_CAMP3="MATERIAL";
    public static final String MAT_CAMP4="MARCA";
    public static final String MAT_CAMP5="MODELO";
    public static final String MAT_CAMP6="SERIAL";
    public static final String MAT_CAMP7="MAC";
    public static final String MAT_CAMP8="IMEI";
    public static final String MAT_CAMP9="ICCID";
    public static final String MAT_CAMP10="CARTAO";
    public static final String MAT_CAMP11="ESTADO";

    // Property Data
    public int m_id;
    public String m_ot;
    public String m_material;
    public String m_marca;
    public String m_modelo;
    public String m_serial;
    public String m_mac;
    public String m_imei;
    public String m_iccid;
    public String m_cartao;
    public String m_estado;

    public static String createTable(){
        return "CREATE TABLE " + Materia.TABLE  + "("
                + Materia.MAT_CAMP1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Materia.MAT_CAMP2 + " TEXT, " + Materia.MAT_CAMP3 + " TEXT, " + Materia.MAT_CAMP4 + " TEXT, "
                + Materia.MAT_CAMP5 + " TEXT, " + Materia.MAT_CAMP6 + " TEXT, " + Materia.MAT_CAMP7 + " TEXT, "
                + Materia.MAT_CAMP8 + " TEXT, " + Materia.MAT_CAMP9 + " TEXT, " + Materia.MAT_CAMP10 + " TEXT, "
                + Materia.MAT_CAMP11 + " TEXT )";
    }

    public Materia() {
    }

    public Materia(String m_material, String m_modelo, String m_estado) {
        this.m_material = m_material;
        this.m_modelo = m_modelo;
        this.m_estado = m_estado;
    }

    public Materia(int m_id, String m_material, String m_modelo, String m_estado) {
        this.m_id = m_id;
        this.m_material = m_material;
        this.m_modelo = m_modelo;
        this.m_estado = m_estado;
    }

    public Materia(String m_ot, String m_material, String m_marca, String m_modelo, String m_serial, String m_mac, String m_imei, String m_iccid, String m_cartao, String m_estado) {
        this.m_ot = m_ot;
        this.m_material = m_material;
        this.m_marca = m_marca;
        this.m_modelo = m_modelo;
        this.m_serial = m_serial;
        this.m_mac = m_mac;
        this.m_imei = m_imei;
        this.m_iccid = m_iccid;
        this.m_cartao = m_cartao;
        this.m_estado = m_estado;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_ot() {
        return m_ot;
    }

    public void setM_ot(String m_ot) {
        this.m_ot = m_ot;
    }

    public String getM_material() {
        return m_material;
    }

    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    public String getM_marca() {
        return m_marca;
    }

    public void setM_marca(String m_marca) {
        this.m_marca = m_marca;
    }

    public String getM_modelo() {
        return m_modelo;
    }

    public void setM_modelo(String m_modelo) {
        this.m_modelo = m_modelo;
    }

    public String getM_serial() {
        return m_serial;
    }

    public void setM_serial(String m_serial) {
        this.m_serial = m_serial;
    }

    public String getM_mac() {
        return m_mac;
    }

    public void setM_mac(String m_mac) {
        this.m_mac = m_mac;
    }

    public String getM_imei() {
        return m_imei;
    }

    public void setM_imei(String m_imei) {
        this.m_imei = m_imei;
    }

    public String getM_iccid() {
        return m_iccid;
    }

    public void setM_iccid(String m_iccid) {
        this.m_iccid = m_iccid;
    }

    public String getM_cartao() {
        return m_cartao;
    }

    public void setM_cartao(String m_cartao) {
        this.m_cartao = m_cartao;
    }

    public String getM_estado() {
        return m_estado;
    }

    public void setM_estado(String m_estado) {
        this.m_estado = m_estado;
    }
}


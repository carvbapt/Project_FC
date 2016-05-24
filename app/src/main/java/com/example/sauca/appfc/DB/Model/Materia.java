package com.example.sauca.appfc.DB.Model;

/**
 * Created by Sauca on 24-05-2016.
 */
public class Materia {

    public String m_material;
    public String m_modelo;
    public String m_estado;

    public Materia() {
    }

    public Materia(String m_material, String m_modelo, String m_estado) {
        this.m_material = m_material;
        this.m_modelo = m_modelo;
        this.m_estado = m_estado;
    }

    public String getM_material() {
        return m_material;
    }

    public void setM_material(String m_material) {
        this.m_material = m_material;
    }

    public String getM_modelo() {
        return m_modelo;
    }

    public void setM_modelo(String m_modelo) {
        this.m_modelo = m_modelo;
    }

    public String getM_estado() {
        return m_estado;
    }

    public void setM_estado(String m_estado) {
        this.m_estado = m_estado;
    }
}


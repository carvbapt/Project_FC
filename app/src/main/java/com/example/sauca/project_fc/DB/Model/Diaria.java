package com.example.sauca.project_fc.DB.Model;

/**
 * Created by Sauca on 10-05-2016.
 */
public class Diaria {

    private String ot;
    private String hora;
    private String estado;

    public Diaria(String ot, String hora, String estado) {
        this.ot=ot;
        this.hora=hora;
        this.estado=estado;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

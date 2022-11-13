package com.example.ejercicio4repasoexamen.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable{
    private String calle;
    private int numero;
    private String provincia;
    private float valoracion;

    public Inmueble(String calle, int numero, String provincia, float valoracion) {
        this.calle = calle;
        this.numero = numero;
        this.provincia = provincia;
        this.valoracion = valoracion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}

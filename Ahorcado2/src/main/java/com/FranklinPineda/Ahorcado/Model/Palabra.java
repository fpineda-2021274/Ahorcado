package com.FranklinPineda.Ahorcado.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "pista1")
    private String pista1;

    @Column(name = "pista2")
    private String pista2;

    @Column(name = "pista3")
    private String pista3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista1() {
        return pista1;
    }

    public void setPista1(String pista1) {
        this.pista1 = pista1;
    }

    public String getPista2() {
        return pista2;
    }

    public void setPista2(String pista2) {
        this.pista2 = pista2;
    }

    public String getPista3() {
        return pista3;
    }

    public void setPista3(String pista3) {
        this.pista3 = pista3;
    }
}

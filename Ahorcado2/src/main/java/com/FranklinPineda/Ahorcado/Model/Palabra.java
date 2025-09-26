package com.FranklinPineda.Ahorcado.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Palabra")
public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_Palabra")
    private Integer codigoPalabra;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "pista_1")
    private String pista1;

    @Column(name = "pista_2")
    private String pista2;

    @Column(name = "pista_3")
    private String pista3;

    // GETTERS AND SETTERS

    public Integer getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(Integer codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
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
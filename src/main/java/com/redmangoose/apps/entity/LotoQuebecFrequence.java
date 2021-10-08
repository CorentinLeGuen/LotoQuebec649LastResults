package com.redmangoose.apps.entity;

public class LotoQuebecFrequence {
    private final String numero;
    private final String frequence;

    public LotoQuebecFrequence(String numero, String frequence) {
        this.numero = numero;
        this.frequence = frequence;
    }

    public String getNumero() {
        return numero;
    }

    public String getFrequence() {
        return frequence;
    }
}

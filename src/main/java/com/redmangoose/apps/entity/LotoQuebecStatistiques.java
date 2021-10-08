package com.redmangoose.apps.entity;

import java.util.LinkedList;
import java.util.List;

public class LotoQuebecStatistiques implements LotoQuebecObject {
    private final String tirages;
    private final String date_debut;
    private final String date_fin;
    private final List<LotoQuebecFrequence> numeros;

    public LotoQuebecStatistiques(String tirages, String date_debut, String date_fin) {
        this.tirages = tirages;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.numeros = new LinkedList<>();
    }

    public String getTirages() {
        return tirages;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void addLotoQuebecFrequence(LotoQuebecFrequence frequence) {
        this.numeros.add(frequence);
    }

    public List<LotoQuebecFrequence> getNumeros() {
        return numeros;
    }
}

package com.redmangoose.apps.entity.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redmangoose.apps.entity.LotoQuebecObject;

import java.util.LinkedList;
import java.util.List;

public class LotoQuebecFrequencesStats implements LotoQuebecObject {
    private final String tirages;
    @JsonProperty("date_debut")
    private final String dateDebut;
    @JsonProperty("date_fin")
    private final String dateFin;
    private final List<LotoQuebecFrequence> numeros;

    public LotoQuebecFrequencesStats(String tirages, String dateDebut, String dateFin) {
        this.tirages = tirages;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numeros = new LinkedList<>();
    }

    public String getTirages() {
        return tirages;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void addLotoQuebecFrequence(LotoQuebecFrequence frequence) {
        this.numeros.add(frequence);
    }

    public List<LotoQuebecFrequence> getNumeros() {
        return numeros;
    }
}

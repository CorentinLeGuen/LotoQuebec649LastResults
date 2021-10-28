package com.redmangoose.apps.lottery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Statistics {
    private final String tirages;
    @JsonProperty("date_debut")
    private final String dateDebut;
    @JsonProperty("date_fin")
    private final String dateFin;
    private final List<LotteryObject> numeros;

    public Statistics(String dateDebut, String dateFin, List<LotteryObject> liste) {
        this.tirages = String.valueOf(liste.size());
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numeros = liste;
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

    public List<LotteryObject> getNumeros() {
        return numeros;
    }
}

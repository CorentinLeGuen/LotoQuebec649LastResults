package com.redmangoose.apps.entity.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;

import java.util.List;

public class LotoQuebecTiragesStats {
    @JsonProperty("nombre_tirages")
    private final int nombreTirages;
    @JsonProperty("date_debut")
    private final String dateDebut;
    @JsonProperty("date_fin")
    private final String dateFin;
    private final List<LotoQuebecTirage> tirages;

    public LotoQuebecTiragesStats(String dateDebut, String dateFin, Iterable<LotoQuebecTirage> tirages) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tirages = (List<LotoQuebecTirage>) tirages;
        this.nombreTirages = this.tirages.size();
    }

    public int getNombreTirages() {
        return nombreTirages;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public List<LotoQuebecTirage> getTirages() {
        return tirages;
    }
}

package com.redmangoose.apps.entity.stats;

import com.redmangoose.apps.entity.last_draw.LotoQuebecTirage;

import java.util.List;

public class LotoQuebecTiragesStats {
    private final int nombre_tirages;
    private final String date_debut;
    private final String date_fin;
    private final List<LotoQuebecTirage> tirages;

    public LotoQuebecTiragesStats(String date_debut, String date_fin, Iterable<LotoQuebecTirage> tirages) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tirages = (List<LotoQuebecTirage>) tirages;
        this.nombre_tirages = this.tirages.size();
    }

    public int getNombre_tirages() {
        return nombre_tirages;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public List<LotoQuebecTirage> getTirages() {
        return tirages;
    }
}

package com.redmangoose.apps.util;

public enum LotoQuebecURLs {
    LOTO_QUEBEC_LAST_RESULTS_URL("https://loteries.lotoquebec.com/fr/resultats"),
    LOTO_QUEBEC_STATS_URL("https://loteries.lotoquebec.com/fr/loteries/lotto-6-49?outil=statistiques-212#lqTableauStatsAVie"),
    LOTO_QUEBEC_RESULTS_URL("https://loteries.lotoquebec.com/fr/loteries/lotto-6-49?annee=%s&widget=resultats-anterieurs&noProduit=212#res");

    private final String url;

    LotoQuebecURLs (final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}

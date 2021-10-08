package com.redmangoose.apps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.redmangoose.apps.entity.LotoQuebecFrequence;
import com.redmangoose.apps.entity.LotoQuebecStatistiques;
import com.redmangoose.apps.entity.LotoQuebecTirage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LotoClientService {
    private final Logger log = LoggerFactory.getLogger(LotoClientService.class);

    private final String LOTO_QUEBEC_LAST_RESULTS_URL = "https://loteries.lotoquebec.com/fr/resultats";
    private final String LOTO_QUEBEC_STATS_URL = "https://loteries.lotoquebec.com/fr/loteries/lotto-6-49?outil=statistiques-212#lqTableauStatsAVie";

    private final String DATE_TIME_FORMAT_PATTERN = "dd MMM yyyy";
    private final String ISO_LOCAL_DATE = "yyyy-MM-dd";

    public LotoQuebecTirage getLastLotoResults() {
        try {
            log.debug("Start webclient");
            final WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LOTO_QUEBEC_LAST_RESULTS_URL);
            log.debug("Retreive XPaths");
            final String dateTirage = ((HtmlDivision) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div[2]/div/div").get(0)).getFirstChild().getNodeValue();
            final String number1 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[1]").get(0)).getFirstChild().getNodeValue();
            final String number2 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[3]").get(0)).getFirstChild().getNodeValue();
            final String number3 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[5]").get(0)).getFirstChild().getNodeValue();
            final String number4 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[7]").get(0)).getFirstChild().getNodeValue();
            final String number5 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[9]").get(0)).getFirstChild().getNodeValue();
            final String number6 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[11]").get(0)).getFirstChild().getNodeValue();
            final String numberComp = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[13]").get(0)).getFirstChild().getNodeValue();

            String date = dateTirage; // We set the date like this just in case the conversion fail

            try {
                log.debug("Format date before conversion");
                String datePreFormatted = dateTirage.split(" ")[0] + " " + dateTirage.split(" ")[1].substring(0, 3) + ". " + dateTirage.split(" ")[2];
                log.debug("Convert date");
                date = new SimpleDateFormat(ISO_LOCAL_DATE).format(new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN, Locale.FRENCH).parse(datePreFormatted));

            } catch (IndexOutOfBoundsException exception) {
                log.error("Error while splitting date : '{}'.", date);
                log.error(exception.getMessage());
            } catch (ParseException exception) {
                log.error("Error while parsing the date : '{}'", date);
                log.error(exception.getMessage());
            }

            final LotoQuebecTirage tirage = new LotoQuebecTirage(date, number1, number2, number3, number4, number5, number6, numberComp);

            client.close();
            log.debug("Close webclient");
            return tirage;
        } catch (Exception exception) {
            log.error("An error occurred while retrieving loto draw : '{}'", exception.getMessage());
            return null;
        }
    }

    public LotoQuebecStatistiques getLastLotoStatistics() {
        try {
            log.debug("Start webclient");
            final WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LOTO_QUEBEC_STATS_URL);
            log.debug("Retreive XPaths");
            final String secondLineText = page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/thead/tr[1]/th/p/text()[2]").get(0).toString();
            final String thirdLineText = page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/thead/tr[1]/th/p/text()[3]").get(0).toString();

            final String tirages = secondLineText.replaceAll("Depuis le début :", "").replaceAll("tirages", "").replaceAll(" ", "");
            try {
                Integer.parseInt(tirages);
            } catch (NumberFormatException exception) {
                log.error("Extracted value isn't an integer : '{}'", exception.getMessage());
            }
            final String date_debut = thirdLineText.split(" ")[1];
            final String date_fin = thirdLineText.split(" ")[3];

            final LotoQuebecStatistiques statistiques = new LotoQuebecStatistiques(tirages, date_debut, date_fin);

            for (int i = 1; i <= 49; i++) {
                HtmlTableRow row = (HtmlTableRow)page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/tbody/tr[" + i + "]").get(0);
                statistiques.addLotoQuebecFrequence(new LotoQuebecFrequence(row.getCell(0).getFirstChild().getTextContent(), row.getCell(1).getFirstChild().getTextContent()));
            }

            client.close();
            log.debug("Close webclient");
            return statistiques;
        } catch (Exception exception) {
            log.error("An error occurred while retrieving loto stats : '{}'", exception.getMessage());
            return null;
        }
    }

    public String getLotoQuebecLastResultsURL() {
        return LOTO_QUEBEC_LAST_RESULTS_URL;
    }

    public String getLotoQuebecStatsURL() {
        return LOTO_QUEBEC_STATS_URL;
    }
}

package com.redmangoose.apps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.redmangoose.apps.entity.LotoQuebecTirage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LotoClientService {
    private final Logger log = LoggerFactory.getLogger(LotoClientService.class);

    private final String LOTO_QUEBEC_URL = "https://loteries.lotoquebec.com/fr/resultats";

    private final String DATE_TIME_FORMAT_PATTERN = "dd MMM yyyy";
    private final String ISO_LOCAL_DATE = "yyyy-MM-dd";

    public LotoQuebecTirage getLastLotoResults() {
        try {
            log.debug("Start webclient");
            final WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LOTO_QUEBEC_URL);
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
        } catch (IOException exception) {
            log.error("An error occurred while retrieving loto draw : " + exception.getMessage());
            return null;
        }
    }

    public String getLotoQuebecURL() {
        return LOTO_QUEBEC_URL;
    }
}

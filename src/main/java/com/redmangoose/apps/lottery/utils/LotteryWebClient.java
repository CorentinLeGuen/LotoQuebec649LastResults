package com.redmangoose.apps.lottery.utils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.redmangoose.apps.lottery.entity.Draw;
import com.redmangoose.apps.lottery.entity.Frequency;
import com.redmangoose.apps.lottery.entity.LotteryObject;
import com.redmangoose.apps.lottery.entity.Statistics;
import com.redmangoose.apps.lottery.exception.BadBindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class LotteryWebClient {
    private final Logger log = LoggerFactory.getLogger(LotteryWebClient.class);

    private static final String DATE_TIME_FORMAT_PATTERN = "dd MMM yyyy";
    private static final String ISO_LOCAL_DATE = "yyyy-MM-dd";

    public Draw getLastLotoResults() {
        try (WebClient client = new WebClient()) {
            log.debug("Start webclient");
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LotteryURLs.LOTO_QUEBEC_LAST_RESULTS_URL.toString());
            log.debug("Retreive XPaths");
            final String dateTirage = ((HtmlDivision) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div[2]/div/div").get(0)).getFirstChild().getNodeValue();
            final String number1 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[1]").get(0)).getFirstChild().getNodeValue();
            final String number2 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[3]").get(0)).getFirstChild().getNodeValue();
            final String number3 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[5]").get(0)).getFirstChild().getNodeValue();
            final String number4 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[7]").get(0)).getFirstChild().getNodeValue();
            final String number5 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[9]").get(0)).getFirstChild().getNodeValue();
            final String number6 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[11]").get(0)).getFirstChild().getNodeValue();
            final String numberComp = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[13]").get(0)).getFirstChild().getNodeValue();

            String date = convertingStringFrenchDateToSimpleDate(dateTirage); // We set the date like this just in case the conversion fail
            if (date == null) {
                throw new BadBindingException();
            }

            final Draw tirage = new Draw();
            tirage.setDateTirage(date);
            tirage.setNumero1(number1);
            tirage.setNumero2(number2);
            tirage.setNumero3(number3);
            tirage.setNumero4(number4);
            tirage.setNumero5(number5);
            tirage.setNumero6(number6);
            tirage.setNumeroComplementaire(numberComp);

            log.debug("Close webclient");
            return tirage;
        } catch (Exception exception) {
            log.error("An error occurred while retrieving loto draw : '{}'", exception.getMessage());
            return null;
        }
    }

    public Statistics getLastLotoStatistics() {
        try (WebClient client = new WebClient()) {
            log.debug("Start webclient");
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LotteryURLs.LOTO_QUEBEC_STATS_URL.toString());
            log.debug("Retreive XPaths");
            final String secondLineText = page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/thead/tr[1]/th/p/text()[2]").get(0).toString();
            final String thirdLineText = page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/thead/tr[1]/th/p/text()[3]").get(0).toString();

            final String tirages = secondLineText
                    .replace("Depuis le début :", "")
                    .replace("tirages", "")
                    .replace(" ", "");

            checkIfIsAnInteger(tirages);

            final String date_debut = thirdLineText.split(" ")[1];
            final String date_fin = thirdLineText.split(" ")[3];

            List<LotteryObject> frequencies = new LinkedList<>();
            for (int i = 1; i <= 49; i++) {
                HtmlTableRow row = (HtmlTableRow) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[4]/div/div/div[2]/div/div[2]/div/div[3]/div[1]/table/tbody/tr[" + i + "]").get(0);
                frequencies.add(new Frequency(row.getCell(0).getFirstChild().getTextContent(), row.getCell(1).getFirstChild().getTextContent()));
            }
            return new Statistics(date_debut, date_fin, frequencies);
        } catch (Exception exception) {
            log.error("An error occurred while retrieving loto stats : '{}'", exception.getMessage());
            return null;
        } finally {
            log.debug("Close webclient");
        }
    }

    private String convertingStringFrenchDateToSimpleDate(final String dateTirage) {
        try {
            log.debug("Format date before conversion");
            String datePreFormatted = dateTirage.split(" ")[0] + " " + dateTirage.split(" ")[1].substring(0, 3) + ". " + dateTirage.split(" ")[2];
            log.debug("Convert date");
            return new SimpleDateFormat(ISO_LOCAL_DATE).format(new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN, Locale.FRENCH).parse(datePreFormatted));
        } catch (IndexOutOfBoundsException exception) {
            log.error("Error while splitting date : '{}'.", dateTirage);
            log.error(exception.getMessage());
        } catch (ParseException exception) {
            log.error("Error while parsing the date : '{}'", dateTirage);
            log.error(exception.getMessage());
        }
        return null;
    }

    private void checkIfIsAnInteger(String tirages) {
        try {
            Integer.parseInt(tirages);
        } catch (NumberFormatException exception) {
            log.error("Extracted value isn't an integer : '{}'", exception.getMessage());
        }
    }
}

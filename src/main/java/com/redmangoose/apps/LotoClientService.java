package com.redmangoose.apps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.redmangoose.apps.entity.LotoQuebecTirage;

import java.io.IOException;

public class LotoClientService {
    private final String LOTO_QUEBEC_URL = "https://loteries.lotoquebec.com/fr/resultats";

    public LotoQuebecTirage getLastLotoResults() {
        try {
            final WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            final HtmlPage page = client.getPage(LOTO_QUEBEC_URL);
            final String dateTirage = ((HtmlDivision) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div[2]/div/div").get(0)).getFirstChild().getNodeValue();
            final String number1 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[1]").get(0)).getFirstChild().getNodeValue();
            final String number2 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[3]").get(0)).getFirstChild().getNodeValue();
            final String number3 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[5]").get(0)).getFirstChild().getNodeValue();
            final String number4 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[7]").get(0)).getFirstChild().getNodeValue();
            final String number5 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[9]").get(0)).getFirstChild().getNodeValue();
            final String number6 = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[11]").get(0)).getFirstChild().getNodeValue();
            final String numberComp = ((HtmlSpan) page.getByXPath("/html/body/div[2]/div[2]/section/div[2]/div[1]/div/div/div/div/div[1]/div/div[3]/span[13]").get(0)).getFirstChild().getNodeValue();

            final LotoQuebecTirage tirage = new LotoQuebecTirage(dateTirage, number1, number2, number3, number4, number5, number6, numberComp);

            client.close();

            return tirage;
        } catch (IOException exception) {
            return null;
        }
    }
}

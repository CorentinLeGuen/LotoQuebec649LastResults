package com.redmangoose.apps.util;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LotoClientHistoryDraws {
    private final static String FILE_PATH = "history_all.txt";
    private final static String URL_PATH = "https://loteries.lotoquebec.com/fr/loteries/lotto-6-49?annee=%s&widget=resultats-anterieurs&noProduit=212#res";

    public static void main(String[] args) throws IOException {
        File file = new File(FILE_PATH);
        if (file.createNewFile()) {
            System.out.println("File '" + FILE_PATH + "' created");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

        final WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        for (int i = 1982; i <= 2020; i++) {
            final HtmlPage page = client.getPage(URL_PATH.replace("%s", String.valueOf(i)));

            for (int j = 2; j <= 400; j++) {
                try {
                    final HtmlTableDataCell lineDate = (HtmlTableDataCell) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[1]").get(0);
                    final HtmlSpan number1 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[1]").get(0);
                    final HtmlSpan number2 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[2]").get(0);
                    final HtmlSpan number3 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[3]").get(0);
                    final HtmlSpan number4 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[4]").get(0);
                    final HtmlSpan number5 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[5]").get(0);
                    final HtmlSpan number6 = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[6]").get(0);
                    final HtmlSpan numberC = (HtmlSpan) page.getByXPath("/html/body/div[1]/table/tbody/tr[" + j + "]/td[2]/div/span[7]").get(0);

                    writer.write("|" + lineDate.getFirstChild().toString()
                            + "|" + number1.getFirstChild().toString()
                            + "|" + number2.getFirstChild().toString()
                            + "|" + number3.getFirstChild().toString()
                            + "|" + number4.getFirstChild().toString()
                            + "|" + number5.getFirstChild().toString()
                            + "|" + number6.getFirstChild().toString()
                            + "|" + numberC.getFirstChild().toString()
                            + "|");
                    writer.newLine();
                } catch (IndexOutOfBoundsException ex) {
                    System.err.println("Over for year " + i + " with " + (j-2) + " records");
                    break;
                }
            }

        }
        writer.close();
    }
}

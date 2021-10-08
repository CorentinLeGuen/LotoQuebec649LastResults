package com.redmangoose.apps.entity;

public class LotoQuebecTirage implements LotoQuebecObject {
    private String date_tirage;
    private String numero_1;
    private String numero_2;
    private String numero_3;
    private String numero_4;
    private String numero_5;
    private String numero_6;
    private String numero_complementaire;

    public LotoQuebecTirage(String date_tirage, String numero_1, String numero_2, String numero_3, String numero_4, String numero_5, String numero_6, String numero_complementaire) {
        this.date_tirage = date_tirage;
        this.numero_1 = numero_1;
        this.numero_2 = numero_2;
        this.numero_3 = numero_3;
        this.numero_4 = numero_4;
        this.numero_5 = numero_5;
        this.numero_6 = numero_6;
        this.numero_complementaire = numero_complementaire;
    }

    public String getDate_tirage() {
        return date_tirage;
    }

    public void setDate_tirage(String date_tirage) {
        this.date_tirage = date_tirage;
    }

    public String getNumero_1() {
        return numero_1;
    }

    public void setNumero_1(String numero_1) {
        this.numero_1 = numero_1;
    }

    public String getNumero_2() {
        return numero_2;
    }

    public void setNumero_2(String numero_2) {
        this.numero_2 = numero_2;
    }

    public String getNumero_3() {
        return numero_3;
    }

    public void setNumero_3(String numero_3) {
        this.numero_3 = numero_3;
    }

    public String getNumero_4() {
        return numero_4;
    }

    public void setNumero_4(String numero_4) {
        this.numero_4 = numero_4;
    }

    public String getNumero_5() {
        return numero_5;
    }

    public void setNumero_5(String numero_5) {
        this.numero_5 = numero_5;
    }

    public String getNumero_6() {
        return numero_6;
    }

    public void setNumero_6(String numero_6) {
        this.numero_6 = numero_6;
    }

    public String getNumero_complementaire() {
        return numero_complementaire;
    }

    public void setNumero_complementaire(String numero_complementaire) {
        this.numero_complementaire = numero_complementaire;
    }
}

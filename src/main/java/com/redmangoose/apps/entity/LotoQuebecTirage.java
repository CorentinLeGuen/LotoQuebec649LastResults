package com.redmangoose.apps.entity;

public class LotoQuebecTirage implements LotoQuebecObject {
    private String dateTirage;
    private String number1;
    private String number2;
    private String number3;
    private String number4;
    private String number5;
    private String number6;
    private String numberComp;

    public LotoQuebecTirage(String dateTirage, String number1, String number2, String number3, String number4, String number5, String number6, String numberComp) {
        this.dateTirage = dateTirage;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
        this.numberComp = numberComp;
    }

    public String getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(String dateTirage) {
        this.dateTirage = dateTirage;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getNumber3() {
        return number3;
    }

    public void setNumber3(String number3) {
        this.number3 = number3;
    }

    public String getNumber4() {
        return number4;
    }

    public void setNumber4(String number4) {
        this.number4 = number4;
    }

    public String getNumber5() {
        return number5;
    }

    public void setNumber5(String number5) {
        this.number5 = number5;
    }

    public String getNumber6() {
        return number6;
    }

    public void setNumber6(String number6) {
        this.number6 = number6;
    }

    public String getNumberComp() {
        return numberComp;
    }

    public void setNumberComp(String numberComp) {
        this.numberComp = numberComp;
    }
}

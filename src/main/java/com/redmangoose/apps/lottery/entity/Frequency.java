package com.redmangoose.apps.lottery.entity;

public class Frequency implements LotteryObject {
    private final String numero;
    private final String frequence;

    public Frequency(String numero, String frequence) {
        this.numero = numero;
        this.frequence = frequence;
    }

    public String getNumero() {
        return numero;
    }

    public String getFrequence() {
        return frequence;
    }
}

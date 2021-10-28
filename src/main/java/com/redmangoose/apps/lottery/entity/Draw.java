package com.redmangoose.apps.lottery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tirage")
public class Draw implements LotteryObject{
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @JsonProperty("date_tirage")
    @Column(name = "date_tirage")
    private String dateTirage;

    @JsonProperty("numero_1")
    @Column(name = "numero_1")
    private String numero1;

    @JsonProperty("numero_2")
    @Column(name = "numero_2")
    private String numero2;

    @JsonProperty("numero_3")
    @Column(name = "numero_3")
    private String numero3;

    @JsonProperty("numero_4")
    @Column(name = "numero_4")
    private String numero4;

    @JsonProperty("numero_5")
    @Column(name = "numero_5")
    private String numero5;

    @JsonProperty("numero_6")
    @Column(name = "numero_6")
    private String numero6;

    @JsonProperty("numero_complementaire")
    @Column(name = "numero_complementaire")
    private String numeroComplementaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(String dateTirage) {
        this.dateTirage = dateTirage;
    }

    public String getNumero1() {
        return numero1;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getNumero3() {
        return numero3;
    }

    public void setNumero3(String numero3) {
        this.numero3 = numero3;
    }

    public String getNumero4() {
        return numero4;
    }

    public void setNumero4(String numero4) {
        this.numero4 = numero4;
    }

    public String getNumero5() {
        return numero5;
    }

    public void setNumero5(String numero5) {
        this.numero5 = numero5;
    }

    public String getNumero6() {
        return numero6;
    }

    public void setNumero6(String numero6) {
        this.numero6 = numero6;
    }

    public String getNumeroComplementaire() {
        return numeroComplementaire;
    }

    public void setNumeroComplementaire(String numeroComplementaire) {
        this.numeroComplementaire = numeroComplementaire;
    }
}

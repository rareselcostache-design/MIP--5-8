package org.example.model;

public class Bautura extends Produs {
    private int volum;

    public Bautura(String nume, double pret, int volum) {
        super(nume, pret);
        this.volum = volum;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Volum: " + volum + "ml";
    }

    public int getVolum() {
        return volum;
    }
}
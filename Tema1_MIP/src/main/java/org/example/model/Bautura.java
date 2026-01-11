package org.example.model;

public final class Bautura extends Produs {
    private int volum;

    public Bautura(String nume, double pret, Categorie categorie, boolean vegetarian, int volum) {
        super(nume, pret, categorie, vegetarian);
        this.volum = volum;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Volum: " + volum + "ml";
    }
}
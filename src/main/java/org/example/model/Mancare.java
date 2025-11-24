package org.example.model;

public final class Mancare extends Produs {
    private int gramaj;

    public Mancare(String nume, double pret, Categorie categorie, boolean vegetarian, int gramaj) {
        super(nume, pret, categorie, vegetarian);
        this.gramaj = gramaj;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Gramaj: " + gramaj + "g";
    }
}
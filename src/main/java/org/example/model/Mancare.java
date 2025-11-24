package org.example.model;

public class Mancare extends Produs {
    private int gramaj;

    public Mancare(String nume, double pret, int gramaj) {
        super(nume, pret);
        this.gramaj = gramaj;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Gramaj: " + gramaj + "g";
    }

    public int getGramaj() {
        return gramaj;
    }
}
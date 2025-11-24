package org.example.model;

public abstract class Produs {
    protected String nume;
    protected double pret;

    public Produs(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public abstract String getDetaliiSpecifice();

    @Override
    public String toString() {
        return nume + " - " + pret + " RON - " + getDetaliiSpecifice();
    }

    // Getteri pentru viitoarele iterații
    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }
}
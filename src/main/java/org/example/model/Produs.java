package org.example.model;

public abstract class Produs {
    protected String nume;
    protected double pret;
    protected Categorie categorie;
    protected boolean vegetarian;

    public Produs(String nume, double pret, Categorie categorie, boolean vegetarian) {
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
        this.vegetarian = vegetarian;
    }

    public abstract String getDetaliiSpecifice();

    @Override
    public String toString() {
        return nume + " - " + pret + " RON - " + getDetaliiSpecifice() + " [" + categorie.getNume() + "]";
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public Categorie getCategorie() { return categorie; }
    public boolean isVegetarian() { return vegetarian; }
}
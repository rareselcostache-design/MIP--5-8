package org.example;

public class Config {
    private double tva;
    private String numeRestaurant;
    private String versiune;

    // Constructor default
    public Config() {
        this.tva = 0.09;
        this.numeRestaurant = "La Andrei";
        this.versiune = "1.0";
    }

    // Getteri
    public double getTva() { return tva; }
    public String getNumeRestaurant() { return numeRestaurant; }
    public String getVersiune() { return versiune; }

    // Setteri (pentru viitor)
    public void setTva(double tva) { this.tva = tva; }
    public void setNumeRestaurant(String numeRestaurant) { this.numeRestaurant = numeRestaurant; }
    public void setVersiune(String versiune) { this.versiune = versiune; }
}
package org.example.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BAUTURA")
public class Bautura extends Produs {

    @Column(name = "volum")
    private int volum;

    public Bautura() {}

    public Bautura(String nume, double pret, Categorie categorie, boolean vegetarian, int volum) {
        super(nume, pret, categorie, vegetarian);
        this.volum = volum;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Volum: " + volum + "ml";
    }

    public int getVolum() { return volum; }
    public void setVolum(int volum) { this.volum = volum; }
}
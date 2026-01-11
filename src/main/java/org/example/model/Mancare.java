package org.example.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MANCARE")
public class Mancare extends Produs {

    @Column(name = "gramaj")
    private int gramaj;

    public Mancare() {}

    public Mancare(String nume, double pret, Categorie categorie, boolean vegetarian, int gramaj) {
        super(nume, pret, categorie, vegetarian);
        this.gramaj = gramaj;
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Gramaj: " + gramaj + "g";
    }

    public int getGramaj() { return gramaj; }
    public void setGramaj(int gramaj) { this.gramaj = gramaj; }
}
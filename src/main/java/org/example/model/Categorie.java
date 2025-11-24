package org.example.model;

public enum Categorie {
    APERITIVE("Aperitive"),
    FEL_PRINCIPAL("Fel Principal"),
    DESERT("Desert"),
    BAUTURI_RACORITOARE("Băuturi Răcoritoare"),
    BAUTURI_ALCOOLICE("Băuturi Alcoolice"),
    PIZZA("Pizza");

    private final String nume;

    Categorie(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
}
package org.example.model.pizza;

public enum Sos {
    ROSII("Sos de roșii", 3.0),
    BRANZA("Sos de brânză", 5.0),
    PICANT("Sos picant", 4.0),
    ALB("Sos alb", 4.5);

    private final String descriere;
    private final double pret;

    Sos(String descriere, double pret) {
        this.descriere = descriere;
        this.pret = pret;
    }

    public String getDescriere() { return descriere; }
    public double getPret() { return pret; }
}
package org.example.model.pizza;

public enum Blat {
    SUBTIERE("Subtire", 2.0),
    NORMAL("Normal", 0.0),
    PUFOS("Pufos", 3.0),
    INTEGRAL("Integral", 4.0);

    private final String descriere;
    private final double pretAditional;

    Blat(String descriere, double pretAditional) {
        this.descriere = descriere;
        this.pretAditional = pretAditional;
    }

    public String getDescriere() { return descriere; }
    public double getPretAditional() { return pretAditional; }
}
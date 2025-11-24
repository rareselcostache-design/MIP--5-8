package org.example.model.pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String nume;
    private double pret;
    private boolean vegetarian;
    private Blat blat;
    private Sos sos;
    private List<Topping> toppinguri = new ArrayList<>();

    public PizzaBuilder(String nume) {
        this.nume = nume;
        this.pret = 25.0; // Preț de bază pentru pizza
        this.vegetarian = true; // Presupunem vegetarian până adăugăm toppinguri non-vegetariene
    }

    public PizzaBuilder setBlat(Blat blat) {
        this.blat = blat;
        this.pret += blat.getPretAditional();
        return this;
    }

    public PizzaBuilder setSos(Sos sos) {
        this.sos = sos;
        this.pret += sos.getPret();
        return this;
    }

    public PizzaBuilder adaugaTopping(Topping topping) {
        this.toppinguri.add(topping);
        this.pret += topping.getPret();

        // Verifică dacă pizza devine non-vegetariană
        if (topping == Topping.SUNCA || topping == Topping.SALAMI) {
            this.vegetarian = false;
        }

        return this;
    }

    public Pizza build() {
        if (blat == null) {
            throw new IllegalStateException("Blatul este obligatoriu!");
        }
        if (sos == null) {
            throw new IllegalStateException("Sosul este obligatoriu!");
        }
        return new Pizza(this.nume, this.blat, this.sos, this.toppinguri, this.pret, this.vegetarian);
    }
}
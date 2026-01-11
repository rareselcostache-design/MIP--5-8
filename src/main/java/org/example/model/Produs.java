package org.example.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produse")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tip_produs", discriminatorType = DiscriminatorType.STRING)
public abstract class Produs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "pret", nullable = false)
    private double pret;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", nullable = false)
    private Categorie categorie;

    @Column(name = "vegetarian")
    private boolean vegetarian;

    public Produs() {}

    public Produs(String nume, double pret, Categorie categorie, boolean vegetarian) {
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
        this.vegetarian = vegetarian;
    }

    public abstract String getDetaliiSpecifice();

    // Getters și Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public boolean isVegetarian() { return vegetarian; }
    public void setVegetarian(boolean vegetarian) { this.vegetarian = vegetarian; }

    @Override
    public String toString() {
        return nume + " - " + pret + " RON - " + getDetaliiSpecifice();
    }
}
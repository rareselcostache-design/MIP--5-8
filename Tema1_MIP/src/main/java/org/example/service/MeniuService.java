package org.example.service;

import org.example.model.*;
import java.util.List;
import java.util.Optional;

public class MeniuService {
    private Meniu meniu;

    public MeniuService() {
        this.meniu = new Meniu();
        initializareMeniu();
    }

    private void initializareMeniu() {
        // Produse simple - FĂRĂ Pizza pentru moment
        meniu.adaugaProdus(new Mancare("Bruschete", 18.0, Categorie.APERITIVE, true, 200));
        meniu.adaugaProdus(new Mancare("Pizza Margherita", 45.0, Categorie.FEL_PRINCIPAL, true, 450));
        meniu.adaugaProdus(new Mancare("Paste Carbonara", 52.5, Categorie.FEL_PRINCIPAL, false, 400));
        meniu.adaugaProdus(new Mancare("Tiramisu", 22.0, Categorie.DESERT, true, 150));
        meniu.adaugaProdus(new Mancare("Cheesecake", 28.0, Categorie.DESERT, true, 180));

        meniu.adaugaProdus(new Bautura("Limonada", 15.0, Categorie.BAUTURI_RACORITOARE, true, 400));
        meniu.adaugaProdus(new Bautura("Apa Plata", 8.0, Categorie.BAUTURI_RACORITOARE, true, 500));
        meniu.adaugaProdus(new Bautura("Vin Rosu", 25.0, Categorie.BAUTURI_ALCOOLICE, true, 250));
        meniu.adaugaProdus(new Bautura("Bere", 12.0, Categorie.BAUTURI_ALCOOLICE, true, 500));
    }

    public void afisareMeniuComplet() {
        System.out.println("=== MENIU COMPLET PE CATEGORII ===");
        meniu.afiseazaMeniuComplet();
    }

    public void afiseazaInterogariComplexe() {
        System.out.println("\n=== INTEROGĂRI COMPLEXE ===");

        // 1. Produse vegetariene sortate
        System.out.println("\n1. PRODUSE VEGETARIENE:");
        List<Produs> vegetariene = meniu.getProduseVegetarieneSortate();
        vegetariene.forEach(p -> System.out.println("> " + p.getNume() + " - " + p.getPret() + " RON"));

        // 2. Preț mediu deserturi
        System.out.println("\n2. PREȚ MEDIU DESERTURI:");
        meniu.getPretMediuCategorie(Categorie.DESERT)
                .ifPresent(avg -> System.out.println("> " + String.format("%.2f", avg) + " RON"));

        // 3. Produse peste 100 RON
        System.out.println("\n3. PRODUSE PESTE 100 RON:");
        System.out.println("> " + (meniu.existaProdusPeste100RON() ? "DA" : "NU"));
    }

    public void cautaProdus(String nume) {
        System.out.println("\n🔍 CĂUTARE: '" + nume + "'");
        Optional<Produs> rezultat = meniu.cautaProdusDupaNume(nume);
        if (rezultat.isPresent()) {
            System.out.println("✓ Găsit: " + rezultat.get());
        } else {
            System.out.println("❌ Negăsit");
        }
    }

    public List<Produs> getProduse() {
        return meniu.getToateProdusele();
    }

    public Produs gasesteProdusDupaNume(String nume) {
        return meniu.cautaProdusDupaNume(nume).orElse(null);
    }
}
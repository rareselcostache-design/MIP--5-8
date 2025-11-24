package org.example.service;

import org.example.model.Produs;
import org.example.model.Mancare;
import org.example.model.Bautura;
import java.util.ArrayList;
import java.util.List;

public class MeniuService {
    private List<Produs> produse;

    public MeniuService() {
        this.produse = new ArrayList<>();
        initializareMeniu();
    }

    private void initializareMeniu() {
        // Date hardcodate conform cerinței
        produse.add(new Mancare("Pizza Margherita", 45.0, 450));
        produse.add(new Mancare("Paste Carbonara", 52.5, 400));
        produse.add(new Bautura("Limonada", 15.0, 400));
        produse.add(new Bautura("Apa Plata", 8.0, 500));
        // Adăugăm și o băutură alcoolică pentru testare Happy Hour
        produse.add(new Bautura("Vin Rosu", 25.0, 250));
    }

    public void afisareMeniu() {
        System.out.println("--- Meniul Restaurantului \"La Andrei\" ---");
        for (Produs produs : produse) {
            System.out.println("> " + produs.toString());
        }
        System.out.println("---");
    }

    public List<Produs> getProduse() {
        return new ArrayList<>(produse);
    }

    public Produs gasesteProdusDupaNume(String nume) {
        return produse.stream()
                .filter(p -> p.getNume().equalsIgnoreCase(nume))
                .findFirst()
                .orElse(null);
    }
}
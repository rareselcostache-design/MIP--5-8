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
    }

    public void afisareMeniu() {
        System.out.println("--- Meniul Restaurantului \"La Andrei\" ---");
        for (Produs produs : produse) {
            System.out.println("> " + produs.toString());
        }
        System.out.println("---");
    }

    // Pentru viitoarele iterații
    public List<Produs> getProduse() {
        return new ArrayList<>(produse);
    }
}
package org.example.command;

import org.example.ConfigManager;
import org.example.model.Produs;
import java.util.HashMap;
import java.util.Map;

public class Comanda {
    private Map<Produs, Integer> produse;
    private DiscountStrategy discountStrategy;

    // Constructor - folosește TVA din ConfigManager
    public Comanda() {
        this.produse = new HashMap<>();
        this.discountStrategy = (subtotal, produse) -> subtotal;
    }

    // Metoda calculeazaTotal actualizată
    public double calculeazaTotal() {
        double subtotal = produse.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPret() * entry.getValue())
                .sum();

        double totalCuDiscount = discountStrategy.aplicaDiscount(subtotal, produse);
        double tva = ConfigManager.getTva(); // TVA din config
        double totalCuTVA = totalCuDiscount * (1 + tva);

        return Math.round(totalCuTVA * 100.0) / 100.0;
    }

    // Restul metodelor rămân EXACT LA FEL ca în Iterația 3
    public void adaugaProdus(Produs produs, int cantitate) {
        produse.put(produs, produse.getOrDefault(produs, 0) + cantitate);
        System.out.println("✓ Adăugat: " + cantitate + " x " + produs.getNume());
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void afiseazaComanda() {
        double tva = ConfigManager.getTva();

        System.out.println("\n--- Detalii Comandă ---");
        produse.forEach((produs, cantitate) -> {
            System.out.println("> " + cantitate + " x " + produs.getNume() + " - " +
                    (produs.getPret() * cantitate) + " RON");
        });
        System.out.println("Total cu TVA " + (tva * 100) + "%: " + calculeazaTotal() + " RON");
        System.out.println("---");
    }
}
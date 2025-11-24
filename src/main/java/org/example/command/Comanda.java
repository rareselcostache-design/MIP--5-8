package org.example.command;

import org.example.model.Produs;
import java.util.HashMap;
import java.util.Map;

public class Comanda {
    private static final double TVA = 0.09; // 9% TVA
    private Map<Produs, Integer> produse;
    private DiscountStrategy discountStrategy;

    public Comanda() {
        this.produse = new HashMap<>();
        this.discountStrategy = (subtotal, produse) -> subtotal; // Discount implicit: niciunul
    }

    // Adaugă produs în comandă cu cantitate
    public void adaugaProdus(Produs produs, int cantitate) {
        if (cantitate <= 0) {
            throw new IllegalArgumentException("Cantitatea trebuie să fie pozitivă");
        }
        produse.put(produs, produse.getOrDefault(produs, 0) + cantitate);
        System.out.println("✓ Adăugat: " + cantitate + " x " + produs.getNume());
    }

    // Setează strategia de discount
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
        System.out.println("✓ Strategie discount aplicată");
    }

    // Calculează subtotalul (fără TVA și discount)
    public double calculeazaSubtotal() {
        return produse.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPret() * entry.getValue())
                .sum();
    }

    // Calculează totalul comenzii
    public double calculeazaTotal() {
        double subtotal = calculeazaSubtotal();
        double totalCuDiscount = discountStrategy.aplicaDiscount(subtotal, produse);
        double totalCuTVA = totalCuDiscount * (1 + TVA);

        return Math.round(totalCuTVA * 100.0) / 100.0; // Rotunjire la 2 zecimale
    }

    // Afișează detalii comandă
    public void afiseazaComanda() {
        System.out.println("\n--- Detalii Comandă ---");
        if (produse.isEmpty()) {
            System.out.println("> Comandă goală");
        } else {
            produse.forEach((produs, cantitate) -> {
                double pretTotalProdus = produs.getPret() * cantitate;
                System.out.println("> " + cantitate + " x " + produs.getNume() + " - " +
                        pretTotalProdus + " RON");
            });

            double subtotal = calculeazaSubtotal();
            double total = calculeazaTotal();
            double discount = subtotal - discountStrategy.aplicaDiscount(subtotal, produse);

            System.out.println("----------------------");
            System.out.println("Subtotal: " + subtotal + " RON");
            if (discount > 0) {
                System.out.println("Discount: -" + discount + " RON");
            }
            System.out.println("TVA (" + (TVA * 100) + "%): +" + (total - (subtotal - discount)) + " RON");
            System.out.println("TOTAL: " + total + " RON");
        }
        System.out.println("---");
    }

    public Map<Produs, Integer> getProduse() {
        return new HashMap<>(produse);
    }

    public static double getTVA() {
        return TVA;
    }
}
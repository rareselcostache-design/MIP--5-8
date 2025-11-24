package org.example.service;

import org.example.command.Comanda;
import org.example.command.DiscountStrategy;
import org.example.model.Bautura;
import org.example.model.Produs;
import java.util.Map;

public class ComandaService {

    // Strategie Happy Hour - 20% reducere la toate băuturile
    public static DiscountStrategy createHappyHourStrategy() {
        return (subtotal, produse) -> {
            double discountTotal = 0.0;

            for (Map.Entry<Produs, Integer> entry : produse.entrySet()) {
                Produs produs = entry.getKey();
                int cantitate = entry.getValue();

                if (produs instanceof Bautura) {
                    double pretProdus = produs.getPret() * cantitate;
                    discountTotal += pretProdus * 0.20; // 20% discount
                    System.out.println("✓ Discount Happy Hour aplicat pentru: " + produs.getNume());
                }
            }

            return subtotal - discountTotal;
        };
    }

    // Strategie discount general - procent la tot
    public static DiscountStrategy createDiscountGeneralStrategy(double procent) {
        return (subtotal, produse) -> {
            double discount = subtotal * (procent / 100);
            System.out.println("✓ Discount general " + procent + "% aplicat: -" + discount + " RON");
            return subtotal - discount;
        };
    }

    // Strategie "Buy 1 Get 1 Free" pentru un anumit produs
    public static DiscountStrategy createBOGOFStrategy(Produs produsOferta) {
        return (subtotal, produse) -> {
            Integer cantitate = produse.get(produsOferta);
            if (cantitate != null && cantitate >= 2) {
                int produseGratis = cantitate / 2;
                double discount = produseGratis * produsOferta.getPret();
                System.out.println("✓ Oferta BOGOF: " + produseGratis + " x " + produsOferta.getNume() + " gratis!");
                return subtotal - discount;
            }
            return subtotal;
        };
    }
}
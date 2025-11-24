package org.example;

import org.example.model.*;
import org.example.service.MeniuService;
import org.example.service.ComandaService;
import org.example.command.Comanda;

public class Main {
    public static void main(String[] args) {
        MeniuService meniuService = new MeniuService();

        System.out.println("=== ITERAȚIA 1 - Afișare Meniu ===");
        meniuService.afisareMeniu();

        System.out.println("\n=== ITERAȚIA 2 - Sistem Comenzi & Discounturi ===");

        // Test 1: Comandă simplă fără discount
        System.out.println("\n🧪 TEST 1: Comandă fără discount");
        Comanda comanda1 = new Comanda();
        comanda1.adaugaProdus(meniuService.gasesteProdusDupaNume("Pizza Margherita"), 1);
        comanda1.adaugaProdus(meniuService.gasesteProdusDupaNume("Limonada"), 2);
        comanda1.afiseazaComanda();

        // Test 2: Comandă cu Happy Hour
        System.out.println("\n🧪 TEST 2: Comandă cu Happy Hour (20% băuturi)");
        Comanda comanda2 = new Comanda();
        comanda2.adaugaProdus(meniuService.gasesteProdusDupaNume("Pizza Margherita"), 2);
        comanda2.adaugaProdus(meniuService.gasesteProdusDupaNume("Limonada"), 1);
        comanda2.adaugaProdus(meniuService.gasesteProdusDupaNume("Vin Rosu"), 1);
        comanda2.setDiscountStrategy(ComandaService.createHappyHourStrategy());
        comanda2.afiseazaComanda();

        // Test 3: Comandă cu discount general
        System.out.println("\n🧪 TEST 3: Comandă cu 15% discount general");
        Comanda comanda3 = new Comanda();
        comanda3.adaugaProdus(meniuService.gasesteProdusDupaNume("Paste Carbonara"), 1);
        comanda3.adaugaProdus(meniuService.gasesteProdusDupaNume("Apa Plata"), 4);
        comanda3.setDiscountStrategy(ComandaService.createDiscountGeneralStrategy(15));
        comanda3.afiseazaComanda();

        // Test 4: Oferta BOGOF (Buy One Get One Free)
        System.out.println("\n🧪 TEST 4: Oferta BOGOF - Cumpără 1, Primești 1 Gratis");
        Comanda comanda4 = new Comanda();
        Produs apa = meniuService.gasesteProdusDupaNume("Apa Plata");
        comanda4.adaugaProdus(apa, 3); // Primești 1 gratis
        comanda4.adaugaProdus(meniuService.gasesteProdusDupaNume("Pizza Margherita"), 1);
        comanda4.setDiscountStrategy(ComandaService.createBOGOFStrategy(apa));
        comanda4.afiseazaComanda();

        // Afișare TVA
        System.out.println("\nℹ️  TVA curent: " + (Comanda.getTVA() * 100) + "%");

        System.out.println("\n🎉 Toate testele Iterației 2 au fost rulate cu succes!");
    }
}
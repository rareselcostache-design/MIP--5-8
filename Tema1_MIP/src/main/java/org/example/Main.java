package org.example;

import org.example.service.MeniuService;
import org.example.service.ComandaService;
import org.example.command.Comanda;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("🏪 PROIECT MENIU RESTAURANT - TOATE ITERAȚIILE");
        System.out.println("================================================");

        // Iterația 1 se încarcă automat în MeniuService
        System.out.println("\n" + "═".repeat(50));
        System.out.println("1️⃣  ITERAȚIA 1 - AFIȘARE MENIU");
        System.out.println("═".repeat(50));
        MeniuService meniuService = new MeniuService();
        meniuService.afisareMeniuComplet();

        System.out.println("\n" + "═".repeat(50));
        System.out.println("2️⃣  ITERAȚIA 2 - SISTEM COMENZI & DISCOUNTURI");
        System.out.println("═".repeat(50));

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

        System.out.println("\n" + "═".repeat(50));
        System.out.println("3️⃣  ITERAȚIA 3 - INTEROGĂRI COMPLEXE");
        System.out.println("═".repeat(50));
        meniuService.afiseazaInterogariComplexe();

        System.out.println("\n" + "═".repeat(50));
        System.out.println("3️⃣  ITERAȚIA 3 - CĂUTARE SIGURĂ");
        System.out.println("═".repeat(50));
        meniuService.cautaProdus("Pizza Margherita");
        meniuService.cautaProdus("Produs Inexistent");
        meniuService.cautaProdus("Tiramisu");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("4️⃣  ITERAȚIA 4 - CONFIGURARE EXTERNĂ");
        System.out.println("═".repeat(50));
        // Configurarea se încarcă automat la pornire
        System.out.println("🏪 " + ConfigManager.getNumeRestaurant());
        System.out.println("📊 TVA: " + (ConfigManager.getTva() * 100) + "%");
        System.out.println("🔢 Versiune: " + ConfigManager.getConfig().getVersiune());

        System.out.println("\n" + "═".repeat(50));
        System.out.println("4️⃣  ITERAȚIA 4 - EXPORT MENIU");
        System.out.println("═".repeat(50));
        StringBuilder exportContent = new StringBuilder();
        meniuService.getProduse().forEach(produs ->
                exportContent.append("- ").append(produs).append("\n")
        );

        FileExporter.exportaMeniu(exportContent.toString());

        System.out.println("\n" + "═".repeat(50));
        System.out.println("🎉 REZUMAT FINAL - TOATE ITERAȚIILE COMPLETATE!");
        System.out.println("═".repeat(50));
        System.out.println("✅ Iterația 1: Modelare ierarhie clase + afișare meniu");
        System.out.println("✅ Iterația 2: Sistem comenzi + discounturi flexibile");
        System.out.println("✅ Iterația 3: Categorii + interogări + căutare sigură");
        System.out.println("✅ Iterația 4: Configurare externă + export + gestionare erori");
        System.out.println("\n📊 TVA curent: " + (ConfigManager.getTva() * 100) + "%");
        System.out.println("🏪 Restaurant: " + ConfigManager.getNumeRestaurant());
        System.out.println("📁 Export generat: export_meniu.txt");
        System.out.println("🎯 Proiect complet și funcțional!");
    }
}
package org.example;

import org.example.service.MeniuService;
import org.example.command.Comanda;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ITERAȚIA 4 - CONFIGURARE EXTERNĂ ===");
        System.out.println("🏪 " + ConfigManager.getNumeRestaurant());
        System.out.println("📊 TVA: " + (ConfigManager.getTva() * 100) + "%");
        System.out.println("🔢 Versiune: " + ConfigManager.getConfig().getVersiune());

        MeniuService meniuService = new MeniuService();
        meniuService.afisareMeniuComplet();

        // Test comandă cu TVA din config
        Comanda comanda = new Comanda();
        comanda.adaugaProdus(meniuService.gasesteProdusDupaNume("Pizza Margherita"), 2);
        comanda.adaugaProdus(meniuService.gasesteProdusDupaNume("Limonada"), 1);
        comanda.afiseazaComanda();

        // Export meniu
        System.out.println("\n=== EXPORT MENIU ===");
        StringBuilder exportContent = new StringBuilder();
        meniuService.getProduse().forEach(produs ->
                exportContent.append("- ").append(produs).append("\n")
        );

        FileExporter.exportaMeniu(exportContent.toString());

        System.out.println("\n🎉 Iterația 4 completă!");
    }
}
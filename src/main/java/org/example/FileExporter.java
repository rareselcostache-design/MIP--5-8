package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public static void exportaMeniu(String continut) {
        try (FileWriter writer = new FileWriter("export_meniu.txt")) {
            writer.write("=== MENIU RESTAURANT ===\n");
            writer.write("Nume: " + ConfigManager.getNumeRestaurant() + "\n");
            writer.write("TVA: " + (ConfigManager.getTva() * 100) + "%\n");
            writer.write("Versiune: " + ConfigManager.getConfig().getVersiune() + "\n\n");
            writer.write(continut);
            writer.write("\n=== Export generat automat ===");
            System.out.println("✅ Meniu exportat în export_meniu.txt");
        } catch (IOException e) {
            System.out.println("❌ Eroare la export: " + e.getMessage());
        }
    }
}
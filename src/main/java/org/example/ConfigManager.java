package org.example;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static Config config;

    static {
        incarcaConfig();
    }

    private static void incarcaConfig() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);

            // Creează obiectul Config cu datele din fișier
            config = new Config();
            config.setTva(Double.parseDouble(properties.getProperty("tva", "0.09")));
            config.setNumeRestaurant(properties.getProperty("nume_restaurant", "La Andrei"));
            config.setVersiune(properties.getProperty("versiune", "1.0"));

            System.out.println("✅ Configurare încărcată din config.properties");

        } catch (IOException e) {
            System.out.println("⚠️  Fișier config.properties negăsit. Folosesc valori default.");
            config = new Config(); // Valori default
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static double getTva() {
        return config.getTva();
    }

    public static String getNumeRestaurant() {
        return config.getNumeRestaurant();
    }

    public static void salveazaConfig() {
        Properties properties = new Properties();
        properties.setProperty("tva", String.valueOf(config.getTva()));
        properties.setProperty("nume_restaurant", config.getNumeRestaurant());
        properties.setProperty("versiune", config.getVersiune());

        try (OutputStream output = new FileOutputStream("config.properties")) {
            properties.store(output, "Configurare Restaurant");
            System.out.println("✅ Configurare salvată în config.properties");
        } catch (IOException e) {
            System.out.println("❌ Eroare la salvarea configurării: " + e.getMessage());
        }
    }
}
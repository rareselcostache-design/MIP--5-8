package org.example;

import org.example.service.MeniuService;
import org.example.command.Comanda;

public class Main {
    public static void main(String[] args) {
        MeniuService meniuService = new MeniuService();

        // Iterația 1 & 2
        meniuService.afisareMeniuComplet();

        // Iterația 3
        meniuService.afiseazaInterogariComplexe();

        // Căutări
        meniuService.cautaProdus("Pizza Margherita");
        meniuService.cautaProdus("Produs Inexistent");

        // Test comandă
        Comanda comanda = new Comanda();
        comanda.adaugaProdus(meniuService.gasesteProdusDupaNume("Pizza Margherita"), 2);
        comanda.adaugaProdus(meniuService.gasesteProdusDupaNume("Limonada"), 1);
        comanda.afiseazaComanda();

        System.out.println("\n🎉 Iterația 3 completă și FUNCȚIONALĂ!");
    }
}
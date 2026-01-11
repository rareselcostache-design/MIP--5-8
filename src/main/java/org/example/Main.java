package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println(" PROIECT MENIU RESTAURANT");
        System.out.println("================================================");
        System.out.println("1. Iterația 5 - GUI simplu (fără baza de date)");
        System.out.println("2. Iterația 6 - PostgreSQL + JSON (complet)");
        System.out.print("\nAlegeți opțiunea (1 sau 2): ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        if ("1".equals(choice)) {
            // Rulează iterația 5
            System.out.println("\nPornire Iterația 5...");
            org.example.gui.MeniuGUI5.main(args);
        } else if ("2".equals(choice)) {
            // Rulează iterația 6
            System.out.println("\nPornire Iterația 6...");
            System.out.println(" PostgreSQL + JPA + JSON Import/Export");
            org.example.gui.MeniuGUI6.main(args);
        } else {
            System.out.println("Opțiune invalidă. Pornire Iterația 6...");
            org.example.gui.MeniuGUI6.main(args);
        }

        scanner.close();
    }
}
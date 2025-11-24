package org.example.model;

import java.util.*;
import java.util.stream.Collectors;

public class Meniu {
    private Map<Categorie, List<Produs>> produsePeCategorii;

    public Meniu() {
        this.produsePeCategorii = new HashMap<>();
        for (Categorie categorie : Categorie.values()) {
            produsePeCategorii.put(categorie, new ArrayList<>());
        }
    }

    public void adaugaProdus(Produs produs) {
        produsePeCategorii.get(produs.getCategorie()).add(produs);
    }

    public List<Produs> getProduseDinCategorie(Categorie categorie) {
        return new ArrayList<>(produsePeCategorii.get(categorie));
    }

    public List<Produs> getToateProdusele() {
        return produsePeCategorii.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Interogări complexe
    public List<Produs> getProduseVegetarieneSortate() {
        return getToateProdusele().stream()
                .filter(Produs::isVegetarian)
                .sorted(Comparator.comparing(Produs::getNume))
                .collect(Collectors.toList());
    }

    public OptionalDouble getPretMediuCategorie(Categorie categorie) {
        return produsePeCategorii.get(categorie).stream()
                .mapToDouble(Produs::getPret)
                .average();
    }

    public boolean existaProdusPeste100RON() {
        return getToateProdusele().stream()
                .anyMatch(produs -> produs.getPret() > 100);
    }

    public Optional<Produs> cautaProdusDupaNume(String nume) {
        return getToateProdusele().stream()
                .filter(produs -> produs.getNume().equalsIgnoreCase(nume))
                .findFirst();
    }

    public void afiseazaMeniuComplet() {
        for (Categorie categorie : Categorie.values()) {
            List<Produs> produse = produsePeCategorii.get(categorie);
            if (!produse.isEmpty()) {
                System.out.println("\n--- " + categorie.getNume() + " ---");
                produse.forEach(produs -> System.out.println("> " + produs));
            }
        }
    }
}
package org.example.repository;

import jakarta.persistence.*;
import org.example.model.*;
import java.util.List;

public class ProdusRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdusRepository() {
        emf = Persistence.createEntityManagerFactory("restaurant-pu");
        em = emf.createEntityManager();
        initDatabase();
    }

    private void initDatabase() {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Verifică dacă există produse
            Long count = em.createQuery("SELECT COUNT(p) FROM Produs p", Long.class)
                    .getSingleResult();

            if (count == 0) {
                // Inserează date demo
                em.persist(new Mancare("Bruschete", 18.0, Categorie.APERITIVE, true, 200));
                em.persist(new Mancare("Pizza Margherita", 45.0, Categorie.FEL_PRINCIPAL, true, 450));
                em.persist(new Mancare("Paste Carbonara", 52.5, Categorie.FEL_PRINCIPAL, false, 400));
                em.persist(new Mancare("Tiramisu", 22.0, Categorie.DESERT, true, 150));
                em.persist(new Mancare("Cheesecake", 28.0, Categorie.DESERT, true, 180));

                em.persist(new Bautura("Limonada", 15.0, Categorie.BAUTURI_RACORITOARE, true, 400));
                em.persist(new Bautura("Apa Plata", 8.0, Categorie.BAUTURI_RACORITOARE, true, 500));
                em.persist(new Bautura("Vin Rosu", 25.0, Categorie.BAUTURI_ALCOOLICE, true, 250));
                em.persist(new Bautura("Bere", 12.0, Categorie.BAUTURI_ALCOOLICE, true, 500));

                System.out.println("✓ Date inițiale încărcate în PostgreSQL");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.out.println("⚠️ Eroare inițializare baza de date: " + e.getMessage());
        }
    }

    public List<Produs> getAllProduse() {
        return em.createQuery("SELECT p FROM Produs p ORDER BY p.nume", Produs.class)
                .getResultList();
    }

    public void saveProdus(Produs produs) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (produs.getId() == null) {
                em.persist(produs);
            } else {
                em.merge(produs);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void close() {
        if (em != null && em.isOpen()) em.close();
        if (emf != null && emf.isOpen()) emf.close();
    }
}
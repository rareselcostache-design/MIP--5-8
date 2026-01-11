package org.example;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        try {
            // SQLite - creează fișier local
            conn = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
            createTable();
            insertDemoData();
            System.out.println("✓ SQLite database ready");
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS produse (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nume TEXT NOT NULL,
                pret REAL NOT NULL,
                categorie TEXT NOT NULL,
                tip_produs TEXT NOT NULL
            )
            """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private void insertDemoData() throws SQLException {
        String check = "SELECT COUNT(*) FROM produse";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(check)) {
            if (rs.next() && rs.getInt(1) == 0) {
                String insert = """
                    INSERT INTO produse (nume, pret, categorie, tip_produs) VALUES
                    ('Bruschete', 18.0, 'APERITIVE', 'MANCARE'),
                    ('Pizza Margherita', 45.0, 'FEL_PRINCIPAL', 'MANCARE'),
                    ('Limonada', 15.0, 'BAUTURI_RACORITOARE', 'BAUTURA')
                    """;
                stmt.executeUpdate(insert);
                System.out.println("✓ Demo data inserted");
            }
        }
    }

    public List<Map<String, Object>> getProduse() {
        List<Map<String, Object>> produse = new ArrayList<>();
        String sql = "SELECT * FROM produse ORDER BY nume";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> produs = new HashMap<>();
                produs.put("id", rs.getInt("id"));
                produs.put("nume", rs.getString("nume"));
                produs.put("pret", rs.getDouble("pret"));
                produs.put("categorie", rs.getString("categorie"));
                produs.put("tip_produs", rs.getString("tip_produs"));
                produse.add(produs);
            }
        } catch (SQLException e) {
            System.out.println("Error reading: " + e.getMessage());
        }

        return produse;
    }

    public void insertProdus(String nume, double pret, String categorie, String tip) {
        String sql = "INSERT INTO produse (nume, pret, categorie, tip_produs) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nume);
            pstmt.setDouble(2, pret);
            pstmt.setString(3, categorie);
            pstmt.setString(4, tip);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting: " + e.getMessage());
        }
    }
}
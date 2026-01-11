package org.example.model.pizza;

public enum Topping {
    MOZZARELLA("Mozzarella", 5.0),
    PARMEZAN("Parmezan", 7.0),
    CIUPERCI("Ciuperci", 4.0),
    SUNCA("Șuncă", 6.0),
    SALAMI("Salami", 8.0),
    MASLINE("Măsline", 3.0),
    ARDEI("Ardei gras", 3.5),
    CEAPA("Ceapă", 2.0),
    ANANAS("Ananas", 4.0), // 😄
    LEGUME_FELIATE("Legume feliate", 6.0);

    private final String nume;
    private final double pret;

    Topping(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
}
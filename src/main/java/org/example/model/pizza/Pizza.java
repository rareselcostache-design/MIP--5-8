package org.example.model.pizza;

import org.example.model.Categorie;
import org.example.model.Produs;
import java.util.ArrayList;
import java.util.List;

public class Pizza extends Produs {
    private Blat blat;
    private Sos sos;
    private List<Topping> toppinguri;

    // Constructor pentru Builder
    public Pizza(String nume, Blat blat, Sos sos, List<Topping> toppinguri, double pret, boolean vegetarian) {
        super(nume, pret, Categorie.PIZZA, vegetarian);
        this.blat = blat;
        this.sos = sos;
        this.toppinguri = new ArrayList<>(toppinguri);
    }

    @Override
    public String getDetaliiSpecifice() {
        return "Blat: " + blat.getDescriere() + ", Sos: " + sos.getDescriere() +
                ", Toppinguri: " + toppinguri.size();
    }

    public String getDetaliiComplete() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizza '").append(getNume()).append("'\n");
        sb.append("Blat: ").append(blat.getDescriere()).append("\n");
        sb.append("Sos: ").append(sos.getDescriere()).append("\n");
        sb.append("Toppinguri: ");
        if (toppinguri.isEmpty()) {
            sb.append("Niciunul");
        } else {
            toppinguri.forEach(t -> sb.append("\n  - ").append(t.getNume()).append(" (+").append(t.getPret()).append(" RON)"));
        }
        sb.append("\nPreț total: ").append(getPret()).append(" RON");
        return sb.toString();
    }
}
package org.example.command;

import org.example.model.Produs;
import java.util.Map;

@FunctionalInterface
public interface DiscountStrategy {
    double aplicaDiscount(double subtotal, Map<Produs, Integer> produse);
}
package com.model.shop.entity;


import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    private List<CartElement> merchandises;

    public ShoppingCart() {
        merchandises = new LinkedList<>();
    }

    public List<CartElement> getMerchandises() {
        return merchandises;
    }

    public void setMerchandises(List<CartElement> merchandises) {
        this.merchandises = merchandises;
    }
}

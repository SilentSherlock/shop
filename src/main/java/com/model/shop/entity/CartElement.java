package com.model.shop.entity;

public class CartElement {
    private Merchandise merchandise;
    private int number;

    public CartElement(Merchandise merchandise, int number) {
        this.merchandise = merchandise;
        this.number = number;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

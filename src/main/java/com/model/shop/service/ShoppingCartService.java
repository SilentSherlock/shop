package com.model.shop.service;

import com.model.shop.entity.CartElement;
import com.model.shop.entity.ShoppingCart;

import java.util.List;

public class ShoppingCartService {

    private ShoppingCart shoppingCart;

    public ShoppingCartService() {
        shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addMerchandise(CartElement cartElement) {
        if (cartElement != null) {
            shoppingCart.getMerchandises().add(cartElement);
        }
        return;
    }

    public ShoppingCart deleteMerchandise(Integer merIndex) {
        if (merIndex != -1) {
            shoppingCart.getMerchandises().remove(merIndex);
            return shoppingCart;
        }
        return shoppingCart;
    }

    //结算购物车中所有商品
    public double windUp() {
        double totalPrice = 0;
        List<CartElement> merchandises = shoppingCart.getMerchandises();
        if (merchandises != null) {
            for (CartElement merchandise : merchandises) {
                totalPrice += merchandise.getMerchandise().getPrice() * merchandise.getNumber();
            }
        }
        return totalPrice;
    }

    //清空购物车
    public void clearUp() {
        shoppingCart.getMerchandises().clear();
    }
}

package com.model.shop.entity;

import lombok.Data;

@Data
public class Order {

    private Integer orderId;
    private LoginUser loginUser;
    private Merchandise merchandise;
    private Integer merQuantity;
    private double totalPrice;
    private String orderState;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getMerQuantity() {
        return merQuantity;
    }

    public void setMerQuantity(Integer merQuantity) {
        this.merQuantity = merQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", loginUser=" + loginUser +
                ", merchandise=" + merchandise +
                ", merQuantity=" + merQuantity +
                ", totalPrice=" + totalPrice +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}

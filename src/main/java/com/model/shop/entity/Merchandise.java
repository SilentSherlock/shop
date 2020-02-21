package com.model.shop.entity;

import lombok.Data;

@Data
public class Merchandise {
    private Integer merId;
    private String merName;
    private String merDetail;
    private Double price;
    private Integer quantity;

    public Integer getMerId() {
        return merId;
    }

    public void setMerId(Integer merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerDetail() {
        return merDetail;
    }

    public void setMerDetail(String merDetail) {
        this.merDetail = merDetail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

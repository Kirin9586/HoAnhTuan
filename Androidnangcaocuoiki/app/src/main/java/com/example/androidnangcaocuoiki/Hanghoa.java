package com.example.androidnangcaocuoiki;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Hanghoa implements Serializable {
    private String description;
    private int id;
    private int price;
    private String product_name;
    private String producer;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Exclude
    String keyParent;
    @Exclude
    public String getKeyParent() {
        return keyParent;
    }
    @Exclude
    public void setKeyParent(String keyParent) {
        this.keyParent = keyParent;
    }
}

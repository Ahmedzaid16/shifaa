package com.example.lab1;

import android.graphics.Bitmap;

public class data {
    private String name;
    private String price;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Bitmap image;
    public data()
    {

    }
    public data(String name, String price,String key, Bitmap image)
    {
        this.name=name;
        this.price=price;
        this.key=key;
        this.image=image;

    }
    public data(String name, String price)
    {
        this.name=name;
        this.price=price;
        //this.image=image;

    }

    public data(String name, String price, String key) {
        this.name = name;
        this.price = price;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name:" + name + "\n Price : "+price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
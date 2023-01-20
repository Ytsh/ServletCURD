package org.example.model.dto;

public class Productss {
    private float price;
    private int id;
    private String productName;
    private String productDescription;
    private String image;


    public Productss(float price, int id, String productName, String productDescription, String image) {
        this.price = price;
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.image = image;
    }

}

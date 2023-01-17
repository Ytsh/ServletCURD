package org.example.model.dto;

import org.example.model.Category;

public class ProductDTO {

    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private int id;
    private String productName;
    private String productDescription;
    private String image;
    private Category category;

    public ProductDTO(int id, String productName, String productDescription, String image, float price, Category category) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public ProductDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

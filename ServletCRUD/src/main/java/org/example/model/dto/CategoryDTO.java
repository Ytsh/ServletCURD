package org.example.model.dto;

import java.util.List;

public class CategoryDTO {
    private int id;
    private String categoryName;
    private String categoryDescription;
    private List<Productss> product;

    public CategoryDTO(int id, String categoryName, String categoryDescription, List<Productss> product) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<Productss> getProduct() {
        return product;
    }

    public void setProduct(List<Productss> product) {
        this.product = product;
    }
}

package org.example.model.dto;

import java.util.List;

public class userDTO {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private List<Integer> shoppingCartId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(List<Integer> shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}

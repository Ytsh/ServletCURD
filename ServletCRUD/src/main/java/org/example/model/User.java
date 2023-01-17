package org.example.model;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(List<Integer> shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}

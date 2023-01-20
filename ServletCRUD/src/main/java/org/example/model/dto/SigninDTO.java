package org.example.model.dto;

import org.example.model.User;

import java.util.List;

public class SigninDTO {
    private int id;
    private String email;
    private String token;

    public SigninDTO(User user, String token, List<String> role) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.token = token;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    private List<String> role;
}

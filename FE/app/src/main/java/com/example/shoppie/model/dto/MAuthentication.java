package com.example.shoppie.model.dto;

public class MAuthentication
{
    String email;
    String password;
    String id;

    public MAuthentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MAuthentication(String id, String email, String password) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public MAuthentication() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MAuthentication{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

package br.com.duckvault.Models;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isTwoFactorEnabled;
    private LocalDateTime createdAt;

    public User(){}

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTwoFactorEnabled = false;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public boolean isTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        isTwoFactorEnabled = twoFactorEnabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("User[name='%s', email='%s']", name, email);
    }

}

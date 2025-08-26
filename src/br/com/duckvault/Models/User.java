package br.com.duckvault.Models;

import java.sql.Date;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int isTwoFactorEnabled;
    private String createdAt;

    public User(){}

    public User(String name, String email, String password, int isTwoFactorEnabled ){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIsTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public void setIsTwoFactorEnabled(int isTwoFactorEnabled) {
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("User[name='%s', email='%s']", name, email);
    }

}

package com.tubes.dokterly.user;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private static Map<String, Integer> counters = new HashMap<>();
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(String name, String email, String password, String role) {
        int counter = counters.getOrDefault(role, 1);
        int number;

        switch (role) {
            case "Pasien" -> {
                number = 1100 + counter;
                this.id = "PAS" + number;
            }
            case "Dokter" -> {
                number = 2100 + counter;
                this.id = "DOC" + number;
            }
            case "Admin" -> {
                number = 3100 + counter;
                this.id = "ADM" + number;
            }
            default -> throw new IllegalArgumentException("Role tidak valid.");
        }

        counters.put(role, counter + 1);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void display() {
        System.out.println("\n=== Profil Pengguna ===");
        System.out.println("ID       : " + id);
        System.out.println("Nama     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Role     : " + role);
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
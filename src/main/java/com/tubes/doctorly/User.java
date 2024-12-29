
package com.tubes.doctorly;
import java.util.HashMap;

public abstract class User {
    private static HashMap<String, Integer> counters = new HashMap<>();
    private String id;
    private String name;
    private String password;
    private String role;
    private String email;
    
    public User(String name, String email, String password, String role) {
        int number;
        int counter = counters.getOrDefault(role, 1);
        switch(role){
            case "Pasien" -> {
                number = 1100 + counter;
                this.id = "PAS" + String.valueOf(number);
            }
            case "Dokter" -> {
                number = 2100 + counter;
                this.id =  "DOC" + String.valueOf(number);
            }
            case "Admin" -> {
                number = 3100 + counter;
                this.id = "ADM" + String.valueOf(number);
            }
        }
        counters.put(role, counter + 1);
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
    }
    
    public void display(){
        System.out.println("ID\t: " + this.id);
        System.out.println("Nama\t: " + this.name);
        System.out.println("Email\t: " + this.email);
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    public void setNama(String nama) {
        this.name = nama;
    }

    
}

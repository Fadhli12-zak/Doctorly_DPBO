package com.tubes.doctorly;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<User> users;

    public RegistrationService() {
        this.users = new ArrayList();
    }
    
    public List<Dokter> getDaftarDokter() {
        List<Dokter> dokterList = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Dokter) {
                dokterList.add((Dokter) user); // Casting ke Dokter
            }
        }
        return dokterList;
    }
    
    public User login(String email, String password){
        for(User user:users){
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
        }
        return null;
    }
    
    
    public void registerPasien(String nama, String email, String password, LocalDate tglLahir, String jenisKelamin){
        if(isEmailRegistered(email)){
            System.out.println("Email sudah terdaftar, silahkan coba yang lain.");
               return;
        }
        
        Pasien pasien = new Pasien(nama, email, password, tglLahir, jenisKelamin);
        users.add(pasien);
        System.out.println("\nRegistrasi berhasil: "+ pasien.getName() + "\n");
    }
    
    public void registerDokter(String nama, String email, String password, String spesialis, String riwayatPendidikan, String tempatPraktik ,Admin admin){
        if (!isAdmin(admin)) {
            System.out.println("Hanya admin yang bisa mendaftarkan dokter!");
            return;
        }

        if (isEmailRegistered(email)) {
            System.out.println("Email sudah terdaftar!");
            return;
        }

        Dokter dokter = new Dokter(nama, email, password, spesialis, riwayatPendidikan, tempatPraktik);
        users.add(dokter);
        
        System.out.println("Registrasi berhasil: " + dokter);
    }
    
    private boolean isEmailRegistered(String email){
        for(User user:users){
            if(user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
        boolean isMatch = password.matches(passwordRegex);
        return isMatch;
    }
    
    public void registerAdmin(String nama, String email, String password, Admin admin) {
        if (!isAdmin(admin)) {
            System.out.println("Hanya admin yang bisa mendaftarkan admin lain!");
            return;
        }

        if (isEmailRegistered(email)) {
            System.out.println("Email sudah terdaftar!");
            return;
        }

        Admin newAdmin = new Admin(nama, email, password);
        users.add(newAdmin);
        System.out.println("Registrasi berhasil: " + newAdmin);
    }
    
    private boolean isAdmin(User user) {
        return user instanceof Admin;
    }

    public void printAllUsers() {
        System.out.println("Daftar Pengguna:");
        for (User user : users) {
            if(user instanceof Pasien){
                user.display();
            } else if(user instanceof Dokter){
                user.display();
            } else if(user instanceof Admin){
                user.display();
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

}

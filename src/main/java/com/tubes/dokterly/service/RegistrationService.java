package com.tubes.dokterly.service;

import com.tubes.dokterly.user.Admin;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import com.tubes.dokterly.user.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<User> users;

    // Konstruktor
    public RegistrationService() {
        this.users = new ArrayList<>();
    }

    // Registrasi Pasien
    public void registerPasien(String nama, String email, String password, LocalDate tglLahir, String jenisKelamin) {
        if (isEmailRegistered(email)) {
            System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
            return;
        }
        Pasien pasien = new Pasien(nama, email, password, tglLahir, jenisKelamin);
        users.add(pasien);
        System.out.println("Pasien berhasil diregistrasi: " + pasien.getName());
    }

    // Registrasi Dokter
    public void registerDokter(String nama, String email, String password, String spesialisasi, String riwayatPendidikan, String tempatPraktik, Admin admin) {
        if (!isAdmin(admin)) {
            System.out.println("Hanya admin yang dapat mendaftarkan dokter.");
            return;
        }
        if (isEmailRegistered(email)) {
            System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
            return;
        }
        Dokter dokter = new Dokter(nama, email, password, spesialisasi, riwayatPendidikan, tempatPraktik);
        users.add(dokter);
        System.out.println("Dokter berhasil diregistrasi: " + dokter.getName());
    }

    // Registrasi Admin
    public void registerAdmin(String nama, String email, String password, Admin admin) {
        if (!isAdmin(admin)) {
            System.out.println("Hanya admin yang dapat mendaftarkan admin baru.");
            return;
        }
        if (isEmailRegistered(email)) {
            System.out.println("Email sudah terdaftar, silakan gunakan email lain.");
            return;
        }
        Admin newAdmin = new Admin(nama, email, password);
        users.add(newAdmin);
        System.out.println("Admin berhasil diregistrasi: " + newAdmin.getName());
    }

    // Login
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Email atau password salah.");
        return null;
    }

    // Validasi Email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Validasi Password
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
        return password.matches(passwordRegex);
    }

    // Cek Email Terdaftar
    private boolean isEmailRegistered(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Cek Apakah User Admin
    private boolean isAdmin(User user) {
        return user instanceof Admin;
    }

    // Mendapatkan Daftar Dokter
    public List<Dokter> getDaftarDokter() {
        List<Dokter> dokterList = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Dokter) {
                dokterList.add((Dokter) user);
            }
        }
        return dokterList;
    }

    // Menampilkan Semua Pengguna
    public void tampilkanSemuaPengguna() {
        System.out.println("\n=== Daftar Pengguna ===");
        for (User user : users) {
            user.display();
            System.out.println("-------------------------");
        }
    }

    // Mendapatkan Semua User
    public List<User> getUsers() {
        return users;
    }
}

package com.tubes.dokterly.user;

import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.communication.Chat;
import com.tubes.dokterly.service.RegistrationService;
import com.tubes.dokterly.medical.RekamMedis;
import com.tubes.dokterly.medical.Transaksi;
import com.tubes.dokterly.utils.Utils;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pasien extends User {

    private LocalDate tglLahir;
    private int usia;
    private String jenisKelamin;
    private String riwayatKesehatan;
    private String alergi;
    private boolean infoCheck;
    private List<Asuransi> daftarAsuransi;
    private List<Transaksi> riwayatTransaksi;
    private List<RekamMedis> rekamMedis;

    // Konstruktor
    public Pasien(String name, String email, String password, LocalDate tglLahir, String jenisKelamin) {
        super(name, email, password, "Pasien");
        this.tglLahir = tglLahir;
        this.usia = hitungUsia(tglLahir);
        this.jenisKelamin = jenisKelamin;
        this.riwayatKesehatan = "-";
        this.alergi = "-";
        this.infoCheck = false;
        this.daftarAsuransi = new ArrayList<>();
        this.riwayatTransaksi = new ArrayList<>();
        this.rekamMedis = new ArrayList<>();
    }

    // Getter dan Setter
    public LocalDate getTglLahir() {
        return tglLahir;
    }

    public int getUsia() {
        return usia;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setRiwayatKesehatan(String riwayatKesehatan) {
        this.riwayatKesehatan = riwayatKesehatan;
    }

    public String getRiwayatKesehatan() {
        return riwayatKesehatan;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getAlergi() {
        return alergi;
    }

    public boolean isInfoCheck() {
        return infoCheck;
    }

    public void setInfoCheck(boolean infoCheck) {
        this.infoCheck = infoCheck;
    }

    public List<Asuransi> getDaftarAsuransi() {
        return daftarAsuransi;
    }

    public List<Transaksi> getRiwayatTransaksi() {
        return riwayatTransaksi;
    }

    public List<RekamMedis> getRekamMedis() {
        return rekamMedis;
    }

    // Metode untuk menghitung usia
    private int hitungUsia(LocalDate tglLahir) {
        return Period.between(tglLahir, LocalDate.now()).getYears();
    }

    public static void register(RegistrationService regService) {
        Scanner input = new Scanner(System.in);
        String nama;
        String email;
        String password;
        LocalDate tglLahir;
        System.out.println("=== Register ===");
        System.out.print("Nama lengkap               : ");
        nama = input.nextLine();
            
        while (true){
            System.out.print("Email                      : ");
            email = input.nextLine();
                
            if(RegistrationService.isValidEmail(email)){
                break;
            } else {
                System.out.println("    Masukan kembali email yang valid.");
            }
        }
            
        while(true){
            System.out.print("Password                   : ");
            password = input.nextLine();
            if(RegistrationService.isValidPassword(password)){
                break;
            } else {
                System.out.println("    Password harus mengandung 8 karakter huruf dan angka.");
            }
        }
        
        while (true){
            System.out.print("Tanggal Lahir (dd-mm-yyyy) : ");
            String tglLahirIn = input.nextLine();
            tglLahir = Utils.validateDate(tglLahirIn);
            if(tglLahir != null){
                break;
            } else {
                System.out.println("Pastikan tanggal dalam format dd-mm-yyyy.");
            }
        }
            
        System.out.println("Jenis Kelamin              : 1. Laki-Laki");
        System.out.println("                             2. Perempuan");
        System.out.print("Pilih(1/2)                 : ");
        int no = input.nextInt();
        input.nextLine();
        String jenisKelamin;
        switch (no){
            case 1 -> jenisKelamin = "Laki-Laki";
            case 2 -> jenisKelamin = "Perempuan";
            default -> {
                System.out.println("Opsi tidak Valid");
                return;
            }
        }
                       
        regService.registerPasien(nama, email, password, tglLahir, jenisKelamin);
    }
    
    // Metode untuk menambah asuransi
    public void tambahAsuransi(Asuransi asuransi) {
        daftarAsuransi.add(asuransi);
        System.out.println("Asuransi berhasil ditambahkan: " + asuransi.getNamaAsuransi());
    }

    // Metode untuk menampilkan daftar asuransi
    public void lihatAsuransi() {
        if (daftarAsuransi.isEmpty()) {
            System.out.println("Tidak ada asuransi yang dihubungkan.");
        } else {
            System.out.println("\n=== Daftar Asuransi ===");
            for (int i = 0; i < daftarAsuransi.size(); i++) {
                System.out.printf("%d. %s (%s)\n", i + 1, daftarAsuransi.get(i).getNamaAsuransi(), daftarAsuransi.get(i).getJenisAsuransi());
            }
        }
    }

    // Metode untuk menambah transaksi
    public void tambahTransaksi(Transaksi transaksi) {
        riwayatTransaksi.add(transaksi);
        System.out.println("Transaksi berhasil disimpan.");
    }

    // Menampilkan riwayat transaksi
    public void tampilkanRiwayatTransaksi() {
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Tidak ada riwayat transaksi.");
        } else {
            System.out.println("\n=== Riwayat Transaksi ===");
            for (int i = 0; i < riwayatTransaksi.size(); i++) {
                Transaksi transaksi = riwayatTransaksi.get(i);
                System.out.printf("%d. %s | %s | Dokter: %s | Tarif: %s\n", i + 1, transaksi.getTanggal(), transaksi.getMetodePembayaran(), transaksi.getSubject(), transaksi.getFormattedTarif());
            }
        }
    }
    
    
    
    public void chatDokter(Pasien pasien, Dokter dokter) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nMemulai chat dengan Dokter %s...%n", dokter.getName());
        System.out.println("Ketik 'exit' untuk mengakhiri chat.");

        while (true) {
            System.out.print("Anda: ");
            String pesan = input.nextLine();

            if (pesan.equalsIgnoreCase("exit")) {
                System.out.println("Chat diakhiri.");
                break;
            }

            Chat chat = new Chat(this, dokter, pesan);
            dokter.tambahChat(chat);
            System.out.println(dokter.getName() + ": Pesan Anda diterima. Silakan lanjutkan.\n");
        }
    }
    public void lihatRekamMedis() {
        if (rekamMedis.isEmpty()) {
            System.out.println("Belum ada rekam medis.");
        } else {
            System.out.println("\n=== Rekam Medis ===");
            for (RekamMedis rm : rekamMedis) {
                System.out.println(rm);
            }
        }
    }
    
    // Metode untuk menambah rekam medis
    public void tambahRekamMedis(RekamMedis rekam) {
        rekamMedis.add(rekam);
    }

    // Menampilkan rekam medis
    public void tampilkanRekamMedis() {
        if (rekamMedis.isEmpty()) {
            System.out.println("Tidak ada rekam medis.");
        } else {
            System.out.println("\n=== Rekam Medis ===");
            for (int i = 0; i < rekamMedis.size(); i++) {
                RekamMedis rekam = rekamMedis.get(i);
                System.out.printf("%d. %s | Diagnosis: %s | Catatan: %s\n", i + 1, rekam.getTanggal(), rekam.getDiagnosis(), rekam.getCatatan());
            }
        }
    }

    // Menampilkan profil lengkap pasien
    @Override
    public void display() {
        super.display();
        System.out.println("Tanggal Lahir  : " + tglLahir);
        System.out.println("Usia           : " + usia);
        System.out.println("Jenis Kelamin  : " + jenisKelamin);
        System.out.println("Riwayat Kesehatan : " + riwayatKesehatan);
        System.out.println("Alergi         : " + alergi);
        System.out.println("Asuransi       : " + (daftarAsuransi.isEmpty() ? "Tidak ada" : daftarAsuransi.size() + " terhubung\n"));
    }

    // Mengelola profil pasien
    public void editProfile() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Edit Profil ===");
            System.out.println("1. Nama");
            System.out.println("2. Tanggal Lahir");
            System.out.println("3. Jenis Kelamin");
            System.out.println("4. Riwayat Kesehatan");
            System.out.println("5. Alergi");
            System.out.println("6. Selesai");
            System.out.print("Pilih data yang ingin diubah (1-6): ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan nama baru: ");
                    setName(input.nextLine());
                    System.out.println("Nama berhasil diubah.");
                }
                case 2 -> {
                    System.out.print("Masukkan tanggal lahir baru (yyyy-MM-dd): ");
                    try {
                        LocalDate newTglLahir = LocalDate.parse(input.nextLine());
                        this.tglLahir = newTglLahir;
                        this.usia = hitungUsia(newTglLahir);
                        System.out.println("Tanggal lahir berhasil diubah.");
                    } catch (Exception e) {
                        System.out.println("Format tanggal tidak valid.");
                    }
                }
                case 3 -> {
                    System.out.print("Masukkan jenis kelamin baru (L/P): ");
                    this.jenisKelamin = input.nextLine();
                    System.out.println("Jenis kelamin berhasil diubah.");
                }
                case 4 -> {
                    System.out.print("Masukkan riwayat kesehatan baru: ");
                    this.riwayatKesehatan = input.nextLine();
                    System.out.println("Riwayat kesehatan berhasil diubah.");
                }
                case 5 -> {
                    System.out.print("Masukkan alergi baru: ");
                    this.alergi = input.nextLine();
                    System.out.println("Alergi berhasil diubah.");
                }
                case 6 -> {
                    System.out.println("Selesai mengedit profil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
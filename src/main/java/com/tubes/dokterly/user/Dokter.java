package com.tubes.dokterly.user;

import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Chat;
import com.tubes.dokterly.medical.JanjiTemu;
import com.tubes.dokterly.medical.RekamMedis;
import com.tubes.dokterly.medical.Review;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Dokter extends User {
    private String spesialisasi;
    private String riwayatPendidikan;
    private String tempatPraktik;
    private int tarif;
    private List<Review> reviews;
    private List<Pasien> daftarPasien;
    private List<Pasien> daftarPasienJT;
    private List<Article> artikelEdukasi;
    private List<Chat> daftarChat;
    private List<JanjiTemu> daftarJanjiTemu;

    // Konstruktor
    public Dokter(String name, String email, String password, String spesialisasi, String riwayatPendidikan, String tempatPraktik) {
        super(name, email, password, "Dokter");
        this.spesialisasi = spesialisasi;
        this.riwayatPendidikan = riwayatPendidikan;
        this.tempatPraktik = tempatPraktik;
        this.tarif = spesialisasi.equalsIgnoreCase("Umum") ? 9500 : 75000;
        this.reviews = new ArrayList<>();
        this.daftarPasien = new ArrayList<>();
        this.artikelEdukasi = new ArrayList<>();
        this.daftarChat = new ArrayList<>();
        this.daftarJanjiTemu = new ArrayList<>();
        this.daftarPasienJT = new ArrayList<>();
    }

    // Getter dan Setter
    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getRiwayatPendidikan() {
        return riwayatPendidikan;
    }

    public void setRiwayatPendidikan(String riwayatPendidikan) {
        this.riwayatPendidikan = riwayatPendidikan;
    }

    public String getTempatPraktik() {
        return tempatPraktik;
    }

    public void setTempatPraktik(String tempatPraktik) {
        this.tempatPraktik = tempatPraktik;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Pasien> getDaftarPasien() {
        return daftarPasien;
    }

    public List<Article> getArtikelEdukasi() {
        return artikelEdukasi;
    }
    
    public List<Chat> getDaftarChat() {
        return daftarChat;
    }

    public List<JanjiTemu> getDaftarJanjiTemu() {
        return daftarJanjiTemu;
    }

    public List<Pasien> getDaftarPasienJT() {
        return daftarPasienJT;
    }
    
    
    // Menambah review dari pasien
    public void addReview(Review review) {
        reviews.add(review);
        System.out.println("\nTerima kasih atas ulasannya.");
    }

    // Menampilkan semua review
    public void displayReviews() {
        if (reviews.isEmpty()) {
            System.out.println("\nBelum ada ulasan untuk dokter ini.");
        } else {
            System.out.println("\n=== Ulasan Pasien ===");
            for (Review review : reviews) {
                System.out.println(review);
            }
        }
    }
    
    public double avgRate() {
        if (reviews.isEmpty()) {
            return 0; // Tidak ada ulasan dan Rating
        }
        int totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        return (double) totalRating / reviews.size();
    }

    // Menambahkan pasien ke daftar pasien
    public void tambahPasien(Pasien pasien) {
        if (!daftarPasien.contains(pasien)) {
            daftarPasien.add(pasien);
            System.out.println("\nPasien berhasil ditambahkan ke daftar.\n");
        } else {
            System.out.println("\nPasien sudah ada dalam daftar.\n");
        }
    }
    
    public void tambahPasienJT(Pasien pasien) {
        if (!daftarPasienJT.contains(pasien)) {
            daftarPasienJT.add(pasien);
            System.out.println("\nPasien berhasil ditambahkan ke daftar.\n");
        } else {
            System.out.println("\nPasien sudah ada dalam daftar.\n");
        }
    }
    
    public void tambahJanjiTemu(JanjiTemu jt) {
        daftarJanjiTemu.add(jt);
    }
    
    public void tambahChat(Chat chat) {
        daftarChat.add(chat);
        System.out.printf("Pesan dari %s berhasil disimpan.%n", chat.getPengirim().getName());
    }

    // Menampilkan daftar pasien
    public void tampilkanDaftarPasien() {
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada pasien dalam daftar.");
        } else {
            System.out.println("\n=== Daftar Pasien ===");
            for (int i = 0; i < daftarPasien.size(); i++) {
                Pasien pasien = daftarPasien.get(i);
                System.out.printf("%d. %s (ID: %s)\n", i + 1, pasien.getName(), pasien.getId());
            }
        }
    }
    

    // Menambahkan artikel edukasi
    public void tambahArtikel(Article artikel) {
        artikelEdukasi.add(artikel);
        System.out.println("Artikel berhasil ditambahkan.");
    }

    // Menampilkan semua artikel edukasi
    public void tampilkanArtikel() {
        if (artikelEdukasi.isEmpty()) {
            System.out.println("Belum ada artikel yang diunggah.");
        } else {
            System.out.println("\n=== Artikel Edukasi ===");
            for (Article artikel : artikelEdukasi) {
                artikel.displayArticle();
            }
        }
    }

    // Mengelola profil dokter
    public void editProfile() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Edit Profil Dokter ===");
            System.out.println("1. Nama");
            System.out.println("2. Spesialisasi");
            System.out.println("3. Riwayat Pendidikan");
            System.out.println("4. Tempat Praktik");
            System.out.println("5. Selesai");
            System.out.print("Pilih data yang ingin diubah (1-5): ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan nama baru: ");
                    setName(input.nextLine());
                    System.out.println("Nama berhasil diubah.");
                }
                case 2 -> {
                    System.out.print("Masukkan spesialisasi baru: ");
                    this.spesialisasi = input.nextLine();
                    System.out.println("Spesialisasi berhasil diubah.");
                }
                case 3 -> {
                    System.out.print("Masukkan riwayat pendidikan baru: ");
                    this.riwayatPendidikan = input.nextLine();
                    System.out.println("Riwayat pendidikan berhasil diubah.");
                }
                case 4 -> {
                    System.out.print("Masukkan tempat praktik baru: ");
                    this.tempatPraktik = input.nextLine();
                    System.out.println("Tempat praktik berhasil diubah.");
                }
                case 5 -> {
                    System.out.println("Selesai mengedit profil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Menampilkan profil lengkap dokter
    @Override
    public void display() {
        super.display();
        System.out.println("Spesialisasi   : " + spesialisasi);
        System.out.println("Pendidikan     : " + riwayatPendidikan);
        System.out.println("Tempat Praktik : " + tempatPraktik);
        System.out.println("Tarif          : Rp " + tarif);
    }
}

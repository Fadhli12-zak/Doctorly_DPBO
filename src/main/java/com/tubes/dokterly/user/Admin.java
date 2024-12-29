package com.tubes.dokterly.user;

import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.ecommerce.Obat;
import com.tubes.dokterly.ecommerce.Olshop;
import com.tubes.dokterly.service.RegistrationService;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String name, String email, String password) {
        super(name, email, password, "Admin");
    }

    // Registrasi pengguna baru (Dokter atau Admin)
    public void registerUser(RegistrationService reg) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== Registrasi Pengguna Baru ===");
        System.out.println("1. Dokter");
        System.out.println("2. Admin");
        System.out.print("Pilih jenis pengguna (1/2): ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan == 1) {
            System.out.print("Nama Dokter: ");
            String nama = input.nextLine();

            System.out.print("Email Dokter: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            System.out.print("Spesialisasi: ");
            String spesialisasi = input.nextLine();

            System.out.print("Riwayat Pendidikan: ");
            String pendidikan = input.nextLine();

            System.out.print("Tempat Praktik: ");
            String tempatPraktik = input.nextLine();

            reg.registerDokter(nama, email, password, spesialisasi, pendidikan, tempatPraktik, this);
        } else if (pilihan == 2) {
            System.out.print("Nama Admin: ");
            String nama = input.nextLine();

            System.out.print("Email Admin: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            reg.registerAdmin(nama, email, password, this);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    // Menambahkan asuransi baru
    public void tambahAsuransi() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== Tambah Asuransi Baru ===");
        System.out.print("Nama Asuransi: ");
        String namaAsuransi = input.nextLine();

        System.out.print("Nomor Polis: ");
        String nomorPolis = input.nextLine();

        System.out.print("Jenis Asuransi (BPJS/Swasta/Syariah): ");
        String jenisAsuransi = input.nextLine();

        Asuransi asuransiBaru = new Asuransi(namaAsuransi, nomorPolis, jenisAsuransi);
        Asuransi.tambahAsuransiBaru(asuransiBaru);

        System.out.println("Asuransi berhasil ditambahkan.");
    }

    // Menampilkan semua pengguna
    public void tampilkanSemuaPengguna(List<User> users) {
        System.out.println("\n=== Daftar Semua Pengguna ===");
        if (users.isEmpty()) {
            System.out.println("Tidak ada pengguna yang terdaftar.");
        } else {
            for (User user : users) {
                user.display();
                System.out.println("-------------------------");
            }
        }
    }

    // Mengelola artikel kesehatan
    public void kelolaArtikel() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Kelola Artikel Kesehatan ===");
            System.out.println("1. Tambah Artikel");
            System.out.println("2. Lihat Semua Artikel");
            System.out.println("3. Hapus Artikel");
            System.out.println("4. Kembali");
            System.out.print("Pilih opsi: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Judul Artikel: ");
                    String judul = input.nextLine();

                    System.out.print("Konten Artikel: ");
                    String konten = input.nextLine();
                    
                    System.out.print("Author Artikel: ");
                    String author = input.nextLine();

                    Article.tambahArtikel(new Article(judul, konten, author));
                    System.out.println("Artikel berhasil ditambahkan.");
                }
                case 2 -> {
                    if (Article.getArticles().isEmpty()) {
                        System.out.println("Belum ada artikel yang tersedia.");
                    } else {
                        System.out.println("\n=== Daftar Artikel ===");
                        for (int i = 0; i < Article.getArticles().size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, Article.getArticles().get(i).getTitle());
                        }
                    }
                }
                case 3 -> {
                    if (Article.getArticles().isEmpty()) {
                        System.out.println("Belum ada artikel yang tersedia.");
                    } else {
                        System.out.print("Masukkan nomor artikel yang ingin dihapus: ");
                        int nomor = input.nextInt();
                        input.nextLine(); // Konsumsi newline

                        if (nomor > 0 && nomor <= Article.getArticles().size()) {
                            Article.getArticles().remove(nomor - 1);
                            System.out.println("Artikel berhasil dihapus.");
                        } else {
                            System.out.println("Nomor artikel tidak valid.");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Kembali ke menu utama.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    public void tambahProdukOlshop(Olshop olshop) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama produk: ");
        String nama = input.nextLine();
        System.out.print("Masukkan deskripsi produk: ");
        String deskripsi = input.nextLine();
        System.out.print("Masukkan harga produk: ");
        int harga = input.nextInt();
        System.out.print("Masukkan stok produk: ");
        int stok = input.nextInt();
        input.nextLine(); // Konsumsi newline
        System.out.print("Apakah produk memerlukan resep? (true/false): ");
        boolean resepDiperlukan = input.nextBoolean();

        Obat obat = new Obat(nama, deskripsi, harga, stok, resepDiperlukan);
        olshop.tambahProduk(obat);
        System.out.println("Produk berhasil ditambahkan ke olshop.");
    }

    @Override
    public void display() {
        System.out.println("\n=== Profil Admin ===");
        super.display();
    }
}

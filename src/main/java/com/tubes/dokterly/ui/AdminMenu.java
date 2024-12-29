package com.tubes.dokterly.ui;

import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.ui.Menu;
import com.tubes.dokterly.medical.MetodePembayaran;
import com.tubes.dokterly.ecommerce.Obat;
import com.tubes.dokterly.ecommerce.Olshop;
import com.tubes.dokterly.ecommerce.Olshop;
import com.tubes.dokterly.service.RegistrationService;
import com.tubes.dokterly.service.RegistrationService;
import com.tubes.dokterly.user.Admin;
import java.util.Scanner;
import java.util.List;

public class AdminMenu implements Menu {
    private Admin admin;
    private RegistrationService regService;

    public AdminMenu(Admin admin, RegistrationService regService) {
        this.admin = admin;
        this.regService = regService;
    }

    @Override
    public void displayMenu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Lihat Semua Pengguna");
            System.out.println("2. Tambah Pengguna");
            System.out.println("3. Kelola Asuransi");
            System.out.println("4. Kelola Artikel");
            System.out.println("5. Kelola Olshop");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> lihatSemuaPengguna();
                case 2 -> admin.registerUser(regService);
                case 3 -> kelolaAsuransi();
                case 4 -> admin.kelolaArtikel();
                case 5 -> kelolaOlshop();
                case 6 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void lihatSemuaPengguna() {
        regService.tampilkanSemuaPengguna();
    }

    private void kelolaAsuransi() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Kelola Asuransi ===");
            System.out.println("1. Lihat Semua Asuransi");
            System.out.println("2. Tambah Asuransi Baru");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> Asuransi.tampilkanDaftarAsuransi();
                case 2 -> admin.tambahAsuransi();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void kelolaOlshop() {
         Scanner input = new Scanner(System.in);
        Olshop olshop = new Olshop();
        while (true) {
            System.out.println("\n=== Kelola Olshop ===");
            System.out.println("1. Lihat Semua Produk");
            System.out.println("2. Tambah Produk Baru");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> olshop.tampilkanProduk();
                case 2 -> admin.tambahProdukOlshop(olshop);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
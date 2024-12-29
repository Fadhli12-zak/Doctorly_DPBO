package com.tubes.dokterly.ecommerce;

import com.tubes.dokterly.medical.MetodePembayaran;
import com.tubes.dokterly.medical.Transaksi;
import com.tubes.dokterly.utils.Utils;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Olshop {
    private List<Obat> daftarObat;

    // Konstruktor
    public Olshop() {
        this.daftarObat = new ArrayList<>();
    }

    // Menambah produk ke daftar
    public void tambahProduk(Obat produk) {
        daftarObat.add(produk);
        System.out.println("Produk berhasil ditambahkan: " + produk.getNama());
    }

    // Menampilkan semua produk
    public void tampilkanProduk() {
        if (daftarObat.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia di toko.");
        } else {
            System.out.println("\n=== Daftar Produk ===");
            for (int i = 0; i < daftarObat.size(); i++) {
                Obat produk = daftarObat.get(i);
                System.out.printf("%d. %s - Rp %s | Stok: %d%n",
                        i + 1, produk.getNama(), Utils.tarifFormat(produk.getHarga()), produk.getStok());
            }
        }
    }

    // Membeli produk
    public void beliProduk(Pasien pasien) {
        Scanner input = new Scanner(System.in);
        tampilkanProduk();

        if (daftarObat.isEmpty()) {
            return; // Tidak ada produk untuk dibeli
        }

        System.out.print("Masukkan nomor produk yang ingin dibeli: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan < 1 || pilihan > daftarObat.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Obat produkDipilih = daftarObat.get(pilihan - 1);

        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        int jumlah = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (jumlah > produkDipilih.getStok()) {
            System.out.println("Stok tidak mencukupi.");
        } else {
            int totalHarga = produkDipilih.getHarga() * jumlah;
            System.out.printf("Total harga: %s%n", Utils.tarifFormat(totalHarga));

            System.out.print("Konfirmasi pembelian? (y/n): ");
            String konfirmasi = input.nextLine();

            if (konfirmasi.equalsIgnoreCase("y")) {
                if(pilihMetodePembayaran(pasien, produkDipilih)){
                    produkDipilih.kurangiStok(jumlah);
                    /*Transaksi transaksi = new Transaksi(java.time.LocalDate.now(), "Olshop", produkDipilih.getNama(), totalHarga);
                    pasien.tambahTransaksi(transaksi);*/
                    System.out.println("Pembelian berhasil. Terima kasih telah berbelanja di toko kami.");
                }
            } else {
                System.out.println("Pembelian dibatalkan.");
            }
        }
    }
    
    public boolean pilihMetodePembayaran(Pasien pasien, Obat produk) {
        Scanner input = new Scanner(System.in);
        List<MetodePembayaran> metodeList = MetodePembayaran.getMetodePembayaran();

        int tarif = produk.getHarga();
        System.out.printf("\nTarif untuk konsultasi adalah %s.\n", Utils.tarifFormat(tarif), "\n");
        System.out.println("Pilih metode pembayaran:");
        for (int i = 0; i < metodeList.size(); i++) {
            MetodePembayaran metode = metodeList.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, metode.getNama(), metode.getDeskripsi());
        }
        System.out.println((metodeList.size() + 1) + ". Batal");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan == metodeList.size() + 1) {
            System.out.println("\nPembayaran dibatalkan.\n");
            return false; // Pembayaran batal
        } else if (pilihan > 0 && pilihan <= metodeList.size()) {
            MetodePembayaran metodeTerpilih = metodeList.get(pilihan - 1);
            return Transaksi.prosesPembayaran(pasien, produk, metodeTerpilih, tarif); // Proses pembayaran
        } else {
            System.out.println("Pilihan tidak valid.");
            return false; // Pilihan tidak valid
        }
    }
}
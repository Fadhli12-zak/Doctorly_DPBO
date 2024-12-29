package com.tubes.dokterly.ecommerce;

import com.tubes.dokterly.utils.Utils;

public class Obat {
    private String nama;
    private String deskripsi;
    private int harga;
    private int stok;
    private boolean resepDiperlukan;

    // Konstruktor
    public Obat(String nama, String deskripsi, int harga, int stok, boolean resepDiperlukan) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.stok = stok;
        this.resepDiperlukan = resepDiperlukan;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public boolean isResepDiperlukan() {
        return resepDiperlukan;
    }

    // Setter
    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    // Mengurangi stok obat
    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
        } else {
            throw new IllegalArgumentException("Jumlah tidak valid atau stok tidak mencukupi.");
        }
    }

    @Override
    public String toString() {
        return String.format("Nama: %s | Harga: Rp %s | Stok: %d | Resep Diperlukan: %s",
                nama, Utils.tarifFormat(harga), stok, resepDiperlukan ? "Ya" : "Tidak");
    }
}

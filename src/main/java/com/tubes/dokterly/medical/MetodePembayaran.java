package com.tubes.dokterly.medical;
import java.util.ArrayList;
import java.util.List;

public class MetodePembayaran {
    private String nama;
    private String deskripsi;

    // Konstruktor
    public MetodePembayaran(String nama, String deskripsi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Menyediakan daftar metode pembayaran
    public static List<MetodePembayaran> getMetodePembayaran() {
        List<MetodePembayaran> metodeList = new ArrayList<>();
        metodeList.add(new MetodePembayaran("Transfer Bank", "Pembayaran melalui rekening bank."));
        metodeList.add(new MetodePembayaran("Kartu Kredit", "Gunakan kartu kredit Anda."));
        metodeList.add(new MetodePembayaran("Debit", "Pembayaran langsung dari kartu debit."));
        metodeList.add(new MetodePembayaran("Dompet Digital", "Gunakan saldo e-wallet Anda."));
        metodeList.add(new MetodePembayaran("Asuransi", "Gunakan asuransi yang telah terdaftar."));
        return metodeList;
    }

    // Menampilkan daftar metode pembayaran
    public static void tampilkanMetodePembayaran() {
        List<MetodePembayaran> metodeList = getMetodePembayaran();
        System.out.println("\n=== Metode Pembayaran ===");
        for (int i = 0; i < metodeList.size(); i++) {
            MetodePembayaran metode = metodeList.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, metode.getNama(), metode.getDeskripsi());
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s", nama, deskripsi);
    }
}

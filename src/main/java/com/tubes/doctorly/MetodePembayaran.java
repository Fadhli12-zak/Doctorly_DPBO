
package com.tubes.doctorly;
import java.util.*;

public class MetodePembayaran {
    private String nama;
    private String deskripsi;

    public MetodePembayaran(String nama, String deskripsi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Dummy Data Metode Pembayaran
    public static List<MetodePembayaran> getMetodePembayaran() {
        List<MetodePembayaran> metodeList = new ArrayList<>();
        metodeList.add(new MetodePembayaran("Transfer Bank", "Pembayaran melalui rekening bank"));
        metodeList.add(new MetodePembayaran("Kartu Kredit", "Gunakan kartu kredit Anda"));
        metodeList.add(new MetodePembayaran("Debit", "Pembayaran langsung dari kartu debit"));
        metodeList.add(new MetodePembayaran("Dompet Digital", "Gunakan saldo e-wallet Anda"));
        metodeList.add(new MetodePembayaran("Asuransi", "Gunakan asuransi yang terdaftar"));
        return metodeList;
    }

}

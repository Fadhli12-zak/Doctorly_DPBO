package com.tubes.doctorly;

import java.util.*;
import java.time.LocalDate;

public class Transaksi {
    private LocalDate tanggal;
    private String metodePembayaran;
    private String namaDokter;
    private int tarif;

    public Transaksi(LocalDate tanggal, String metodePembayaran, String namaDokter, int tarif) {
        this.tanggal = tanggal;
        this.metodePembayaran = metodePembayaran;
        this.namaDokter = namaDokter;
        this.tarif = tarif;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public int getTarif() {
        return tarif;
    }

    public String getFormattedTarif() {
        return Utils.tarifFormat(tarif);
    }
    
    public static boolean prosesPembayaran(Pasien pasien, Dokter dokter, MetodePembayaran metode, int tarif) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nAnda memilih metode: %s.\n", metode.getNama());
        switch (metode.getNama()) {
            case "Transfer Bank" -> {
                System.out.print("Masukkan nomor rekening: ");
                String nomorRekening = input.nextLine();
                System.out.println("Pembayaran melalui Transfer Bank berhasil.");
            }
            case "Kartu Kredit" -> {
                System.out.print("Masukkan nomor kartu kredit: ");
                String nomorKartu = input.nextLine();
                System.out.println("Pembayaran melalui Kartu Kredit berhasil.");
            }
            case "Debit" -> {
                System.out.print("Masukkan nomor kartu debit: ");
                String nomorDebit = input.nextLine();
                System.out.println("Pembayaran melalui Debit berhasil.");
            }
            case "Dompet Digital" -> {
                System.out.print("Masukkan ID Dompet Digital Anda: ");
                String idDompet = input.nextLine();
                System.out.println("Pembayaran melalui Dompet Digital berhasil.");
            }
            case "Asuransi" -> {
                if (!validasiAsuransi(pasien, tarif)) {
                    System.out.println("Asuransi tidak mencakup biaya ini.");
                    return false; // Pembayaran gagal
                }
                System.out.println("Pembayaran melalui Asuransi berhasil.");
            }
            default -> {
                System.out.println("Metode pembayaran tidak valid.");
                return false;
            }
        }

        // Simpan transaksi ke riwayat pasien
        Transaksi transaksi = new Transaksi(LocalDate.now(), metode.getNama(), dokter.getName(), tarif);
        pasien.tambahTransaksi(transaksi);

        return true; // Pembayaran berhasil
    }
    
    public static boolean validasiAsuransi(Pasien pasien, int tarif) {
        if (pasien.getDaftarAsuransi().isEmpty()) {
            System.out.println("Anda tidak memiliki asuransi terdaftar.");
            return false;
        }
        System.out.println("Memeriksa asuransi...");
        // Validasi sederhana untuk simulasi
        return tarif <= 100000; // Contoh: batas tarif yang ditanggung asuransi
    }
}

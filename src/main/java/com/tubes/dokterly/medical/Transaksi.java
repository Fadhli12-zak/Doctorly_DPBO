package com.tubes.dokterly.medical;

import com.tubes.dokterly.ecommerce.Obat;
import com.tubes.dokterly.utils.Utils;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import java.time.LocalDate;
import java.util.Scanner;

public class Transaksi {
    private LocalDate tanggal;
    private String metodePembayaran;
    private String subject;
    private int tarif;

    // Konstruktor
    public Transaksi(LocalDate tanggal, String metodePembayaran, String subject, int tarif) {
        this.tanggal = tanggal;
        this.metodePembayaran = metodePembayaran;
        this.subject = subject;
        this.tarif = tarif;
    }

    // Getter
    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public String getSubject() {
        return subject;
    }

    public int getTarif() {
        return tarif;
    }

    public String getFormattedTarif() {
        return Utils.tarifFormat(tarif);
    }

    // Proses pembayaran
    public static boolean prosesPembayaran(Pasien pasien, Dokter dokter, MetodePembayaran metode, int tarif) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nAnda memilih metode: %s.%n", metode.getNama());

        switch (metode.getNama()) {
            case "Transfer Bank" -> {
                System.out.print("Masukkan nomor rekening: ");
                String nomorRekening = input.nextLine();
                System.out.println("\nPembayaran melalui Transfer Bank berhasil.\n");
            }
            case "Kartu Kredit" -> {
                System.out.print("Masukkan nomor kartu kredit: ");
                String nomorKartu = input.nextLine();
                System.out.println("\nPembayaran melalui Kartu Kredit berhasil.\n");
            }
            case "Debit" -> {
                System.out.print("Masukkan nomor kartu debit: ");
                String nomorDebit = input.nextLine();
                System.out.println("\nPembayaran melalui Debit berhasil.\n");
            }
            case "Dompet Digital" -> {
                System.out.print("Masukkan ID Dompet Digital Anda: ");
                String idDompet = input.nextLine();
                System.out.println("\nPembayaran melalui Dompet Digital berhasil.\n");
            }
            case "Asuransi" -> {
                if (!validasiAsuransi(pasien, tarif)) {
                    System.out.println("\nAsuransi tidak mencakup biaya ini.\n");
                    return false; // Pembayaran gagal
                }
                System.out.println("\nPembayaran melalui Asuransi berhasil.\n");
            }
            default -> {
                System.out.println("\nMetode pembayaran tidak valid.\n");
                return false;
            }
        }

        // Simpan transaksi ke riwayat pasien
        Transaksi transaksi = new Transaksi(LocalDate.now(), metode.getNama(), dokter.getName(), tarif);
        pasien.tambahTransaksi(transaksi);

        return true; // Pembayaran berhasil
    }
    
    public static boolean prosesPembayaran(Pasien pasien, Obat produk, MetodePembayaran metode, int tarif) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nAnda memilih metode: %s.%n", metode.getNama());

        switch (metode.getNama()) {
            case "Transfer Bank" -> {
                System.out.print("Masukkan nomor rekening: ");
                String nomorRekening = input.nextLine();
                System.out.println("\nPembayaran melalui Transfer Bank berhasil.\n");
            }
            case "Kartu Kredit" -> {
                System.out.print("Masukkan nomor kartu kredit: ");
                String nomorKartu = input.nextLine();
                System.out.println("\nPembayaran melalui Kartu Kredit berhasil.\n");
            }
            case "Debit" -> {
                System.out.print("Masukkan nomor kartu debit: ");
                String nomorDebit = input.nextLine();
                System.out.println("\nPembayaran melalui Debit berhasil.\n");
            }
            case "Dompet Digital" -> {
                System.out.print("Masukkan ID Dompet Digital Anda: ");
                String idDompet = input.nextLine();
                System.out.println("\nPembayaran melalui Dompet Digital berhasil.\n");
            }
            case "Asuransi" -> {
                if (!validasiAsuransi(pasien, tarif)) {
                    System.out.println("\nAsuransi tidak mencakup biaya ini.\n");
                    return false; // Pembayaran gagal
                }
                System.out.println("\nPembayaran melalui Asuransi berhasil.\n");
            }
            default -> {
                System.out.println("\nMetode pembayaran tidak valid.\n");
                return false;
            }
        }

        // Simpan transaksi ke riwayat pasien
        Transaksi transaksi = new Transaksi(LocalDate.now(), metode.getNama(), produk.getNama(), tarif);
        pasien.tambahTransaksi(transaksi);

        return true; // Pembayaran berhasil
    }

    // Validasi asuransi
    public static boolean validasiAsuransi(Pasien pasien, int tarif) {
        if (pasien.getDaftarAsuransi().isEmpty()) {
            System.out.println("Anda tidak memiliki asuransi terdaftar.");
            return false;
        }
        System.out.println("Memeriksa asuransi...");
        // Validasi sederhana untuk simulasi
        return tarif <= 100000; // Contoh: batas tarif yang ditanggung asuransi
    }

    @Override
    public String toString() {
        return String.format("Tanggal: %s | Metode: %s | Dokter: %s | Tarif: %s",
                tanggal, metodePembayaran, subject, getFormattedTarif());
    }
}

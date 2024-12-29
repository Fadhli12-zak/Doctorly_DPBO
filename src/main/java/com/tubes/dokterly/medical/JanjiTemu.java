package com.tubes.dokterly.medical;

import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JanjiTemu {
    private LocalDate tanggal;
    private LocalTime waktu;
    private String namaDokter;
    private String namaPasien;
    private String status; // Contoh: "Dijadwalkan", "Selesai", "Dibatalkan"

    // Konstruktor
    public JanjiTemu(LocalDate tanggal, LocalTime waktu, String namaDokter, String namaPasien) {
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.namaDokter = namaDokter;
        this.namaPasien = namaPasien;
        this.status = "Dijadwalkan";
    }

    // Getter dan Setter
    public LocalDate getTanggal() {
        return tanggal;
    }

    public LocalTime getWaktu() {
        return waktu;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Menjadwalkan janji temu baru
    public static JanjiTemu buatJanjiTemu(Pasien pasien, Dokter dokter) {
       Scanner input = new Scanner(System.in);
        System.out.println("\n=== Buat Janji Temu ===");

        LocalDate tanggal = null;
        while (tanggal == null) {
            System.out.print("Masukkan tanggal (yyyy-MM-dd): ");
            try {
                tanggal = LocalDate.parse(input.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah. Harap masukkan dengan format yyyy-MM-dd.");
            }
        }

        LocalTime waktu = null;
        while (waktu == null) {
            System.out.print("Masukkan waktu (HH:mm): ");
            try {
                waktu = LocalTime.parse(input.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Format waktu salah. Harap masukkan dengan format HH:mm.");
            }
        }

        System.out.printf("Janji temu dengan Dokter %s pada %s pukul %s berhasil dijadwalkan.%n", 
                dokter.getName(), tanggal, waktu);

        return new JanjiTemu(tanggal, waktu, dokter.getName(), pasien.getName());
    }

    // Menampilkan detail janji temu
    public void tampilkanDetail() {
        System.out.printf("\n=== Detail Janji Temu ===%nTanggal: %s%nWaktu: %s%nDokter: %s%nPasien: %s%nStatus: %s%n",
                tanggal, waktu, namaDokter, namaPasien, status);
    }

    // Membatalkan janji temu
    public void batalkanJanjiTemu() {
        this.status = "Dibatalkan";
        System.out.println("Janji temu berhasil dibatalkan.");
    }

    // Menandai janji temu selesai
    public void selesaiJanjiTemu() {
        this.status = "Selesai";
        System.out.println("Janji temu telah selesai.");
    }
}

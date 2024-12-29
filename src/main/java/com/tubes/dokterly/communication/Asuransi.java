package com.tubes.dokterly.communication;

import java.util.*;

public class Asuransi {
    private static final Map<String, Asuransi> asuransiTerdaftar = new HashMap<>();

    private String namaAsuransi;
    private String nomorPolis;
    private String jenisAsuransi; // Contoh: "Swasta", "BPJS", "Syariah"

    // Konstruktor
    public Asuransi(String namaAsuransi, String nomorPolis, String jenisAsuransi) {
        this.namaAsuransi = namaAsuransi;
        this.nomorPolis = nomorPolis;
        this.jenisAsuransi = jenisAsuransi;
    }

    // Getter
    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public String getNomorPolis() {
        return nomorPolis;
    }

    public String getJenisAsuransi() {
        return jenisAsuransi;
    }

    // Menambahkan asuransi baru ke daftar terdaftar
    public static void tambahAsuransiBaru(Asuransi asuransi) {
        if (asuransiTerdaftar.containsKey(asuransi.getNomorPolis())) {
            System.out.println("Asuransi dengan nomor polis ini sudah terdaftar.");
        } else {
            asuransiTerdaftar.put(asuransi.getNomorPolis(), asuransi);
            System.out.println("Asuransi berhasil ditambahkan: " + asuransi.getNamaAsuransi());
        }
    }

    // Mendapatkan daftar semua asuransi
    public static List<Asuransi> getDaftarAsuransi() {
        return new ArrayList<>(asuransiTerdaftar.values());
    }

    // Mencari asuransi berdasarkan nomor polis
    public static Asuransi getAsuransiByNomorPolis(String nomorPolis) {
        return asuransiTerdaftar.get(nomorPolis);
    }

    // Menampilkan semua asuransi yang terdaftar
    public static void tampilkanDaftarAsuransi() {
        if (asuransiTerdaftar.isEmpty()) {
            System.out.println("Belum ada asuransi yang terdaftar.");
        } else {
            System.out.println("\n=== Daftar Asuransi Terdaftar ===");
            int index = 1;
            for (Asuransi asuransi : asuransiTerdaftar.values()) {
                System.out.printf("%d. %s | Nomor Polis: %s | Jenis: %s\n",
                        index++, asuransi.getNamaAsuransi(), asuransi.getNomorPolis(), asuransi.getJenisAsuransi());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Nomor Polis: %s, Jenis: %s)", namaAsuransi, nomorPolis, jenisAsuransi);
    }
}

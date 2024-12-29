package com.tubes.doctorly;

import java.util.*;

public class Asuransi {
    private static final Map<String, Asuransi> asuransiTerdaftar = new HashMap<>();
    private String namaAsuransi;
    private String nomorPolis;
    private String jenisAsuransi; // Contoh: "Swasta", "BPJS", "Syariah"
    /*static {
        // Dummy data awal
        tambahAsuransiBaru(new Asuransi("BPJS Kesehatan", "123456789", "BPJS"));
        tambahAsuransiBaru(new Asuransi("Prudential", "987654321", "Swasta"));
        tambahAsuransiBaru(new Asuransi("AXA Mandiri", "112233445", "Swasta"));
    }*/

    public Asuransi(String namaAsuransi, String nomorPolis, String jenisAsuransi) {
        this.namaAsuransi = namaAsuransi;
        this.nomorPolis = nomorPolis;
        this.jenisAsuransi = jenisAsuransi;
    }
    
    public static void tambahAsuransiBaru(Asuransi asuransi) {
        asuransiTerdaftar.put(asuransi.getNomorPolis(), asuransi);
    }

    // Mendapatkan daftar nama asuransi untuk ditampilkan ke pasien
    public static List<Asuransi> getDaftarAsuransi() {
        return new ArrayList<>(asuransiTerdaftar.values());
    }

    // Mendapatkan asuransi berdasarkan nomor polis
    public static Asuransi getAsuransiByNomorPolis(String nomorPolis) {
        return asuransiTerdaftar.get(nomorPolis);
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public String getNomorPolis() {
        return nomorPolis;
    }

    public String getJenisAsuransi() {
        return jenisAsuransi;
    }

}

package com.tubes.dokterly.medical;

import java.time.LocalDate;

public class RekamMedis {
    private LocalDate tanggal;
    private String namaPasien;
    private String namaDokter;
    private String diagnosis;
    private String catatan;

    // Konstruktor
    public RekamMedis(LocalDate tanggal, String namaPasien, String namaDokter, String diagnosis, String catatan) {
        this.tanggal = tanggal;
        this.namaDokter = namaDokter;
        this.diagnosis = diagnosis;
        this.catatan = catatan;
    }

    // Getter
    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getCatatan() {
        return catatan;
    }

    // Menampilkan rekam medis dalam format yang rapi
    @Override
    public String toString() {
        return String.format("Tanggal: %s | Pasien: %s| Dokter: %s | Diagnosis: %s | Catatan: %s",
                tanggal, namaPasien, namaDokter, diagnosis, catatan != null && !catatan.isEmpty() ? catatan : "Tidak ada catatan");
    }
}

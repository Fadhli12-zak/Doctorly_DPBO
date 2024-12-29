package com.tubes.dokterly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpDesk {
    private List<String> pertanyaan;
    private List<String> jawaban;

    // Konstruktor
    public HelpDesk() {
        this.pertanyaan = new ArrayList<>();
        this.jawaban = new ArrayList<>();
    }

    // Menambah pertanyaan baru
    public void tambahPertanyaan(String pertanyaan) {
        this.pertanyaan.add(pertanyaan);
        this.jawaban.add(null); // Placeholder untuk jawaban
        System.out.println("Pertanyaan Anda telah diterima. Tim kami akan segera menjawab.");
    }

    // Menjawab pertanyaan yang ada (Admin-only)
    public void jawabPertanyaan(int index, String jawaban) {
        if (index < 0 || index >= pertanyaan.size()) {
            System.out.println("Indeks pertanyaan tidak valid.");
        } else if (this.jawaban.get(index) != null) {
            System.out.println("Pertanyaan ini sudah dijawab.");
        } else {
            this.jawaban.set(index, jawaban);
            System.out.println("Jawaban berhasil ditambahkan.");
        }
    }

    // Menampilkan semua pertanyaan dan jawaban
    public void tampilkanSemua() {
        System.out.println("\n=== Pusat Bantuan ===");
        if(pertanyaan.isEmpty() && jawaban.isEmpty()){
            System.out.println("Belum ada Pertanyaan dan Jawaban");
        }
        for (int i = 0; i < pertanyaan.size(); i++) {
            System.out.printf("%d. Pertanyaan: %s%n   Jawaban: %s%n", i + 1, pertanyaan.get(i), 
                    jawaban.get(i) != null ? jawaban.get(i) : "Belum dijawab");
        }
    }

    // Fitur untuk pengguna menghubungi helpdesk
    public void hubungiHelpDesk() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Hubungi HelpDesk ===");
        System.out.print("Masukkan pertanyaan Anda: ");
        String userPertanyaan = input.nextLine();
        tambahPertanyaan(userPertanyaan);
    }
}

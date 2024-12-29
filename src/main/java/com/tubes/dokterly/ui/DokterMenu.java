package com.tubes.dokterly.ui;

import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Chat;
import com.tubes.dokterly.medical.JanjiTemu;
import com.tubes.dokterly.medical.RekamMedis;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class DokterMenu implements Menu {
    private Dokter dokter;
    private List<String> chat;

    public DokterMenu(Dokter dokter) {
        this.dokter = dokter;
    }

    @Override
    public void displayMenu() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Dokter ===");
            System.out.println("1. Lihat Daftar Pasien");
            System.out.println("2. Tambah Artikel Edukasi");
            System.out.println("3. Kelola Jadwal Janji Temu");
            System.out.println("4. Lihat pesan dari Pasien");
            System.out.println("5. Kelola Rekam Medis");
            System.out.println("6. Edit Profil");
            System.out.println("7. Logout");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> lihatDaftarPasien();
                case 2 -> tambahArtikel();
                case 3 -> kelolaJanjiTemu();
                case 4 -> lihatPesanPasien();
                case 5 -> kelolaRekamMedis();
                case 6 -> editProfil();
                case 7 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void lihatDaftarPasien() {
        List<Pasien> daftarPasien = dokter.getDaftarPasien();
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada pasien yang terdaftar.");
        } else {
            System.out.println("\n=== Daftar Pasien ===");
            for (int i = 0; i < daftarPasien.size(); i++) {
                Pasien pasien = daftarPasien.get(i);
                System.out.printf("%d. %s | Usia: %d | Jenis Kelamin: %s%n",
                        i + 1, pasien.getName(), pasien.getUsia(), pasien.getJenisKelamin());
            }
        }
    }
    
    private void lihatPesanPasien() {
        List<Chat> daftarChat = dokter.getDaftarChat();
        if (daftarChat.isEmpty()) {
            System.out.println("Tidak ada pesan dari pasien.");
        } else {
            System.out.println("\n=== Pesan dari Pasien ===");
            for (int i = 0; i < daftarChat.size(); i++) {
                Chat chat = daftarChat.get(i);
                System.out.printf("%d. Dari: %s | Pesan: %s%n", i + 1, chat.getPengirim().getName(), chat.getPesan());
            }
        }
    }
    
    private void kelolaRekamMedis() {
        Scanner input = new Scanner(System.in);
        int i;
        List<Pasien> daftarPasien = dokter.getDaftarPasien();
        List<Pasien> daftarPasienJT = dokter.getDaftarPasienJT();
        daftarPasien.addAll(daftarPasienJT);
        
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada pasien yang terdaftar.");
            return;
        }

        System.out.println("\n=== Kelola Rekam Medis ===");
        for (i = 0; i < daftarPasien.size() ; i++) {
            Pasien pasien = daftarPasien.get(i);
            System.out.printf("%d. %s%n", i + 1, pasien.getName());
        }
        
        System.out.print("\nPilih pasien untuk menambahkan rekam medis: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan < 1 || pilihan > daftarPasien.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Pasien pasienDipilih = daftarPasien.get(pilihan - 1);
        System.out.print("Masukkan diagnosa: ");
        String diagnosa = input.nextLine();
        System.out.print("Masukkan catatan: ");
        String catatan = input.nextLine();
        RekamMedis rekamMedis = new RekamMedis(LocalDate.now(), pasienDipilih.getName(), dokter.getName(), diagnosa, catatan);
        pasienDipilih.tambahRekamMedis(rekamMedis);
        System.out.println("\nRekam medis berhasil ditambahkan untuk pasien: " + pasienDipilih.getName());
        
    }
    
    private void tambahArtikel() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Tambah Artikel Edukasi ===");
        System.out.print("Judul Artikel: ");
        String judul = input.nextLine();
        System.out.print("Konten Artikel: ");
        String konten = input.nextLine();

        Article artikelBaru = new Article(judul, konten, dokter.getName());
        Article.tambahArtikel(artikelBaru);
    }

    private void kelolaJanjiTemu() {
        Scanner input = new Scanner(System.in);
        List<JanjiTemu> daftarJanjiTemu = dokter.getDaftarJanjiTemu();
        if (daftarJanjiTemu.isEmpty()) {
            System.out.println("Tidak ada janji temu yang terdaftar.");
            return;
        }

        System.out.println("\n=== Kelola Janji Temu ===");
        for (int i = 0; i < daftarJanjiTemu.size(); i++) {
            JanjiTemu appointment = daftarJanjiTemu.get(i);
            System.out.printf("%d. Pasien: %s | Waktu: %s | Status: %s%n", i + 1,
                    appointment.getNamaPasien(), appointment.getWaktu(), appointment.getStatus());
        }

        System.out.print("Pilih janji temu untuk memperbarui status: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan < 1 || pilihan > daftarJanjiTemu.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        JanjiTemu appointmentDipilih = daftarJanjiTemu.get(pilihan - 1);
        System.out.println("Status saat ini: " + appointmentDipilih.getStatus());
        System.out.print("Masukkan status baru (Dikonfirmasi/Selesai/Dibatalkan): ");
        String statusBaru = input.nextLine();
        appointmentDipilih.setStatus(statusBaru);
        System.out.println("Status janji temu berhasil diperbarui.");
    }

    /*private void chatDenganPasien() {
        Scanner input = new Scanner(System.in);
        List<Pasien> daftarPasien = dokter.getDaftarPasien();
        if (daftarPasien.isEmpty()) {
            System.out.println("Tidak ada pasien untuk dihubungi.");
            return;
        }

        System.out.println("\n=== Daftar Pasien ===");
        for (int i = 0; i < daftarPasien.size(); i++) {
            Pasien pasien = daftarPasien.get(i);
            System.out.printf("%d. %s%n", i + 1, pasien.getName());
        }
        System.out.print("Pilih pasien untuk chat: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan < 1 || pilihan > daftarPasien.size()) {
            System.out.println("Pilihan tidak valid.");
        } else {
            Pasien pasienTerpilih = daftarPasien.get(pilihan - 1);
            System.out.printf("Memulai chat dengan %s...%n", pasienTerpilih.getName());
            dokter.balasPesan(pasienTerpilih, pesan);
        }
    }*/
    

    private void editProfil() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Edit Profil ===");
        System.out.print("Nama baru: ");
        String namaBaru = input.nextLine();
        System.out.print("Riwayat Pendidikan baru: ");
        String pendidikanBaru = input.nextLine();
        System.out.print("Tempat Praktik baru: ");
        String tempatPraktikBaru = input.nextLine();

        dokter.setName(namaBaru);
        dokter.setRiwayatPendidikan(pendidikanBaru);
        dokter.setTempatPraktik(tempatPraktikBaru);

        System.out.println("Profil berhasil diperbarui.");
    }
}

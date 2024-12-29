package com.tubes.dokterly.ui;

import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.service.HelpDesk;
import com.tubes.dokterly.medical.JanjiTemu;
import com.tubes.dokterly.medical.MetodePembayaran;
import com.tubes.dokterly.ecommerce.Olshop;
import com.tubes.dokterly.service.RegistrationService;
import com.tubes.dokterly.medical.RekamMedis;
import com.tubes.dokterly.medical.Review;
import com.tubes.dokterly.medical.Transaksi;
import com.tubes.dokterly.utils.Utils;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasienMenu implements Menu {
    private final Scanner input = new Scanner(System.in);
    private Pasien pasien;
    private RegistrationService regService;
    private Olshop olshop;

    public PasienMenu(Pasien pasien, RegistrationService regService, Olshop olshop) {
        this.pasien = pasien;
        this.regService = regService;
        this.olshop = olshop;
    }

    @Override
     public void displayMenu() {
        checkInfo();
        while (true) {
            System.out.println("\n=== Menu Pasien ===");
            System.out.println("1. Konsultasi");
            System.out.println("2. Buat Janji Temu");
            System.out.println("3. Profil");
            System.out.println("4. Histori Transaksi");
            System.out.println("5. Akses Artikel");
            System.out.println("6. Hubungi HelpDesk");
            System.out.println("7. Belanja di Olshop");
            System.out.println("8. Lihat Rekam Medis");
            System.out.println("9. Logout");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> konsultasi();
                case 2 -> buatJanjiTemu();
                case 3 -> editProfil();
                case 4 -> pasien.tampilkanRiwayatTransaksi();
                case 5 -> aksesArtikel();
                case 6 -> hubungiHelpDesk();
                case 7 -> belanjaOlshop(olshop, pasien);
                case 8 -> lihatRekamMedis();
                case 9 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
     
    private void checkInfo(){
        if(pasien.getRiwayatKesehatan() == "-" && pasien.getAlergi() == "-" && pasien.getDaftarAsuransi().isEmpty()  && !pasien.isInfoCheck()){
            System.out.println("\nKelengkapan profilmu belum lengkap nih, isi ya biar nanti prosesnya sat set!");
            System.out.println("Kalau gaada, boleh diisi '-'. biar kapan-kapan lagi aja ngisinya\n");
            System.out.print("Riwayat Kesehatan         : ");
            String rKes = input.nextLine();
            pasien.setRiwayatKesehatan(rKes);
            System.out.print("Alergi                    : ");
            String alergi = input.nextLine();
            pasien.setAlergi(alergi);
            String asuransi;
            boolean isRunning = true;
            while(isRunning){
                System.out.print("Hubungkan Asuransi? (y/n) : ");
                String connect = input.nextLine();
                System.out.println();
                
                switch(connect){
                    case "y" -> {
                        tambahAsuransiMenu(pasien);
                        if(!pasien.getDaftarAsuransi().isEmpty()){
                            isRunning = false;
                        }
                    }
                    case "n" -> {
                        isRunning = false;
                    }
                    default -> {
                        System.out.println("Pilihan tidak valid");
                    }
                }
            }
            
        }
    }

    private void konsultasi() {
        List<Dokter> daftarDokter = new ArrayList<>();
        daftarDokter = regService.getDaftarDokter();
        while (true) {
            System.out.println("\n=== Menu Konsultasi ===");
            System.out.println("Pilih dokter untuk konsultasi:");

            // Tampilkan daftar dokter dengan tarif
            for (int i = 0; i < daftarDokter.size(); i++) {
                Dokter dokter = daftarDokter.get(i);
                System.out.printf("%d. %s (Spesialis: %s) - Tarif: Rp %,d - Rating: %.1f\n",
                        i + 1, dokter.getName(), dokter.getSpesialisasi(), dokter.getTarif(), dokter.avgRate());
            }
            System.out.println((daftarDokter.size() + 1) + ". Kembali ke menu utama");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            if (pilihan == daftarDokter.size() + 1) {
                break; // Kembali ke menu utama
            } else if (pilihan > 0 && pilihan <= daftarDokter.size()) {
                Dokter dokterTerpilih = daftarDokter.get(pilihan - 1);
                previewKonsulDokter(dokterTerpilih);
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    private void previewKonsulDokter(Dokter dokter) {
        while (true) {
            System.out.printf("\n=== Menu Dokter: %s ===\n", dokter.getName());
            System.out.println("1. Lihat Profil Dokter");
            System.out.println("2. Langsung Chat Dokter");
            System.out.println("3. Kembali ke daftar dokter");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1 -> {
                    System.out.println("\n===Profil Dokter===");
                    dokter.display();
                }
                case 2 -> {
                    if (pilihMetodePembayaran(dokter)) {
                        pasien.chatDokter(pasien, dokter);
                        dokter.tambahPasien(pasien);
                        beriReview(dokter);
                    }
                }
                case 3 -> {
                    System.out.println("Kembali ke daftar dokter.");
                    return; // Kembali ke menu konsultasi
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    private void lihatRekamMedis() {
        List<RekamMedis> rekamMedisList = pasien.getRekamMedis();
        if (rekamMedisList.isEmpty()) {
            System.out.println("Tidak ada rekam medis tersedia.");
        } else {
            System.out.println("\n=== Rekam Medis ===");
            for (RekamMedis rekam : rekamMedisList) {
                System.out.println(rekam.toString());
            }
        }
    }
    
    private void beriReview(Dokter dokter){
        System.out.println("Terima kasih telah berkonsultasi dengan " + dokter.getName() + ". Bagaimana pengalaman Anda?\n" +
        "Beri rating untuk membantu kami dan dokter memberikan layanan yang lebih baik!"); 
            System.out.print("Apakah anda ingin memberikan rating? (y/n) : ");
            String pilih = input.nextLine();

            switch (pilih) {
                case "y" -> {
                    int rating;
                    String komentar;
                    while (true){
                        System.out.print("Rate(1-5): ");
                        rating = input.nextInt();
                        input.nextLine();
                        if (rating < 1 || rating > 5) {
                            throw new IllegalArgumentException("Rating harus dalam skala 1-5.");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Komentar: ");
                    komentar = input.nextLine();
                    Review review = new Review(pasien.getName(), rating, komentar);
                    dokter.addReview(review);
                }
                case "n" ->{
                    System.out.println("");
                }
                default -> {
                    System.out.println("Pilihan tidak valid");
                }
            }
    }
    
    public void tambahAsuransiMenu(Pasien pasien) {
        // Tampilkan daftar asuransi
        List<Asuransi> daftarAsuransi = Asuransi.getDaftarAsuransi();
        if (daftarAsuransi.isEmpty()) {
            System.out.println("Belum ada asuransi yang terdaftar. Hubungi admin.");
            return;
        }
        
        System.out.println("Pilih nama asuransi:");
        for (int i = 0; i < daftarAsuransi.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, daftarAsuransi.get(i).getNamaAsuransi(),
                daftarAsuransi.get(i).getJenisAsuransi());
        }
        System.out.print("Masukkan nomor pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine();
      
        if (pilihan < 1 || pilihan > daftarAsuransi.size()) {
            System.out.println("\nPilihan tidak valid.\n");
            return;
        }
      
        // Pilih asuransi berdasarkan input
        Asuransi asuransiTerpilih = daftarAsuransi.get(pilihan - 1);
        System.out.println("\nAsuransi terpilih: " + asuransiTerpilih.getNamaAsuransi() + "");
      
        // Verifikasi nomor polis
        System.out.print("Masukkan nomor polis Anda: ");
        String nomorPolis = input.nextLine();
      
        if (asuransiTerpilih.getNomorPolis().equals(nomorPolis)) {
            pasien.tambahAsuransi(asuransiTerpilih);
        } else {
            System.out.println("\nNomor polis tidak valid untuk asuransi ini!\n");
        }
    }
    
    public boolean pilihMetodePembayaran(Dokter dokter) {
        List<MetodePembayaran> metodeList = MetodePembayaran.getMetodePembayaran();

        int tarif = dokter.getTarif();
        System.out.printf("\nTarif untuk konsultasi adalah %s.\n", Utils.tarifFormat(tarif), "\n");
        System.out.println("Pilih metode pembayaran:");
        for (int i = 0; i < metodeList.size(); i++) {
            MetodePembayaran metode = metodeList.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, metode.getNama(), metode.getDeskripsi());
        }
        System.out.println((metodeList.size() + 1) + ". Batal");
        System.out.print("Pilih: ");
        int pilihan = input.nextInt();
        input.nextLine(); // Konsumsi newline

        if (pilihan == metodeList.size() + 1) {
            System.out.println("\nPembayaran dibatalkan.\n");
            return false; // Pembayaran batal
        } else if (pilihan > 0 && pilihan <= metodeList.size()) {
            MetodePembayaran metodeTerpilih = metodeList.get(pilihan - 1);
            return Transaksi.prosesPembayaran(pasien, dokter, metodeTerpilih, tarif); // Proses pembayaran
        } else {
            System.out.println("Pilihan tidak valid.");
            return false; // Pilihan tidak valid
        }
    }

    private void buatJanjiTemu() {
         List<Dokter> daftarDokter = regService.getDaftarDokter();
        if (daftarDokter.isEmpty()) {
            System.out.println("Tidak ada dokter yang tersedia untuk janji temu.");
            return;
        }
        System.out.println("\n=== Daftar Dokter ===");
        for (int i = 0; i < daftarDokter.size(); i++) {
            Dokter dokter = daftarDokter.get(i);
            System.out.printf("%d. %s (Spesialisasi: %s)%n", i + 1, dokter.getName(), dokter.getSpesialisasi());
        }
        System.out.print("Pilih dokter untuk janji temu: ");
        int pilihan = new Scanner(System.in).nextInt();
        if (pilihan < 1 || pilihan > daftarDokter.size()) {
            System.out.println("Pilihan tidak valid.");
        } else {
            Dokter dokterTerpilih = daftarDokter.get(pilihan - 1);
            JanjiTemu appointment = JanjiTemu.buatJanjiTemu(pasien, dokterTerpilih);
            System.out.println("\nJanji temu berhasil dibuat dengan dokter: " + dokterTerpilih.getName());
            dokterTerpilih.tambahJanjiTemu(appointment);
            dokterTerpilih.tambahPasienJT(pasien);
            appointment.tampilkanDetail();
        }
    }

    private void editProfil() {
        System.out.println("=====Profil Anda=====");
        pasien.display();
        pasien.editProfile();
    }

    private void aksesArtikel() {
        List<Article> daftarArtikel = Article.getArticles();
        if (daftarArtikel.isEmpty()) {
            System.out.println("Tidak ada artikel yang tersedia saat ini.");
        } else {
            System.out.println("\n=== Daftar Artikel ===");
            for (int i = 0; i < daftarArtikel.size(); i++) {
                Article artikel = daftarArtikel.get(i);
                System.out.printf("%d. %s - %s%n", i + 1, artikel.getTitle(), artikel.getAuthor());
            }
            System.out.print("Pilih artikel untuk dibaca: ");
            int pilihan = new Scanner(System.in).nextInt();
            if (pilihan < 1 || pilihan > daftarArtikel.size()) {
                System.out.println("Pilihan tidak valid.");
            } else {
                Article artikelDipilih = daftarArtikel.get(pilihan - 1);
                artikelDipilih.displayArticle();
            }
        }
    }

    private void hubungiHelpDesk() {
       HelpDesk helpdesk = new HelpDesk(); 
       int opsi = 0;
       while(opsi != 3){
           System.out.println("\n1. Tambah Pertanyaan ");
           System.out.println("2. Tampilkan Pertanyaan dan jawaban ");
           System.out.println("3. selesai ");
           System.out.print(" Pilih: ");
           opsi = input.nextInt();
           if(opsi == 1){
               helpdesk.hubungiHelpDesk();
           }else if(opsi == 2){
               helpdesk.tampilkanSemua();
           }
       }
    }

    private void belanjaOlshop(Olshop olshop, Pasien pasien) {
        while (true) {
            System.out.println("\n=== Menu Olshop: ===");
            System.out.println("1. Lihat daftar Obat");
            System.out.println("2. Beli Obat");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1 -> {
                    olshop.tampilkanProduk();
                }
                case 2 -> {
                    olshop.beliProduk(pasien);
                    }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }         
        }
    }
}

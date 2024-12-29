package com.tubes.doctorly;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userMenu {
    
    //Menu Pasien
    public static void pasienMenu(RegistrationService reg, Pasien user){
        Scanner input = new Scanner(System.in);
        if(user.getRiwayatKesehatan() == "-" && user.getAlergi() == "-" && user.getDaftarAsuransi().isEmpty()  && !user.isInfoCheck()){
            System.out.println("\nKelengkapan profilmu belum lengkap nih, isi ya biar nanti prosesnya sat set!");
            System.out.println("Kalau gaada, boleh diisi '-'. biar kapan-kapan lagi aja ngisinya\n");
            System.out.print("Riwayat Kesehatan         : ");
            String rKes = input.nextLine();
            user.setRiwayatKesehatan(rKes);
            System.out.print("Alergi                    : ");
            String alergi = input.nextLine();
            user.setAlergi(alergi);
            String asuransi;
            boolean isRunning = true;
            while(isRunning){
                System.out.print("Hubungkan Asuransi? (y/n) : ");
                String connect = input.nextLine();
                System.out.println();
                
                switch(connect){
                    case "y" -> {
                        tambahAsuransiMenu(user);
                        if(!user.getDaftarAsuransi().isEmpty()){
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
        
        while (true) {
            //header
            System.out.println("\n\u001B[34m=== Pasien Menu ===\u001B[0m");
            System.out.println("1. Konsultasi");
            System.out.println("2. Buat Janji Temu");
            System.out.println("3. Profil");
            System.out.println("4. Histori Transaksi");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            //input pilihan menu
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();

            //kondisi dari pilihan menu
            switch (choice) {
                case 1 -> konsultasiMenu(user, reg);
                case 2 -> System.out.println("Fitur janji temu belum tersedia.");
                case 3 -> {
                    while (true) {
                        System.out.println("\n=== Profil Anda ===");
                        user.display(); // Tampilkan profil pasien
                        System.out.print("\nMau edit profil? (y/n): ");
                        String edit = input.nextLine();

                        if (edit.equalsIgnoreCase("n")) {
                            break; // Kembali ke menu utama
                        } else if (edit.equalsIgnoreCase("y")) {
                            user.editProfile(); // Menu edit profil
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                }
                case 4 -> user.tampilkanRiwayatTransaksi();
                case 5 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    public static void tambahAsuransiMenu(Pasien pasien) {
        // Tampilkan daftar asuransi
        Scanner input = new Scanner(System.in);
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
    
    public static void konsultasiMenu(Pasien pasien, RegistrationService reg) {
        List<Dokter> daftarDokter = new ArrayList<>();
        daftarDokter = reg.getDaftarDokter();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Konsultasi ===");
            System.out.println("Pilih dokter untuk konsultasi:");

            // Tampilkan daftar dokter dengan tarif
            for (int i = 0; i < daftarDokter.size(); i++) {
                Dokter dokter = daftarDokter.get(i);
                System.out.printf("%d. %s (Spesialis: %s) - Tarif: Rp %,d\n",
                        i + 1, dokter.getName(), dokter.getSpesialisasi(), dokter.getTarif());
            }
            System.out.println((daftarDokter.size() + 1) + ". Kembali ke menu utama");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            if (pilihan == daftarDokter.size() + 1) {
                break; // Kembali ke menu utama
            } else if (pilihan > 0 && pilihan <= daftarDokter.size()) {
                Dokter dokterTerpilih = daftarDokter.get(pilihan - 1);
                previewKonsulDokter(pasien, dokterTerpilih);
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    private static void previewKonsulDokter(Pasien pasien, Dokter dokter) {
        Scanner input = new Scanner(System.in);
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
                    if (pasien.pilihMetodePembayaran(pasien, dokter)) {
                        pasien.chatDokter(pasien, dokter);
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

    
    //Menu Dokter
    public static void dokterMenu(RegistrationService reg, Dokter user){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n\u001B[34m=== Dokter Menu ===\u001B[0m");
            System.out.println("1. Lihat Daftar Pasien");
            System.out.println("2. Tambah Jadwal Praktik");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();

            switch (choice) {
                case 1 -> System.out.println("Fitur daftar pasien belum tersedia.");
                case 2 -> System.out.println("Fitur tambah jadwal praktik belum tersedia.");
                case 3 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    
    //Menu Admin
    public static void adminMenu(RegistrationService reg, Admin user){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n\u001B[34m=== Admin Menu ===\u001B[0m");
            System.out.println("1. Lihat Semua Pengguna");
            System.out.println("2. Tambah Pengguna");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> reg.printAllUsers();
                case 2 -> System.out.println("Logout berhasil.");
                case 3 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

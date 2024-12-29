package com.tubes.dokterly;

import com.tubes.dokterly.ui.AdminMenu;
import com.tubes.dokterly.medical.Article;
import com.tubes.dokterly.communication.Asuransi;
import com.tubes.dokterly.ui.DokterMenu;
import com.tubes.dokterly.ui.Menu;
import com.tubes.dokterly.ecommerce.Obat;
import com.tubes.dokterly.ecommerce.Olshop;
import com.tubes.dokterly.ui.PasienMenu;
import com.tubes.dokterly.service.RegistrationService;
import com.tubes.dokterly.user.Admin;
import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;
import com.tubes.dokterly.user.User;
import java.time.LocalDate;
import java.util.Scanner;

public class Dokterly {
    public static void main(String[] args) {
        RegistrationService regService = new RegistrationService();
        Olshop olshop = new Olshop();
        Scanner input = new Scanner(System.in);

        // Dummy Data Admin
        Admin superAdmin = new Admin("Super Admin", "admin@doctorly.com", "admin123");
        regService.registerAdmin("Super Admin", "admin@doctorly.com", "admin123", superAdmin);

        // <editor-fold defaultstate="collapsed" desc="Dummy Data">
        // Dummy Data Dokter
        regService.registerDokter("Dr. Arief Santoso", "arief.santoso@doctorly.com", "Dokter123!", "Umum", "Universitas Indonesia", "RS Medika Utama", superAdmin);
        regService.registerDokter("Dr. Melati Kusumawardani", "melati.kusuma@healthplus.co.id", "Melati@456", "Penyakit Dalam", "Universitas Airlangga", "RS Sehat Sentosa", superAdmin);
        regService.registerDokter("Dr. Farhan Prasetya", "farhan.prasetya@mediclink.com", "Farhan!789", "Kardiologi", "Universitas Gadjah Mada", "RS Jantung Prima", superAdmin);
        regService.registerDokter("Dr. Nindy Rahmawati", "nindy.rahmawati@zmail.com", "Nindy456@", "Neurologi", "Universitas Padjadjaran", "RS Saraf Sejahtera", superAdmin);
        regService.registerDokter("Dr. Rizal Wijaya", "rizal.wijaya@doctorly.com", "Rizal123!", "Ortopedi", "Universitas Diponegoro", "RS Tulang Indah", superAdmin);
        regService.registerDokter("Dr. Viona Maharani", "viona.maharani@medcare.com", "Viona987!", "Pediatri", "Universitas Hasanuddin", "RS Anak Ceria", superAdmin);
        regService.registerDokter("Dr. Aditya Saputra", "aditya.saputra@clinicplus.net", "Aditya!567", "Dermatologi", "Universitas Brawijaya", "RS Kulit Sehat", superAdmin);
        regService.registerDokter("Dr. Laila Zahra", "laila.zahra@healthcare.org", "Laila#1234", "Ginekologi", "Universitas Udayana", "RS Ibu dan Anak Harmoni", superAdmin);
        regService.registerDokter("Dr. Yoga Ramadhan", "yoga.ramadhan@medlink.com", "Yoga123@", "Bedah", "Universitas Sriwijaya", "RS Bedah Utama", superAdmin);
        regService.registerDokter("Dr. Siska Putri", "siska.putri@doctorly.com", "Siska!444", "Oftalmologi", "Universitas Sumatera Utara", "RS Mata Indah", superAdmin);

        // Dummy Data Pasien
        regService.registerPasien("Arkan Wibowo", "arkan.wibowo@medic.com", "Arkan789!", LocalDate.of(1992, 5, 15), "Laki-laki");
        regService.registerPasien("Elvira Rahmawati", "elvira.rahmawati@healthcare.co.id", "Elvira!456", LocalDate.of(1994, 10, 20), "Perempuan");
        regService.registerPasien("Naufal Zulfikar", "naufal.zulfikar@mail.com", "Naufal123#", LocalDate.of(1988, 3, 5), "Laki-laki");
        regService.registerPasien("Kirana Adhisty", "kirana.adhisty@gmail.com", "Kirana@2024", LocalDate.of(1990, 12, 11), "Perempuan");
        regService.registerPasien("Rizky Hamdani", "rizky.hamdani@medicplus.com", "Rizky!090", LocalDate.of(1996, 6, 22), "Laki-laki");
        regService.registerPasien("Syifa Mawarni", "syifa.mawarni@clinic.net", "Syifa*987", LocalDate.of(1985, 2, 28), "Perempuan");
        regService.registerPasien("Daffa Mahardika", "daffa.mahardika@medcare.com", "Daffa2023!", LocalDate.of(1997, 4, 17), "Laki-laki");
        regService.registerPasien("Nayla Prameswari", "nayla.prameswari@zmail.com", "Nayla#888", LocalDate.of(1993, 7, 30), "Perempuan");
        regService.registerPasien("Raka Dwiantara", "raka.dwiantara@medlink.com", "Raka567@", LocalDate.of(1991, 1, 25), "Laki-laki");
        regService.registerPasien("Almira Zhafira", "almira.zhafira@yahoo.com", "Almira!432", LocalDate.of(1989, 8, 14), "Perempuan");

        // Dummy Data Asuransi
        Asuransi.tambahAsuransiBaru(new Asuransi("BPJS Kesehatan", "123456", "BPJS"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Prudential", "654321", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("AXA Mandiri", "AXA456", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Allianz Life", "ALL789", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Manulife Indonesia", "MAN321", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("AIA Financial", "AIA654", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Sinarmas MSIG", "SMSIG111", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("BRI Life", "BRI222", "BUMN"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Bumiputera 1912", "BP1912", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Jasindo", "JSD333", "BUMN"));
        
        // Dummy Data Artikel
        
        Article.tambahArtikel(new Article(
            "Panduan Hidup Sehat di Era Modern",
            "Dalam era modern, menjaga kesehatan adalah prioritas utama. Artikel ini membahas pentingnya pola makan seimbang, olahraga teratur, dan manajemen stres.",
            "Dr. Arief Santoso"
        ));
        Article.tambahArtikel(new Article(
            "Teknologi AI dan Masa Depan Manusia",
            "Kecerdasan buatan telah mengubah banyak aspek kehidupan kita. Bagaimana dampaknya di masa depan? Mari kita bahas potensi dan tantangannya.",
            "Melati Kusumawardani, M.T."
        ));
        Article.tambahArtikel(new Article(
            "Tips Mengelola Keuangan Pribadi",
            "Keuangan yang sehat dimulai dari perencanaan yang baik. Artikel ini memberikan tips praktis dalam mengatur anggaran dan menabung.",
            "Naufal Zulfikar, SE"
        ));
        Article.tambahArtikel(new Article(
            "Perkembangan Dunia Kesehatan Pasca Pandemi",
            "Pasca pandemi, berbagai inovasi dan teknologi di dunia kesehatan berkembang pesat. Simak tren dan tantangan yang dihadapi saat ini.",
            "Dr. Kirana Adhisty"
        ));
        Article.tambahArtikel(new Article(
            "10 Destinasi Wisata Terpopuler di 2024",
            "Artikel ini membahas 10 destinasi wisata yang wajib dikunjungi di tahun 2024. Dari pegunungan hingga pantai eksotis.",
            "Daffa Mahardika"
        ));
        Article.tambahArtikel(new Article(
            "Manfaat Meditasi untuk Kesehatan Mental",
            "Meditasi telah terbukti secara ilmiah membantu mengurangi stres dan meningkatkan fokus. Berikut langkah-langkah meditasi sederhana untuk pemula.",
            "Syifa Mawarni, Psikolog"
        ));
        Article.tambahArtikel(new Article(
            "Belajar Pemrograman untuk Pemula: Langkah Awal",
            "Artikel ini membahas cara memulai belajar pemrograman, bahasa yang direkomendasikan, dan sumber belajar yang mudah diikuti.",
            "Rizky Hamdani, S.Kom."
        ));
        Article.tambahArtikel(new Article(
            "Tren Fashion Terbaru di Tahun 2024",
            "Dunia fashion terus berubah. Simak tren terbaru yang akan mendominasi pasar fashion global di tahun 2024.",
            "Nayla Prameswari"
        ));
        Article.tambahArtikel(new Article(
            "Pentingnya Pendidikan Karakter di Sekolah",
            "Pendidikan karakter membantu membentuk kepribadian anak yang kuat. Simak pendekatan dan manfaat penerapan di sekolah.",
            "Raka Dwiantara, M.Pd."
        ));
        Article.tambahArtikel(new Article(
            "Mengatasi Polusi Udara di Kota Besar",
            "Polusi udara menjadi masalah serius di kota-kota besar. Artikel ini membahas solusi praktis dan teknologi yang bisa diterapkan.",
            "Almira Zhafira, M.Sc."
        ));

        // Dummy Data Obat
        olshop.tambahProduk(new Obat("Paracetamol", "Obat untuk meredakan nyeri dan demam", 10000, 100, false));
        olshop.tambahProduk(new Obat("Ibuprofen", "Obat antiinflamasi untuk meredakan nyeri dan bengkak", 15000, 50, false));
        olshop.tambahProduk(new Obat("Amoxicillin", "Antibiotik untuk mengobati infeksi bakteri", 20000, 30, true));
        olshop.tambahProduk(new Obat("Cough Syrup", "Obat untuk meredakan batuk", 12000, 40, false));
        olshop.tambahProduk(new Obat("Antibiotic Cream", "Krim antibiotik untuk luka kulit", 25000, 20, true));
        olshop.tambahProduk(new Obat("Antibiotic Cream", "Krim antibiotik untuk luka kulit", 25000, 20, true));
        olshop.tambahProduk(new Obat("Paracetamol", "Obat pereda demam dan sakit kepala", 10000, 100, false));
        olshop.tambahProduk(new Obat("Cetirizine", "Antihistamin untuk alergi", 18000, 60, false));
        olshop.tambahProduk(new Obat("Salbutamol", "Obat untuk melegakan asma", 30000, 25, true));
        olshop.tambahProduk(new Obat("Ranitidine", "Obat untuk mengatasi gangguan lambung", 22000, 35, false));
        olshop.tambahProduk(new Obat("Vitamin C Tablet", "Suplemen untuk menjaga daya tahan tubuh", 8000, 150, false));
        olshop.tambahProduk(new Obat("Metformin", "Obat untuk mengontrol kadar gula darah", 27000, 40, true));
        olshop.tambahProduk(new Obat("Hydrocortisone Cream", "Krim untuk iritasi kulit dan eksim", 26000, 15, true));
        olshop.tambahProduk(new Obat("Loperamide", "Obat untuk diare akut", 12000, 50, false));
        olshop.tambahProduk(new Obat("Ambroxol Syrup", "Obat pengencer dahak untuk batuk berdahak", 15000, 45, false));
        // </editor-fold>
        
        // Start Aplikasi
        while (true) {
            System.out.println("\n=== WELCOME TO DOCTORLY ===");
            System.out.println("1. Login");
            System.out.println("2. Register Pasien");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int pilihan = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (pilihan) {
                case 1 -> login(regService, input, olshop);
                case 2 -> Pasien.register(regService);
                case 3 -> {
                    System.out.println("Terima kasih telah menggunakan Doctorly. Sampai jumpa!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void login(RegistrationService regService, Scanner input, Olshop olshop) {
        System.out.println("\n=== Login ===");
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        User user = regService.login(email, password);
        if (user == null) {
            System.out.println("Email atau password salah. Silakan coba lagi.");
            return;
        }

        System.out.println("Selamat datang, " + user.getName() + "!");
        Menu menu;

        if (user instanceof Pasien) {
            menu = new PasienMenu((Pasien) user, regService, olshop);
        } else if (user instanceof Dokter) {
            menu = new DokterMenu((Dokter) user);
        } else if (user instanceof Admin) {
            menu = new AdminMenu((Admin) user, regService);
        } else {
            throw new IllegalStateException("Role tidak dikenal.");
        }

        menu.displayMenu();
    }
}
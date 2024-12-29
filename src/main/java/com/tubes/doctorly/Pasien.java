
package com.tubes.doctorly;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Pasien extends User{
    private LocalDate tglLahir;
    private int usia;
    private String jenisKelamin;
    private String riwayatKesehatan;
    private String alergi;
    //private String asuransi;
    private boolean infoCheck;
    private List<Asuransi> daftarAsuransi;
    private List<Transaksi> riwayatTransaksi;

    public Pasien(String name, String email, String password, LocalDate tglLahir, String jenisKelamin) {
        super(name, email, password, "Pasien");
        this.tglLahir = tglLahir;
        this.jenisKelamin = jenisKelamin;
        this.usia = Utils.hitungUsia(tglLahir);
        this.riwayatKesehatan = "-";
        this.alergi = "-";
        this.infoCheck = false;
        this.daftarAsuransi = new ArrayList<>();
        this.riwayatTransaksi = new ArrayList<>();
    }

    public boolean isInfoCheck() {
        return infoCheck;
    }
    
    public void infoCheck(){
        this.infoCheck = true;
    }
    
    public  static void register(RegistrationService reg){
        Scanner input = new Scanner(System.in);
        String nama;
        String email;
        String password;
        LocalDate tglLahir;
        System.out.println("=== Register ===");
        System.out.print("Nama lengkap               : ");
        nama = input.nextLine();
            
        while (true){
            System.out.print("Email                      : ");
            email = input.nextLine();
                
            if(RegistrationService.isValidEmail(email)){
                break;
            } else {
                System.out.println("    Masukan kembali email yang valid.");
            }
        }
            
        while(true){
            System.out.print("Password                   : ");
            password = input.nextLine();
            if(RegistrationService.isValidPassword(password)){
                break;
            } else {
                System.out.println("    Password harus mengandung 8 karakter huruf dan angka.");
            }
        }
        
        while (true){
            System.out.print("Tanggal Lahir (dd-mm-yyyy) : ");
            String tglLahirIn = input.nextLine();
            tglLahir = Utils.validateDate(tglLahirIn);
            if(tglLahir != null){
                break;
            } else {
                System.out.println("Pastikan tanggal dalam format dd-mm-yyyy.");
            }
        }
            
        System.out.println("Jenis Kelamin              : 1. Laki-Laki");
        System.out.println("                             2. Perempuan");
        System.out.print("Pilih(1/2)                 : ");
        int no = input.nextInt();
        input.nextLine();
        String jenisKelamin;
        switch (no){
            case 1 -> jenisKelamin = "Laki-Laki";
            case 2 -> jenisKelamin = "Perempuan";
            default -> {
                System.out.println("Opsi tidak Valid");
                return;
            }
        }
                       
        reg.registerPasien(nama, email, password, tglLahir, jenisKelamin);
    }
    
    public List<Asuransi> getDaftarAsuransi() {
        return daftarAsuransi;
    }

    // Menambah asuransi ke daftar
    public void tambahAsuransi(Asuransi asuransi) {
        daftarAsuransi.add(asuransi);
        System.out.println("\nAsuransi berhasil ditambahkan: " + asuransi.getNamaAsuransi());
    }

    // Melihat daftar asuransi
    public void lihatAsuransi() {
        if (daftarAsuransi.isEmpty()) {
            System.out.println("Tidak ada asuransi yang diapply.");
        } else {
            System.out.println("Daftar Asuransi:");
            for (Asuransi assurance : daftarAsuransi) {
                System.out.println("- " + assurance);
            }
        }
    }
    

    public String getRiwayatKesehatan() {
        return riwayatKesehatan;
    }

    public String getAlergi() {
        return alergi;
    }

    public void setRiwayatKesehatan(String riwayatKesehatan) {
        this.riwayatKesehatan = riwayatKesehatan;
    }

    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public LocalDate getTglLahir() {
        return tglLahir;
    }

    public int getUsia() {
        return usia;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tglLahir = tanggalLahir;
        this.usia = Period.between(tanggalLahir, LocalDate.now()).getYears();
    }
    
    public void editProfile() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Edit Profil ===");
            System.out.println("1. Nama");
            System.out.println("2. Tanggal Lahir");
            System.out.println("3. Jenis Kelamin");
            System.out.println("4. Riwayat Kesehatan");
            System.out.println("5. Alergi");
            System.out.println("6. Selesai");
            System.out.print("Pilih data yang ingin diubah (1-6): ");
            int choice = input.nextInt();
            input.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan nama baru: ");
                    String nama = input.nextLine();
                    this.setNama(nama);
                    System.out.println("Nama berhasil diubah.");
                }
                case 2 -> {
                    System.out.print("Masukkan tanggal lahir baru (dd-MM-yyyy): ");
                    String tglLahirInput = input.nextLine();
                    LocalDate tglLahir = Utils.validateDate(tglLahirInput);
                    if (tglLahir != null) {
                        this.setTanggalLahir(tglLahir);
                        System.out.println("Tanggal lahir berhasil diubah.");
                    } else {
                        System.out.println("Format tanggal tidak valid.");
                    }
                }
                case 3 -> {
                    System.out.print("Masukkan jenis kelamin baru (L/P): ");
                    String jenisKelamin = input.nextLine();
                    this.setJenisKelamin(jenisKelamin);
                    System.out.println("Jenis kelamin berhasil diubah.");
                }
                case 4 -> {
                    System.out.print("Masukkan riwayat kesehatan baru: ");
                    String riwayat = input.nextLine();
                    this.setRiwayatKesehatan(riwayat);
                    System.out.println("Riwayat kesehatan berhasil diubah.");
                }
                case 5 -> {
                    System.out.print("Masukkan alergi baru: ");
                    String alergi = input.nextLine();
                    this.setAlergi(alergi);
                    System.out.println("Alergi berhasil diubah.");
                }
                case 6 -> {
                    System.out.println("Selesai mengedit profil.");
                    return; // Keluar dari menu edit
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
    
    public void chatDokter(Pasien pasien, Dokter dokter) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\nMemulai chat dengan Dokter %s...\n", dokter.getName());
        System.out.println("Ketik 'exit' untuk mengakhiri chat.");

        while (true) {
            System.out.print("Anda: ");
            String pesan = input.nextLine();

            if (pesan.equalsIgnoreCase("exit")) {
                System.out.println("Chat diakhiri.");
                break;
            }

            System.out.println(dokter.getName() + ": Terima kasih atas pertanyaannya. Silakan lanjutkan.");
        }
    }
    
    public boolean bayarTarif(Pasien pasien, int tarif) {
        Scanner input = new Scanner (System.in);
        System.out.printf("\nTarif untuk konsultasi dengan dokter ini adalah %s.\n", 
                          Utils.tarifFormat(tarif));
        System.out.print("Apakah Anda ingin melanjutkan pembayaran? (y/n): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            System.out.println("Pembayaran berhasil. Anda dapat memulai chat dengan dokter.");
            return true; // Pembayaran sukses
        } else {
            System.out.println("Pembayaran dibatalkan.");
            return false; // Pembayaran gagal
        }
    }
    
    public boolean pilihMetodePembayaran(Pasien pasien, Dokter dokter) {
        Scanner input = new Scanner (System.in);
        List<MetodePembayaran> metodeList = MetodePembayaran.getMetodePembayaran();

        int tarif = dokter.getTarif();
        System.out.printf("\nTarif untuk konsultasi adalah %s.\n", Utils.tarifFormat(tarif));
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
            System.out.println("Pembayaran dibatalkan.");
            return false; // Pembayaran batal
        } else if (pilihan > 0 && pilihan <= metodeList.size()) {
            MetodePembayaran metodeTerpilih = metodeList.get(pilihan - 1);
            return Transaksi.prosesPembayaran(pasien, dokter, metodeTerpilih, tarif); // Proses pembayaran
        } else {
            System.out.println("Pilihan tidak valid.");
            return false; // Pilihan tidak valid
        }
    }
    
    @Override
    public void display(){
        System.out.println("ID              : " + super.getId());
        System.out.println("Nama            : " + super.getName());
        System.out.println("Email           : " + super.getEmail());
        System.out.println("Tanggal lahir   : " + this.tglLahir);
        System.out.println("Usia            : " + this.usia);
        System.out.println("Jenis Kelamin   : " + this.jenisKelamin);
        System.out.println("Riwayat         : " + this.riwayatKesehatan);
        System.out.println("Alergi          : " + this.alergi);
        if(daftarAsuransi.isEmpty()){
            System.out.println("Asuransi        : -");
        } else {
            System.out.print("Asuransi        : ");
            for (int i = 0; i < daftarAsuransi.size(); i++) {
            System.out.printf("%s %s\n", "-", daftarAsuransi.get(i).getNamaAsuransi());
            }
        }    
    }
    
    public List<Transaksi> getRiwayatTransaksi() {
        return riwayatTransaksi;
    }

    public void tambahTransaksi(Transaksi transaksi) {
        riwayatTransaksi.add(transaksi);
        System.out.println("Transaksi berhasil disimpan.");
    }

    public void tampilkanRiwayatTransaksi() {
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Tidak ada riwayat transaksi.");
        } else {
            System.out.println("\n=== Riwayat Transaksi ===");
            for (int i = 0; i < riwayatTransaksi.size(); i++) {
                Transaksi transaksi = riwayatTransaksi.get(i);
                System.out.printf("%d. %s | %s | Dokter: %s | Tarif: %s\n",
                        i + 1, transaksi.getTanggal(), transaksi.getMetodePembayaran(),
                        transaksi.getNamaDokter(), transaksi.getFormattedTarif());
            }
        }
    }
}

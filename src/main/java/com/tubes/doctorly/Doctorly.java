package com.tubes.doctorly;
import java.util.*;
import java.time.LocalDate;

public class Doctorly {

    public static void main(String[] args) {
        RegistrationService reg = new RegistrationService();
        Scanner input = new Scanner(System.in);
        Admin superAdmin = new Admin("Super Admin", "doctorly@mymail.com", "031224");
        
        Asuransi.tambahAsuransiBaru(new Asuransi("BPJS Kesehatan", "123456789", "BPJS"));
        Asuransi.tambahAsuransiBaru(new Asuransi("Prudential", "987654321", "Swasta"));
        Asuransi.tambahAsuransiBaru(new Asuransi("AXA Mandiri", "112233445", "Swasta"));
        
        //dummy users
        reg.registerPasien("Apu", "apugemas@gmail.com", "1234", LocalDate.of(2007, 4, 27), "Laki-laki");
        reg.registerDokter("Dr. Gema", "gemathedragon@gmail.com", "4747", "Umum", "Universitas Pernagaan Indonesia", "RS Mamzar", superAdmin);
        
        //start
        while (true) {
            System.out.println("\u001B[34m=========================================");
            System.out.println("                WELCOME TO         ");
            System.out.println("                 DOKTERLY          ");
            System.out.println("=========================================\u001B[0m");
            System.out.println("1. Login                                ");
            System.out.println("2. Belum punya akun?, Register disini!  ");
            System.out.println("3. Exit                                 ");
            System.out.println("=========================================");
            System.out.print("Menu: ");
            int opsi = input.nextInt();
            input.nextLine();
        
            switch (opsi){
                case 1-> {
                    System.out.println("\n=== Login ===");
                    // Input email
                    System.out.print("Email    : ");
                    String email = input.nextLine();
        
                    // Input password
                    System.out.print("Password : ");
                    String password = input.nextLine();
                    
                    User user = reg.login(email, password);
                    if(user == null){
                        System.out.println("\nPassword atau email kamu salah, cek lagi ya!" + "\n");
                    }else{
                        System.out.println("\nLogin berhasil, Selamat datang, ka " + user.getName() + "!\n");
                        user.display();
                        switch (user.getRole()) { //Casting broooooooo!!!
                            case "Pasien" ->{
                                Pasien pasien = (Pasien) user;
                                userMenu.pasienMenu(reg, pasien);
                            }
                            case "Dokter" ->{
                                Dokter dokter = (Dokter) user;
                                userMenu.dokterMenu(reg, dokter);
                            }
                            case "Admin" ->{
                                Admin admin = (Admin) user;
                                userMenu.adminMenu(reg, admin);
                            } 
                        }
                    }
                    
                }
                case 2-> Pasien.register(reg);
                case 3-> {
                    return;
                }
                default -> System.out.println("Pastiin pilih salah satu menu diatas ya!");
            }
        }
    }
}

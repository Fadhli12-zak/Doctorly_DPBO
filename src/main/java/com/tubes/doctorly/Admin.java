
package com.tubes.doctorly;

import java.util.Scanner;

public class Admin extends User {
    public Admin(String nama, String email, String password) {
        super(nama, email, password, "Admin");
    }
    
    public void tambahAsuransi(){
        Scanner input = new Scanner(System.in);
        System.out.print("Nama Asuransi: ");
        String namaAsuransi = input.nextLine();
        System.out.print("Nomor Polis: ");
        String nomorPolis = input.nextLine();
        System.out.print("Jenis Asuransi (BPJS/Swasta/Syariah): ");
        String jenisAsuransi = input.nextLine();
        
        Asuransi asuransiBaru = new Asuransi(namaAsuransi, nomorPolis, jenisAsuransi);
        Asuransi.tambahAsuransiBaru(asuransiBaru);
    }
    
    @Override
    public void display(){
        System.out.println("ID\t\t: " + super.getId());
        System.out.println("Nama\t\t: " + super.getName());
        System.out.println("Email\t\t: " + super.getEmail() + "\n");
    }
}

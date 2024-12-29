package com.tubes.doctorly;

public class Dokter extends User{
    private String spesialisasi;
    private String riwayatPendidikan;
    private String tempatPraktik;
    private int tarif;

    public Dokter(String name, String email, String password, String spesialisasi, String riwayatPendidikan, String tempatPraktik) {
        super(name, email, password, "Dokter");
        this.spesialisasi = spesialisasi;
        this.riwayatPendidikan = riwayatPendidikan;
        this.tempatPraktik = tempatPraktik;
        if(spesialisasi.equalsIgnoreCase("Umum")){
            this.tarif = 9500;
        } else {
            this.tarif = 75000;
        }
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public String getRiwayatPendidikan() {
        return riwayatPendidikan;
    }
    
    public int getTarif() {
        return tarif;
    }

    public String getTempatPraktik() {
        return tempatPraktik;
    }

    public void setRiwayatPendidikan(String riwayatPendidikan) {
        this.riwayatPendidikan = riwayatPendidikan;
    }

    public void setTempatPraktik(String tempatPraktik) {
        this.tempatPraktik = tempatPraktik;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }
    
    @Override
    public void display(){
        System.out.println("ID                  : " + super.getId());
        System.out.println("Nama                : " + super.getName());
        System.out.println("Email               : " + super.getEmail());
        System.out.println("Spesialis           : " + this.spesialisasi);
        System.out.println("Riwayat Pendidikan  : " + this.riwayatPendidikan);
        System.out.println("Tempat Praktik      : " + this.tempatPraktik + "\n");
    }
}

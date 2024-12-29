package com.tubes.dokterly.communication;

import com.tubes.dokterly.user.Dokter;
import com.tubes.dokterly.user.Pasien;

public class Chat {
    private Pasien pengirim;
    private Dokter penerima;
    private String pesan;

    public Chat(Pasien pengirim, Dokter penerima, String pesan) {
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.pesan = pesan;
    }

    public Pasien getPengirim() {
        return pengirim;
    }

    public Dokter getPenerima() {
        return penerima;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    @Override
    public String toString() {
        return String.format("Dari: %s, Kepada: %s, Pesan: %s",
                pengirim.getName(), penerima.getName(), pesan);
    }
}
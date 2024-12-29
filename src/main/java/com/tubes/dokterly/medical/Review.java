package com.tubes.dokterly.medical;

public class Review {
    private String pasienName;
    private int rating; // Skala 1-5
    private String komentar;

    // Konstruktor
    public Review(String pasienName, int rating, String komentar) {
        this.pasienName = pasienName;
        this.rating = rating;
        this.komentar = komentar;
    }

    // Getter
    public String getPasienName() {
        return pasienName;
    }

    public int getRating() {
        return rating;
    }

    public String getKomentar() {
        return komentar;
    }

    // Menampilkan review dalam format yang rapi
    @Override
    public String toString() {
        return String.format("Pasien: %s | Rating: %d/5 | Komentar: %s",
                pasienName, rating, komentar != null && !komentar.isEmpty() ? komentar : "Tidak ada komentar");
    }
}

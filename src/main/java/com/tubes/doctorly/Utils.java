
package com.tubes.doctorly;
import java.time.*;
import java.text.NumberFormat;
import java.time.format.*;
import java.util.Locale;

public class Utils {
    
    public static LocalDate validateDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateStr, formatter); // Parsing berhasil
        } catch (DateTimeParseException e) {
            return null; // Parsing gagal, kembalikan null
        }
    }
    
    public static int hitungUsia(LocalDate tglLahir){
        LocalDate now = LocalDate.now(); // Tanggal saat ini
        return Period.between(tglLahir, now).getYears(); // Menghitung usia dalam tahun
    }
    
    public static String tarifFormat(int tarif){
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedTarif = formatRupiah.format(tarif);
        return formattedTarif;
    }
}

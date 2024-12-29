package com.tubes.dokterly.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    // Validasi format tanggal
    public static LocalDate validateDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal tidak valid. Harus dalam format dd-MM-yyyy.");
            return null;
        }
    }

    // Menghitung usia berdasarkan tanggal lahir
    public static int hitungUsia(LocalDate tglLahir) {
        LocalDate today = LocalDate.now();
        return java.time.Period.between(tglLahir, today).getYears();
    }

    // Format tarif dalam mata uang Indonesia
    public static String tarifFormat(int tarif) {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatRupiah.format(tarif);
    }

    // Validasi input numerik
    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    // Menampilkan header dekoratif untuk UI teks
    public static void printHeader(String title) {
        System.out.println("\n============================");
        System.out.println("        " + title);
        System.out.println("============================\n");
    }
}

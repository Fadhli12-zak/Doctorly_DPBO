package com.tubes.dokterly.medical;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private static List<Article> articles = new ArrayList<>(); // Menyimpan semua artikel
    private String title;
    private String content;
    private String author;

    // Konstruktor
    public Article(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    // Menambah artikel ke daftar
    public static void tambahArtikel(Article article) {
        articles.add(article);
        System.out.println("Artikel berhasil ditambahkan: " + article.getTitle());
    }

    // Menampilkan semua artikel
    public static void tampilkanSemuaArtikel() {
        if (articles.isEmpty()) {
            System.out.println("Belum ada artikel yang tersedia.");
        } else {
            System.out.println("\n=== Daftar Artikel ===");
            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                System.out.printf("%d. %s oleh %s%n", i + 1, article.getTitle(), article.getAuthor());
            }
        }
    }

    // Menampilkan detail artikel
    public void displayArticle() {
        System.out.println("\n=== Detail Artikel ===");
        System.out.println("Judul  : " + title);
        System.out.println("Penulis: " + author);
        System.out.println("Konten :\n" + content);
    }

    // Menghapus artikel berdasarkan indeks
    public static void hapusArtikel(int index) {
        if (index < 0 || index >= articles.size()) {
            System.out.println("Indeks artikel tidak valid.");
        } else {
            Article removed = articles.remove(index);
            System.out.println("Artikel berhasil dihapus: " + removed.getTitle());
        }
    }

    public static List<Article> getArticles() {
        return articles;
    }

    // Mencari artikel berdasarkan judul
    public static void cariArtikel(String keyword) {
        System.out.println("\n=== Hasil Pencarian Artikel ===");
        boolean found = false;
        for (Article article : articles) {
            if (article.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("- %s oleh %s%n", article.getTitle(), article.getAuthor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ditemukan artikel dengan kata kunci: " + keyword);
        }
    }
}

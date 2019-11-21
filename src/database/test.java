package database;

import java.io.FileNotFoundException;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        ArtikelLezer lezer = new ArtikelLezer();
        lezer.read("src/bestanden/artikel");
    }
}

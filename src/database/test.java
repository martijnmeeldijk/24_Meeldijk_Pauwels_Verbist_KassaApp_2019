package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        ArtikelLezer lezer = new ArtikelLezer();
        HashMap map = lezer.read("src/bestanden/artikel");
        for(Object i:map.keySet()){
            System.out.println(map.get(i).toString());
        }
    }
}

package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        ArtikelLoadSave lezer = new ArtikelLoadSave();
        lezer.setFilename("src/bestanden/artikel");
        HashMap<Integer, Artikel> map = lezer.load();
        for(Object i:map.keySet()){
            System.out.println(map.get(i).toString());
        }
        map.put(20, new Artikel(23, "bla1", "test1", 21, 1));
        map.put(20, new Artikel(24, "bla", "test", 20, 3));

        try {
            lezer.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

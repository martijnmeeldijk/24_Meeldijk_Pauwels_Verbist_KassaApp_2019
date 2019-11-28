package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class test {
    public static void main(String[] args) throws FileNotFoundException {

        ArtikelTextLoadSave lezer = new ArtikelTextLoadSave();
        System.out.println(lezer.filename);
        HashMap<Integer, Artikel> map = lezer.load();
        for(Object i:map.keySet()){
            System.out.println(map.get(i).toString());
        }
        map.put(22, new Artikel(23, "bla1", "test1", 21, 1));
        map.put(20, new Artikel(24, "bla", "test", 20, 3));

<<<<<<< Updated upstream
       lezer.save(map);
=======

            lezer.save(map);
>>>>>>> Stashed changes

    }
}

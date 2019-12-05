package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface ArtikelDbStrategy {
    HashMap<Integer, Artikel> load();
    void save(HashMap<Integer, Artikel> artikels);
}

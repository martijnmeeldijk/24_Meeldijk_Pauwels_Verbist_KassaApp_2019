package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface ArtikelDbStrategy {
    public HashMap<Integer, Artikel> load();
    public void save(HashMap<Integer, Artikel> artikels);
}

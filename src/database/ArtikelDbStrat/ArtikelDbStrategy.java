package database.ArtikelDbStrat;

import model.artikel.Artikel;

import java.util.HashMap;

public interface ArtikelDbStrategy {
    HashMap<Integer, Artikel> load();
    void save(HashMap<Integer, Artikel> artikels);
}

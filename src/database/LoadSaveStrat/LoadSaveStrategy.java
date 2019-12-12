package database.LoadSaveStrat;

import model.artikel.Artikel;

import java.util.HashMap;

public interface LoadSaveStrategy {
    HashMap<Integer, Artikel> load();
    void save(HashMap<Integer, Artikel> artikels);
}

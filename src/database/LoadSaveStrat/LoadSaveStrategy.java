package database.LoadSaveStrat;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy {
    HashMap<Integer, Artikel> load();
    void save(HashMap<Integer, Artikel> artikels);
}

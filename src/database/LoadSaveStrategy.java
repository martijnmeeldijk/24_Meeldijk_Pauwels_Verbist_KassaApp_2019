package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy extends ArtikelDbStrategy {
    public HashMap<Integer, Artikel> load();
    public void save(HashMap<Integer, Artikel> artikels);
}

package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy {
    public HashMap<Integer, Artikel> load() throws FileNotFoundException;
    public void save(HashMap<Integer, Artikel> artikels);
}

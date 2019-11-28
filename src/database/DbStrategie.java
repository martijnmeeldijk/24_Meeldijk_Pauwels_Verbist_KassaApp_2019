package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface DbStrategie {
    public HashMap<Integer, Artikel> load(String filename) throws FileNotFoundException;
    public void save();

}

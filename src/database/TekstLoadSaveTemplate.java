package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public abstract class TekstLoadSaveTemplate {
    public abstract  HashMap<Integer, Artikel> load(String filename) throws FileNotFoundException;
    public abstract void save(String filename) throws IOException;

}

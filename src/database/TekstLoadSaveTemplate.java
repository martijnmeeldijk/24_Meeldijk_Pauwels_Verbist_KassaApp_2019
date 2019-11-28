package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy{
    public abstract  HashMap<Integer, Artikel> load();
    public abstract void save(HashMap<Integer, Artikel> artikels);
    //public abstract boolean hook();

}

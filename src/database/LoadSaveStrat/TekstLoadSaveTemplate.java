package database.LoadSaveStrat;

import model.artikel.Artikel;

import java.util.HashMap;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    public abstract  HashMap<Integer, Artikel> load();
    public abstract void save(HashMap<Integer, Artikel> artikels);
    //public abstract boolean hook();

}

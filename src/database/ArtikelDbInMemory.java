package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    LoadSaveStrategy loadSaveStrategy;

    @Override
    public HashMap<Integer, Artikel> load(String filename) throws FileNotFoundException {
        return null;
    }

    @Override
    public void save() {
    }

    public void setStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

}

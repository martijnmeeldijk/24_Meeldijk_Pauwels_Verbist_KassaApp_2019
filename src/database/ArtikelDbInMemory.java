package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private LoadSaveStrategy loadSaveStrategy;

    @Override
    public HashMap<Integer, Artikel> load() {
        return loadSaveStrategy.load();
    }

    @Override
    public void save(HashMap<Integer, Artikel> artikels) {
    }

    public void setStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

}

package database;

import model.Artikel;

import java.util.HashMap;

public class LoadSaveContext {
    private LoadSaveStrategy loadSaveStrategy;

    public LoadSaveContext(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public void setStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public HashMap<Integer, Artikel> load(){
        return loadSaveStrategy.load();
    }
    public void save(HashMap<Integer, Artikel> artikels){
        loadSaveStrategy.save(artikels);
    }
}

package database;

import model.Artikel;

import java.util.HashMap;

public class ArtikelDbContext {
    private ArtikelDbStrategy artikelDbStrategy;

    public ArtikelDbContext(ArtikelDbStrategy artikelDbStrategy) {
        this.artikelDbStrategy = artikelDbStrategy;
    }

    public void setStrategy(ArtikelDbStrategy artikelDbStrategy){
        this.artikelDbStrategy = artikelDbStrategy;
    }
    public HashMap<Integer, Artikel> load(){
        return artikelDbStrategy.load();
    }
    public void save(HashMap<Integer, Artikel> artikels){
        artikelDbStrategy.save(artikels);
    }


}

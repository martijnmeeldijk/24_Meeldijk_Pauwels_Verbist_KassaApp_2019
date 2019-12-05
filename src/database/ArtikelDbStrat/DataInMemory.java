package database.ArtikelDbStrat;

import database.ArtikelDbStrat.ArtikelDbContext;
import model.Artikel;

import java.util.HashMap;

public class DataInMemory {
    public ArtikelDbContext getArtikelDbContext() {
        return artikelDbContext;
    }

    private ArtikelDbContext artikelDbContext;
    private HashMap<Integer, Artikel>artikels;

    public DataInMemory() {
        this.artikelDbContext = new ArtikelDbContext();
        artikels = artikelDbContext.load();
    }

    public void saveData(){
        artikelDbContext.save(artikels);
    }

    public Artikel getArtikel(int getal){
        try{
            return artikels.get(getal);
        }catch (Exception e){
            //throw new IllegalArgumentException("verkeerd getal meegegeven bij opvragen artikel");
            return null;
        }

    }

    public HashMap<Integer, Artikel> getArtikels() {
        return artikels;
    }


}

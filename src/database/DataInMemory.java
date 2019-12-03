package database;

import controller.Observer;
import model.Artikel;
import model.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataInMemory {
    private ArtikelDbContext artikelDbContext;
    private HashMap<Integer, Artikel>artikels;

    public DataInMemory() {
        this.artikelDbContext = new ArtikelDbContext();
        artikels= artikelDbContext.load();
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

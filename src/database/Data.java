package database;

import model.Artikel;

import java.util.HashMap;

public class Data {
    private ArtikelDbContext artikelDbContext;
    private HashMap<Integer, Artikel>artikels;

    public Data(ArtikelDbContext artikelDbContext) {
        this.artikelDbContext = artikelDbContext;
        artikels= artikelDbContext.load();
    }

    public void SaveData(){
        artikelDbContext.save(artikels);
    }

    

}

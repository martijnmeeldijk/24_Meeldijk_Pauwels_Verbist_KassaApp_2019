package database;

public class ArtikelDbContext {
    private ArtikelDbStrategy artikelDbStrategy;

    public void setStrategy(ArtikelDbStrategy artikelDbStrategy){
        this.artikelDbStrategy = artikelDbStrategy;
    }

}

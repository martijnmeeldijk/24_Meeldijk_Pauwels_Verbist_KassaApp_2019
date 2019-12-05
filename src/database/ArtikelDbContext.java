package database;

import database.Factory.ArtikelDbStrategyFactory;
import model.Artikel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ArtikelDbContext {
    private ArtikelDbStrategy artikelDbStrategy;
    private Properties properties;

    public ArtikelDbContext() {
        properties = loadProperties("src/database/database.properties");
        // Ik lees een properties file uit
        // an Laat ik de factory de klasse maken die in de properties file staat
        // Luister naar King Marti op SoundCloud
        this.setStrategy(ArtikelDbStrategyFactory.getInstance().createArtikelDbStrategy(properties.getProperty("ArtikelDbStrategy")));
    }

    private void setStrategy(ArtikelDbStrategy artikelDbStrategy){
        this.artikelDbStrategy = artikelDbStrategy;
    }

    public HashMap<Integer, Artikel> load(){
        return artikelDbStrategy.load();
    }
    public void save(HashMap<Integer, Artikel> artikels){
        artikelDbStrategy.save(artikels);
    }

    private Properties loadProperties(String filename){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }


}

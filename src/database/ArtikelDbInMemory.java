package database;

import database.Factory.LoadSaveStrategyFactory;
import model.Artikel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private LoadSaveStrategy loadSaveStrategy;
    Properties properties;

    @Override
    public HashMap<Integer, Artikel> load() {
        return loadSaveStrategy.load();
    }

    public ArtikelDbInMemory() {
        properties = loadProperties("src/database/database.properties");
        // Ik lees een properties file uit
        // an Laat ik de factory de klasse maken die in de properties file staat
        // Luister naar King Marti op SoundCloud
        this.setStrategy(LoadSaveStrategyFactory.getInstance().createArtikelDbStrategy(properties.getProperty("LoadSaveStrategy")));
    }

    @Override
    public void save(HashMap<Integer, Artikel> artikels) {
        loadSaveStrategy.save(artikels);
    }

    public void setStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
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

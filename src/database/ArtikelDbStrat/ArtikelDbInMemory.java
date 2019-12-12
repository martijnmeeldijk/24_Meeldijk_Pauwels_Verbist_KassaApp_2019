package database.ArtikelDbStrat;

import database.Factory.LoadSaveStrategyFactory;
import database.LoadSaveStrat.LoadSaveStrategy;
import model.artikel.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class ArtikelDbInMemory implements ArtikelDbStrategy {
    private LoadSaveStrategy loadSaveStrategy;
    private Properties properties;
    private String filename = "src/database/database.properties";

    @Override
    public HashMap<Integer, Artikel> load() {
        return loadSaveStrategy.load();
    }

    public ArtikelDbInMemory() {
        properties = loadProperties();
        // Ik lees een properties file uit
        // an Laat ik de factory de klasse maken die in de properties file staat
        // Luister naar King Marti op SoundCloud
        this.setStrategy(LoadSaveStrategyFactory.getInstance().createArtikelDbStrategy(properties.getProperty("LoadSaveStrategy")));
    }

    @Override
    public void save(HashMap<Integer, Artikel> artikels) {
        loadSaveStrategy.save(artikels);
    }

    private void setStrategy(LoadSaveStrategy loadSaveStrategy){
        this.loadSaveStrategy = loadSaveStrategy;
    }

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    /*private void saveProperties(Properties prop){
        try (OutputStream output = new FileOutputStream(filename)) {

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }*/
}

package database.ArtikelDbStrat;

import database.Factory.ArtikelDbStrategyFactory;
import model.artikel.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class ArtikelDbContext {
    private ArtikelDbStrategy artikelDbStrategy;
    private Properties properties;
    private String pad = "src/database/database.properties";

    public ArtikelDbContext() {
        properties = loadProperties();
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

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(pad)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public void saveProperties(String loadSaveStrategy){
        try (OutputStream output = new FileOutputStream(pad)) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("ArtikelDbStrategy", "ArtikelDbInMemory");
            prop.setProperty("LoadSaveStrategy", loadSaveStrategy);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

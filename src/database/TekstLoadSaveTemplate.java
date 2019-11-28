package database;

import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public abstract class TekstLoadSaveTemplate {
    protected String filename;
    public abstract  HashMap<Integer, Artikel> load() throws FileNotFoundException;
    public abstract void save() throws IOException;
    public abstract void setFilename(String filename);

}

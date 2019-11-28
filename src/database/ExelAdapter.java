package database;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExelAdapter implements LoadSaveStrategie{
        ExcelPlugin excelPlugin;

    public ExelAdapter() {
        excelPlugin= new ExcelPlugin();
    }

    @Override
        public HashMap<Integer, Artikel> load(String filename) throws FileNotFoundException {
            File file = new File(filename);
            try {
                excelPlugin.read(file);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void save(String filename, ArrayList<ArrayList<String>> artikels) {
            File file = new File(filename);
            try {
                excelPlugin.write(file,artikels);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }

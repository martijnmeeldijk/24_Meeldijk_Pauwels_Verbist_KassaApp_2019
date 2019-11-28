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

public class ExcelAdapter implements LoadSaveStrategy {
        ExcelPlugin excelPlugin;

    public ExcelAdapter() {
        excelPlugin= new ExcelPlugin();
    }

    //@Override
        public HashMap<Integer, Artikel> load() throws FileNotFoundException {
            File file = new File("");
            try {
                excelPlugin.read(file);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //@Override
        public void save(HashMap<Integer, Artikel> artikels) {
        //TODO ArrayList<ArrayList<String>> artikels
            File file = new File("filename");
            /*try {
                excelPlugin.write(file,artikels);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

             */
        }
    }

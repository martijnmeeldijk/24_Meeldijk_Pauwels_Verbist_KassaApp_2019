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
        public HashMap<Integer, Artikel> load(){
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

        public ArrayList<String> geefArray(Artikel artikel){
        ArrayList<String>artikelInLijst= new ArrayList<>();
        artikelInLijst.add(String.valueOf(artikel.getCode()));
        artikelInLijst.add(artikel.getArtikelgroep());
        artikelInLijst.add(artikel.getOmschrijving());
        artikelInLijst.add(String.valueOf(artikel.getVoorraad()));
        artikelInLijst.add(String.valueOf(artikel.getVerkoopprijs()));
        return artikelInLijst
        }

        @Override
        public void save(HashMap<Integer, Artikel> artikels) {
        //TODO ArrayList<ArrayList<String>> artikels
            ArrayList<ArrayList<String>>items= new ArrayList<ArrayList<String>>();
            for(Integer key: artikels.keySet()){
                items.add(geefArray(artikels.get(key)));
            }

            File file = new File("filename");
            try {
                excelPlugin.write(file,items);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }

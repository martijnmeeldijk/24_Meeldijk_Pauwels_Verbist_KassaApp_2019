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

    @Override
        public HashMap<Integer, Artikel> load(){
            HashMap<Integer,Artikel>artikels= new HashMap<>();
            File file = new File("src/bestanden/artikel.xls");
            try {
                ArrayList<ArrayList<String>> excelLijst=excelPlugin.read(file);
                for(ArrayList<String> artikel: excelLijst){
                    Integer code = Integer.parseInt(artikel.get(0));
                    Artikel artikel1= Artikel.MaakArtikel(artikel.get(0),artikel.get(1),artikel.get(2),artikel.get(3),artikel.get(4));
                    artikels.put(code,artikel1);
                }
                System.out.println(excelLijst);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return artikels;
        }

        public ArrayList<String> geefArray(Artikel artikel){
        ArrayList<String>artikelInLijst= new ArrayList<>();
        artikelInLijst.add(String.valueOf(artikel.getCode()));
        artikelInLijst.add(artikel.getArtikelgroep());
        artikelInLijst.add(artikel.getOmschrijving());
        artikelInLijst.add(String.valueOf(artikel.getVoorraad()));
        artikelInLijst.add(String.valueOf(artikel.getVerkoopprijs()));
        return artikelInLijst;
        }

        @Override
        public void save(HashMap<Integer, Artikel> artikels) {
            ArrayList<ArrayList<String>>items= new ArrayList<ArrayList<String>>();
            for(Integer key: artikels.keySet()){
                items.add(geefArray(artikels.get(key)));
            }

            File file = new File("src/bestanden/artikel.xls");
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

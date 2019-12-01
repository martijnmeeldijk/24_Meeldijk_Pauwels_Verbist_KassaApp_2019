package database;

import database.Factory.ArtikelDbStrategyFactory;
import model.Artikel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args){
        /*ArtikelTextLoadSave lezer = new ArtikelTextLoadSave();
        HashMap<Integer, Artikel> map = lezer.load();
        for(Object i:map.keySet()){
          //  System.out.println(map.get(i).toString());
        }
        map.put(22, new Artikel(23, "bla1", "test1", 21, 1));
        map.put(20, new Artikel(24, "bla", "test", 20, 3));
        lezer.save(map);

        ExcelAdapter excelAdapter= new ExcelAdapter();
        HashMap<Integer, Artikel> test;
        test= excelAdapter.load();
        test.put(33, new Artikel(77, "rara", "ririr", 8, 9));
        System.out.println(test);

        test.put(20, new Artikel(24, "bla", "test", 20, 3));
        excelAdapter.save(test);*/
        ArtikelDbStrategyFactory.getInstance().createArtikelDbStrategy("ArtikelDbInMemory");

        ArtikelDbContext context = new ArtikelDbContext();
        System.out.println(context.load());
    }
}

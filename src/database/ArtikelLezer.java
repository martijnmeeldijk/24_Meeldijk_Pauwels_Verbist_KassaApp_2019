package database;

import com.sun.tools.jdeprscan.scan.Scan;
import model.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelLezer {
    private HashMap read(String filename) throws FileNotFoundException {
        HashMap<Integer, Artikel> artikels = new HashMap<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            Artikel artikel = new Artikel();
            Scanner linescanner = new Scanner(scanner.nextLine());
            linescanner.useDelimiter(",");

            int code = Integer.valueOf(linescanner.next());
            String omschrijving;
            String artikelgroep;
            double verkoopprijs;
            int voorraad;



        }
    }

}

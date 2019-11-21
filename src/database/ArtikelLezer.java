package database;

import com.sun.tools.jdeprscan.scan.Scan;
import model.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelLezer {
    private HashMap<Integer, Artikel> artikels;

    public ArtikelLezer() {
        this.artikels =new HashMap<>();
    }

    public HashMap read(String filename) throws FileNotFoundException {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            Scanner linescanner = new Scanner(scanner.nextLine());
            linescanner.useDelimiter(",");

            int code = Integer.parseInt(linescanner.next());
            String omschrijving = linescanner.next();
            String artikelgroep = linescanner.next();
            double verkoopprijs = Double.parseDouble(linescanner.next());
            int voorraad= Integer.parseInt(linescanner.next());

            Artikel artikel = new Artikel(code,omschrijving,artikelgroep,verkoopprijs,voorraad);

            //effe testen
            System.out.println(artikel.toString());

            artikels.put(code,artikel);
        }

        return artikels;
    }

}

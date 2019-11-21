package database;

import model.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelSchrijver {
    private boolean write(String filename, HashMap<Integer, Artikel> artikels) throws FileNotFoundException {


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

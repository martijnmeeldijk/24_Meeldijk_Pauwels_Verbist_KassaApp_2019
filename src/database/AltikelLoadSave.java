package database;

//import com.sun.tools.jdeprscan.scan.Scan;
import model.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class AltikelLoadSave implements TekstLoadSaveTemplate{
    private HashMap<Integer, Artikel> artikels;

    public AltikelLoadSave() {
        this.artikels =new HashMap<>();
    }

    public HashMap<Integer, Artikel> load(String filename) throws FileNotFoundException {

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

            artikels.put(code,artikel);
        }

        return artikels;
    }
    public void save(String filename) throws IOException {
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for(Integer a : artikels.keySet()){

        }

        writer.close();
    }

}

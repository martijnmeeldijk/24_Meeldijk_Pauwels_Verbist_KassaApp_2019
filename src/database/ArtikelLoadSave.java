package database;

//import com.sun.tools.jdeprscan.scan.Scan;
import model.Artikel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelLoadSave extends TekstLoadSaveTemplate{
    private HashMap<Integer, Artikel> artikels;
    public ArtikelLoadSave() {
        this.artikels =new HashMap<>();
    }
    public void setFilename(String filename){
        this.filename = filename;
    }
    public HashMap<Integer, Artikel> load() throws FileNotFoundException {
        // code , omschrijving, artikelgroep, verkoopprijs, voorraad \n
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
    public void save() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for(Integer a : artikels.keySet()){
            Artikel artikel = artikels.get(a);
            int code = artikel.getCode();
            String omschrijving = artikel.getOmschrijving();
            String artikelgroep = artikel.getArtikelgroep();
            double verkoopprijs = artikel.getVerkoopprijs();
            int voorraad= artikel.getVoorraad();

            writer.write(code + ","+ omschrijving+ ","+ artikelgroep+ ","+ verkoopprijs+ ","+ voorraad);
        }


        writer.close();
    }

}

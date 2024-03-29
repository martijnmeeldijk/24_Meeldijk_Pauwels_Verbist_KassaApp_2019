package database.LoadSaveStrat;

//import com.sun.tools.jdeprscan.scan.Scan;
import model.artikel.Artikel;
import model.artikel.Artikelgroep;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ArtikelTextLoadSave extends TekstLoadSaveTemplate {
    private String filename = "src/bestanden/artikel.txt";
    private HashMap<Integer, Artikel> artikels;
    public ArtikelTextLoadSave() {
        this.artikels =new HashMap<>();
    }

    public HashMap<Integer, Artikel> load(){
        // code , omschrijving, artikelgroep, verkoopprijs, voorraad \n
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
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
    public void save(HashMap<Integer, Artikel> artikels){
        try {
            BufferedWriter writer;

            writer = new BufferedWriter(new FileWriter(filename));

            for(Integer a : artikels.keySet()){
                Artikel artikel = artikels.get(a);
                int code = artikel.getCode();
                String omschrijving = artikel.getOmschrijving();
                Artikelgroep artikelgroep = artikel.getArtikelgroep();
                double verkoopprijs = artikel.getVerkoopprijs();
                int voorraad= artikel.getVoorraad();

                writer.write(code + ","+ omschrijving+ ","+ artikelgroep+ ","+ verkoopprijs+ ","+ voorraad +"\n");
        }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package model.bestelling;

import model.Artikelgroep;
import model.korting.*;

public class BestellingTest {
    public static void main(String[] args){
        Bestelling bestelling = new Bestelling();
        bestelling.addArtikel(1);
        bestelling.betaal();
    }
}

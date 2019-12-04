package model.bestelling;

public class BestellingTest {
    public static void main(String[] args){
        Bestelling bestelling = new Bestelling();
        bestelling.addArtikel(1);
        bestelling.zetOnHold();
        bestelling.addArtikel(1);
    }
}

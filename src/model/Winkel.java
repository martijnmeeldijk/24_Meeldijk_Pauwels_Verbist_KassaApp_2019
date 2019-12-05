package model;

import model.bestelling.Bestelling;

import java.util.ArrayList;

public class Winkel {
    ArrayList<Bestelling> bestellingen;

    public Winkel() {
        bestellingen = new ArrayList<>();
    }

    public void addBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                throw new IllegalStateException("er is nog een actieve bestelling dus geen nieuwe toevoegen");
            }
        }
        bestellingen.add(new Bestelling());
    }

}

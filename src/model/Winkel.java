package model;

import model.bestelling.Bestelling;

import java.util.ArrayList;

public class Winkel {
    ArrayList<Bestelling> bestellingen;
    //dataInMemory kan wss beter hier in winkel worden toegevoegd ipv in elke bestelling appart dus nog refactoren

    public Winkel() {
        bestellingen = new ArrayList<>();
        bestellingen.add(new Bestelling());

    }

    public void addBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                throw new IllegalStateException("er is nog een actieve bestelling dus geen nieuwe toevoegen");
            }
        }
        bestellingen.add(new Bestelling());
    }

    public Bestelling getActieveBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                return bestelling;
            }
        }
        return null;
    }

    public void verwisselActieveBestellingen(){
        //deze methode werkt enkel als er maar 2 bestellingen zijn want hij zet alles op actief of op hold (hij wisselt ze gewoon)
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                bestelling.zetOnHold();
            }
            else {
            bestelling.isActief();
            }
        }
    }

    public Bestelling getpassiveBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(!bestelling.isActief()){
                return bestelling;
            }
        }
        return null;
    }


}

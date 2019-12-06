package model;

import controller.Observer;
import model.bestelling.Bestelling;

import java.util.ArrayList;

public class Winkel implements Subject{
    private ArrayList<Observer> observers;
    private ArrayList<Bestelling> bestellingen;
    int timeheld;
    //dataInMemory kan wss beter hier in winkel worden toegevoegd ipv in elke bestelling appart dus nog refactoren

    public Winkel() {
        observers=new ArrayList<>();
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

    public void addArtikel(int code){
        getActieveBestelling().addArtikel(code);
        notifyObserver();
    }
    public void removeArtikel(int code){
        getActieveBestelling().getArtikels().remove(getActieveBestelling().getDataInMemory().getArtikel(code));
        notifyObserver();
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
    @Override
    public void notifyObserver() {
        System.out.println("bestelling : update");
        for(Observer observer:observers){
            System.out.println(observer);
            observer.update();
        }
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
        notifyObserver();
    }

    @Override
    public void remove(Observer observer) {
        observers.add(observer);

    }


}

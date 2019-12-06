package model.bestelling.state;


import model.bestelling.Bestelling;
import model.bestelling.NotPossibleException;

public abstract class BestellingState {
    public Bestelling bestelling;


    public BestellingState(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
    public void zetOnHold(){
        throw new NotPossibleException("De bestelling kan niet 'on hold' gezet worden.");
    }
    public void zetActief(){
        throw new NotPossibleException(("De bestelling kan niet op 'actief' gezet worden"));
    }
    public void addArtikel(int code){
        throw new NotPossibleException(("Het artikel kan niet toegevogd worden want de bestelling is 'On Hold' gezet"));
    }

    public void removeArtikel(int code){
        throw new NotPossibleException("Het artikel kan niet verwijderd worden want de bestelling is 'On Hold' gezet");
    }
    public void sluitAf(){
        throw new NotPossibleException("De bestelling kan niet afgesloten worden want hij staat 'On hold'");
    }

    public  void betaal(){
        throw new NotPossibleException("De bestelling kan nog niet betaald worden want hij is niet afgesloten.");
    }
}

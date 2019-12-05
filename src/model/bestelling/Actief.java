package model.bestelling;

public class Actief extends BestellingState {

    public Actief(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void zetOnHold() {
        bestelling.setCurrentState(bestelling.getOnHold());
    }

    public void addArtikel(int code){
        bestelling.getArtikels().add(bestelling.getDataInMemory().getArtikel(code));
        bestelling.notifyObserver();
    }

    public void removeArtikel(int code){
        bestelling.getArtikels().remove(bestelling.getDataInMemory().getArtikel(code));
        bestelling.notifyObserver();
    }
    public void sluitAf(){
        
    }
}

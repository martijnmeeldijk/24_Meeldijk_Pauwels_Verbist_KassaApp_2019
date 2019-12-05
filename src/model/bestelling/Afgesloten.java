package model.bestelling;

public class Afgesloten extends BestellingState{
    public Afgesloten(Bestelling bestelling) {
        super(bestelling);
    }
    public void betaal(){
        bestelling.setCurrentState(bestelling.getBetaald());
    }

}

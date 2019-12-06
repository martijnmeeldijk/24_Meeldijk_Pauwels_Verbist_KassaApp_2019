package model.bestelling.state;

import model.bestelling.Bestelling;

public class Afgesloten extends BestellingState {
    public Afgesloten(Bestelling bestelling) {
        super(bestelling);
    }
    public void betaal(){
        bestelling.setCurrentState(bestelling.getBetaald());
    }

}

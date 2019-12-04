package model.bestelling;

public class Actief extends BestellingState {

    public Actief(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void zetOnHold() {
        bestelling.setCurrentState(bestelling.getOnHold());
    }
}

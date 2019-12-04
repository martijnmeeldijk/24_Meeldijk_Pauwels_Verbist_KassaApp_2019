package model.bestelling;

public class OnHold extends BestellingState {

    public OnHold(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void zetActief() {
        bestelling.setCurrentState(bestelling.getActief());
    }
}

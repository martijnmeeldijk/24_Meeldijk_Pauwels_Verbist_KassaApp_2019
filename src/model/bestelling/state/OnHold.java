package model.bestelling.state;

import model.bestelling.Bestelling;
import model.bestelling.state.BestellingState;

public class OnHold extends BestellingState {

    public OnHold(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void zetActief() {
        bestelling.setCurrentState(bestelling.getActief());
    }
}

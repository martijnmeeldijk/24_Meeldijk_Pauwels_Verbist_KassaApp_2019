package model.bestelling;


public abstract class BestellingState {
    Bestelling bestelling;
    public void zetOnHold(){
        throw new NotPossibleException("Het artikel kan niet 'on hold' gezet worden.");
    }
    public void zetActief(){
        throw new NotPossibleException(("Het artikel kan niet op 'actief' gezet worden"));
    }

    public BestellingState(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }


}

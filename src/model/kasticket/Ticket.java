package model.kasticket;

import model.Winkel;

public abstract class Ticket {
    Winkel winkel;

    public Ticket(Winkel winkel) {
        this.winkel = winkel;
    }

    public void setWinkel(Winkel winkel) {
        this.winkel = winkel;
    }

    public Winkel getWinkel() {
        return winkel;
    }

    public abstract String print();
}

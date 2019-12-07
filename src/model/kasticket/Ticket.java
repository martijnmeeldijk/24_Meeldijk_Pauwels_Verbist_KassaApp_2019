package model.kasticket;

import model.Winkel;

public abstract class Ticket {
    Winkel winkel;

    public Winkel getWinkel() {
        return winkel;
    }

    public abstract String print();
}

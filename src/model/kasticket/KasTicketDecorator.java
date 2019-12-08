package model.kasticket;

import model.Winkel;

public abstract class KasTicketDecorator extends Ticket {
    public KasTicketDecorator(Winkel winkel) {
        super(winkel);
    }


    public abstract String print();
}

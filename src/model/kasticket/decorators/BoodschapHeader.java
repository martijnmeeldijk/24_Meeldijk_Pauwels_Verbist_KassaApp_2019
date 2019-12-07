package model.kasticket.decorators;

import controller.AantalList;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

public class BoodschapHeader extends KasTicketDecorator {
    Ticket ticket;
    String boodschap;

    public BoodschapHeader(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
    }

    @Override
    public String print() {
        return ticket.print() + "\n" + boodschap;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }
}

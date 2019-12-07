package model.kasticket.decorators;

import controller.AantalList;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

public class BoodschapHeader extends KasTicketDecorator {
    Ticket ticket;

    public BoodschapHeader(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String print() {
        return ticket + "\n" + AantalList.getList(ticket.getWinkel());
    }
}

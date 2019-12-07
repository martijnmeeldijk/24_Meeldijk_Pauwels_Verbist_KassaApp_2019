package model.kasticket.decorators;

import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

public class BoodschapFooter extends KasTicketDecorator {
    Ticket ticket;
    String boodschap;
    public BoodschapFooter(Ticket ticket, Winkel winkel){
        super(winkel);
        this.ticket = ticket;
    }

    public String print() {
        return ticket.print() + "\n" +boodschap;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
    }


}

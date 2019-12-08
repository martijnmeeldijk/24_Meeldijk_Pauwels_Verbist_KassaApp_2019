package model.kasticket;

import model.Winkel;
import model.kasticket.decorators.*;

public class TicketTest {
    public static void main(String[] args){
        Winkel winkel = new Winkel();
        winkel.addArtikel(1);
        winkel.addArtikel(1);
        winkel.addArtikel(1);
        winkel.addArtikel(10);
        /*
        Ticket ticket = new KasTicket(winkel);


        ticket = new BoodschapHeader(ticket, winkel);
        ticket = new DatumHeader(ticket, winkel);

        ticket = new BtwFooter(ticket,winkel);
        ticket = new KortingFooter(ticket, winkel);
        ticket = new BoodschapFooter(ticket, winkel);*/


        //System.out.print(ticket.print());
        Ticket ticket = DecoratorFactory.getInstance().decorateTicket(winkel);
        System.out.print(ticket.print());



    }
}

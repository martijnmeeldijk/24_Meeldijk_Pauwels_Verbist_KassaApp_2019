package model.kasticket.decorators;

import controller.AantalList;
import model.Artikel;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;
import model.korting.Korting;

import java.util.List;

public class KortingFooter extends KasTicketDecorator {
    Ticket ticket;

    public KortingFooter(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
    }

    @Override
    public String print() {
        return ticket.print() + "\nPrijs zonder korting: €" +
                String.format("%.2f", prijsZonderKorting()) + "\nKorting: €" + String.format("%.2f",korting());
    }


    private double prijsZonderKorting() {
        double totaal = 0.0;
        for (Artikel artikel : ticket.getWinkel().getActieveBestelling().getArtikels()) {
            totaal += artikel.getVerkoopprijs();
        }
        return totaal + korting();
    }

    private double korting() {

        double korting = 0.0;
        for (Korting k : ticket.getWinkel().getKortingen()) {
            korting += k.getKorting(AantalList.getList(ticket.getWinkel()));
        }
        return korting;
    }
}

package model.kasticket.decorators;

import controller.AantalList;
import model.artikel.Artikel;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

import java.util.List;

public class BtwFooter extends KasTicketDecorator {
    Ticket ticket;

    public BtwFooter(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
    }

    @Override
    public String print() {
        return ticket.print() + "\n" + "Prijs zonder BTW: €" +
                String.format("%.2f" ,prijsZonderBtw()) + "\n" + "BTW: €" + String.format("%.2f",btw());

    }
    private double somPrijsArtikelen(){
        List artikelen = AantalList.getList(getWinkel());
        double som = 0;
        for(Object o : artikelen){
            Artikel a = (Artikel) o;
            som += a.getVerkoopprijs()*a.getAantal();
        }
        return som;
    }

    private double prijsZonderBtw(){
        return somPrijsArtikelen() - btw();
    }

    private double btw(){
        return somPrijsArtikelen()*(Artikel.getBtw()/100.0);
    }
}

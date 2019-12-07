package model.kasticket.decorators;

import jxl.write.DateTime;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatumHeader extends KasTicketDecorator {
    Ticket ticket;

    public DatumHeader(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
    }

    @Override
    public String print() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now) + "\n" + ticket.print();
    }

}

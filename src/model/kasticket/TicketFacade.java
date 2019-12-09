package model.kasticket;

import model.Winkel;

public class TicketFacade {

    public static String makeTicket(Winkel winkel){
        return DecoratorFactory.getInstance().decorateTicket(winkel).print();
    }
}

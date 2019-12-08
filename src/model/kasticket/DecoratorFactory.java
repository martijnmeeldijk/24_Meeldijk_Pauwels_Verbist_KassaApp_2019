package model.kasticket;
public class DecoratorFactory {


    private static DecoratorFactory unique = new DecoratorFactory();

    private DecoratorFactory() {
    }

    public static DecoratorFactory getInstance(Ticket ticket) {
        return unique;
    }

    public KasTicketDecorator createKasTicketDecorator(String name) {
        KasTicketDecorator decorator;
        try {
            Class strategyClass = Class.forName("model.kasticket.decorators." + name);
            Object strategyObject = strategyClass.newInstance();
            decorator = (KasTicketDecorator) strategyObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error trying to make decorator for " + name);
        }
        return decorator;
    }
}
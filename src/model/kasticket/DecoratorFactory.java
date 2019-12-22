package model.kasticket;

import model.Winkel;
import model.kasticket.decorators.Decorators;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DecoratorFactory {
    private String filename = "src/model/kasticket/ticket.properties";
    private Properties properties;
    private static DecoratorFactory unique = new DecoratorFactory();

    private DecoratorFactory() {
        properties = loadProperties();
    }

    public static DecoratorFactory getInstance() {
        return unique;
    }



    public Ticket decorateTicket(Winkel winkel) {
        Ticket ticket = TicketFactory.getInstance().createTicket(winkel);

        for(Decorators s : Decorators.values()){
            if(properties.getProperty(s.getClassname()).equalsIgnoreCase("true")){
                String name = s.getClassname();
                try {
                    Class decoratorClass = Class.forName("model.kasticket.decorators." + name);
                    Object decoratorObject = decoratorClass.getDeclaredConstructor(Ticket.class, Winkel.class).newInstance(ticket, winkel);
                    ticket = (KasTicketDecorator) decoratorObject;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Error trying to make decorator for " + name);
                }

            }
        }
        return ticket;
    }

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("model/kasticket/ticket.properties")) {
                //new FileInputStream(filename)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    public void saveProperties(){
        try (OutputStream output = new FileOutputStream(filename)) {
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
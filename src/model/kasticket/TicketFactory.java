package model.kasticket;

import model.Winkel;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class TicketFactory {
    private String filename = "src/model/kasticket/ticket.properties";
    private Properties properties;
    private static TicketFactory unique = new TicketFactory();

    private TicketFactory() {
        properties = loadProperties();
    }

    public static TicketFactory getInstance() {
        return unique;
    }

    public Ticket createTicket(Winkel winkel){
        Ticket ticket;
        String name = properties.getProperty("Ticket");
        try {
            Class ticketClass = Class.forName("model.kasticket." + name);
            Object ticketObject = ticketClass.getDeclaredConstructor(Winkel.class).newInstance(winkel);
            ticket = (Ticket) ticketObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error trying to make ticket for " + name);
        }
        return ticket;
    }

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("props/ticket.properties")){
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

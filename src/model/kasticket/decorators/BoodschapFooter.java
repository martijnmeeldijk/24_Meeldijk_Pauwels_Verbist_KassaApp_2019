package model.kasticket.decorators;

import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

import java.io.*;
import java.util.Properties;

public class BoodschapFooter extends KasTicketDecorator {
    Ticket ticket;
    private String boodschap;
    private static String filename = "src/model/kasticket/message.properties";
    private Properties properties;

    public BoodschapFooter(Ticket ticket, Winkel winkel){
        super(winkel);
        this.ticket = ticket;
        properties = loadProperties();
        boodschap = properties.getProperty("FooterMessage");

    }

    public String print() {
        return ticket.print() + "\n" +boodschap;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
        properties.setProperty("FooterMessage", boodschap);
        saveProperties();
    }

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
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

package model.kasticket.decorators;

import controller.AantalList;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

import java.io.*;
import java.util.Properties;

public class BoodschapHeader extends KasTicketDecorator {
    Ticket ticket;
    private String boodschap;
    private static String filename = "src/model/kasticket/message.properties";
    Properties properties;

    public BoodschapHeader(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
        properties = loadProperties();
        boodschap = properties.getProperty("HeaderMessage");
    }

    @Override
    public String print() {
        return boodschap + "\n" + ticket.print();
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
        properties.setProperty("HeaderMessage", boodschap);
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

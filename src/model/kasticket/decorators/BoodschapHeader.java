package model.kasticket.decorators;

import controller.AantalList;
import model.Winkel;
import model.kasticket.KasTicketDecorator;
import model.kasticket.Ticket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BoodschapHeader extends KasTicketDecorator {
    Ticket ticket;
    private String boodschap;
    private String filename = "src/model/kasticket/ticket.properties";
    Properties properties;

    public BoodschapHeader(Ticket ticket, Winkel winkel) {
        super(winkel);
        this.ticket = ticket;
        properties = loadProperties();
        boodschap = properties.getProperty("HeaderMessage");
    }

    @Override
    public String print() {
        return ticket.print() + "\n" + boodschap;
    }

    public void setBoodschap(String boodschap) {
        this.boodschap = boodschap;
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
}

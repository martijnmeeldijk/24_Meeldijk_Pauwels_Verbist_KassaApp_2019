package model.kasticket;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Winkel;
import model.korting.KortingFactory;

import java.io.*;
import java.util.Properties;

public class TicketFacade {
    private static Properties ticketProperties = loadTicketProperties();
    private static Properties messageProperties = loadMessageProperties();
    private static String ticketFilename = "src/model/kasticket/ticket.properties";
    private static String messageFilename = "src/model/kasticket/message.properties";

    private TicketFacade(){}

    public static String makeTicket(Winkel winkel){
        return DecoratorFactory.getInstance().decorateTicket(winkel).print();
    }
    public static void setTicketFilename(String filename){
        TicketFacade.ticketFilename= filename;
    }
    public static void setMessageFilename(String filename){
        TicketFacade.messageFilename= filename;
    }

    /**
     * Set to true to display a custom header
     * @param bool
     */
    public static void setHeader(boolean bool){
        if(bool) ticketProperties.setProperty("BoodschapHeader", "true");
        else ticketProperties.setProperty("BoodschapHeader", "false");
        saveTicketProperties();
    }
    /**
     * Set to true to display a custom footer
     * @param bool
     */
    public static void setFooter(boolean bool){
        if(bool) ticketProperties.setProperty("BoodschapFooter", "true");
        else ticketProperties.setProperty("BoodschapFooter", "false");
        saveTicketProperties();
    }
    /**
     * Set to true to display Btw footer
     * @param bool
     */
    public static void setBtwFooter(boolean bool){
        if(bool) ticketProperties.setProperty("BtwFooter", "true");
        else ticketProperties.setProperty("BtwFooter", "false");
        saveTicketProperties();
    }
    /**
     * Set to true to display the current date in a header
     * @param bool
     */
    public static void setDatumHeader(boolean bool){
        if(bool) ticketProperties.setProperty("DatumHeader", "true");
        else ticketProperties.setProperty("DatumHeader", "false");
        saveTicketProperties();
    }
    /**
     * Set to true to display 'korting' in a footer
     * @param bool
     */
    public static void setKortingFooter(boolean bool){
        if(bool) ticketProperties.setProperty("KortingFooter", "true");
        else ticketProperties.setProperty("KortingFooter", "false");
        saveTicketProperties();
    }

    /**
     * Set a custom message as header
     * @param s
     */
    public static void setCustomHeader(String s){
        messageProperties.setProperty("HeaderMessage", s);
        saveMessageProperties();
    }
    /**
     * Set a custom message as footer
     * @param s
     */
    public static void setCustomFooter(String s){
        messageProperties.setProperty("FooterMessage", s);
        saveMessageProperties();
    }

    /**
     *
     * @return The footer message
     */
    public static String getCustomFooter(){
        return messageProperties.getProperty("FooterMessage");
    }

    /**
     *
     * @return The header message
     */
    public static String getCustomHeader(){
        return messageProperties.getProperty("HeaderMessage");
    }

    public static boolean getHeader(){
        return Boolean.parseBoolean(ticketProperties.getProperty("BoodschapHeader"));
    }
    public static boolean getFooter(){
        return Boolean.parseBoolean(ticketProperties.getProperty("BoodschapFooter"));
    }
    public static boolean getBtwFooter(){
        return Boolean.parseBoolean(ticketProperties.getProperty("BtwFooter"));
    }
    public static boolean getDatumHeader(){
        return Boolean.parseBoolean(ticketProperties.getProperty("DatumHeader"));
    }
    public static boolean getKortingFooter(){
        return Boolean.parseBoolean(ticketProperties.getProperty("KortingFooter"));
    }






    private static Properties loadTicketProperties(){
        Properties prop = new Properties();
        try (InputStream input = //TicketFacade.class.getClassLoader().getResourceAsStream("src/model/kasticket/ticket.properties")){
                     new FileInputStream("src/model/kasticket/ticket.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    private static void saveTicketProperties(){
        try (OutputStream output = new FileOutputStream(ticketFilename)) {
            ticketProperties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static Properties loadMessageProperties() {
        Properties prop = new Properties();
        try (InputStream input = //TicketFacade.class.getClassLoader().getResourceAsStream("src/model/kasticket/message.properties")){
                     new FileInputStream("src/model/kasticket/message.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    private static void saveMessageProperties(){
        try (OutputStream output = new FileOutputStream(messageFilename)) {
            messageProperties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}

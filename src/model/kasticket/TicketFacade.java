package model.kasticket;

import model.Winkel;

import java.io.*;
import java.util.Properties;

public class TicketFacade {
    private static Properties properties = loadProperties();
    private static String filename = "src/model/kasticket/message.properties";

    public static String makeTicket(Winkel winkel){
        return DecoratorFactory.getInstance().decorateTicket(winkel).print();
    }
    public static void setFilename(String filename){
        TicketFacade.filename= filename;
    }
    public static void setHeader(boolean bool){
        if(bool) properties.setProperty("BoodschapHeader", "true");
        else properties.setProperty("BoodschapHeader", "false");
        saveProperties();
    }
    public static void setFooter(boolean bool){
        if(bool) properties.setProperty("BoodschapFooter", "true");
        else properties.setProperty("BoodschapFooter", "false");
        saveProperties();
    }
    public static void setBtwFooter(boolean bool){
        if(bool) properties.setProperty("BtwFooter", "true");
        else properties.setProperty("BtwFooter", "false");
        saveProperties();
    }
    public static void setDatumHeader(boolean bool){
        if(bool) properties.setProperty("DatumHeader", "true");
        else properties.setProperty("DatumHeader", "false");
        saveProperties();
    }
    public static void setKortingFooter(boolean bool){
        if(bool) properties.setProperty("KortingFooter", "true");
        else properties.setProperty("KortingFooter", "false");
        saveProperties();
    }


    
    private static Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    public static void saveProperties(){
        try (OutputStream output = new FileOutputStream(filename)) {
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}

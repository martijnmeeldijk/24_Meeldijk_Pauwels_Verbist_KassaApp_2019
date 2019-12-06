package model;

import controller.Observer;
import model.bestelling.Bestelling;
import model.korting.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Winkel implements Subject{
    private ArrayList<Observer> observers;
    private ArrayList<Bestelling> bestellingen;
    int timeheld;

    private ArrayList<Korting> kortingen = new ArrayList<>();
    private Properties properties;
    private boolean firstKorting = true;

    //dataInMemory kan wss beter hier in winkel worden toegevoegd ipv in elke bestelling appart dus nog refactoren

    public Winkel() {
        observers=new ArrayList<>();
        bestellingen = new ArrayList<>();
        bestellingen.add(new Bestelling());
        loadKortingen();
    }

    public void removeActiveBestelling(){
        bestellingen.remove(getActieveBestelling());
        addBestelling();
    }

    public void addBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                throw new IllegalStateException("er is nog een actieve bestelling dus geen nieuwe toevoegen");
            }
        }
        bestellingen.add(new Bestelling());
    }

    public void addArtikel(int code){
        getActieveBestelling().addArtikel(code);
        notifyObserver();
    }
    public void removeArtikel(int code){
        getActieveBestelling().getArtikels().remove(getActieveBestelling().getDataInMemory().getArtikel(code));
        notifyObserver();
    }

    public Bestelling getActieveBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                return bestelling;
            }
        }
        return null;
    }

    public void verwisselActieveBestellingen(){
        //deze methode werkt enkel als er maar 2 bestellingen zijn want hij zet alles op actief of op hold (hij wisselt ze gewoon)
        for(Bestelling bestelling:bestellingen){
            if(bestelling.isActief()){
                bestelling.zetOnHold();
            }
            else {
            bestelling.isActief();
            }
        }
    }

    public Bestelling getpassiveBestelling(){
        for(Bestelling bestelling:bestellingen){
            if(!bestelling.isActief()){
                return bestelling;
            }
        }
        return null;
    }
    @Override
    public void notifyObserver() {
        System.out.println("bestelling : update");
        for(Observer observer:observers){
            System.out.println(observer);
            observer.update();
        }
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
        notifyObserver();
    }

    @Override
    public void remove(Observer observer) {
        observers.add(observer);

    }

    private Properties loadProperties(){
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/model/korting/korting.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    private void loadKortingen(){
        Properties properties = loadProperties();
        if(!properties.isEmpty()){
            int aantal = Integer.parseInt(properties.getProperty("Aantal"));
            String[] s;
            for(int i=0;i<aantal;i++){
                s= properties.getProperty(String.valueOf(i)).split("/");
                Korting korting = KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.valueOf(s[0]));
                korting.setKortingspercentage(Integer.parseInt(s[1]));

                switch (s[0]){
                    case "Groep" :
                        Groepkorting groep = (Groepkorting) korting;
                        groep.setGroep(Artikelgroep.valueOf(s[2]));
                        break;
                    case "Drempel" :
                        Drempelkorting drempel = (Drempelkorting) korting;
                        drempel.setDrempel(Double.parseDouble(s[2]));
                }

                kortingen.add(korting);
            }
        }
    }

    public void addKorting(Korting korting) {
        try (OutputStream output = new FileOutputStream("src/model/korting/korting.properties")) {
            int aantal=0;
            if(firstKorting){
                properties = new Properties();
                firstKorting=false;
            }
            else {
                aantal=Integer.parseInt(properties.getProperty("Aantal"));
            }

            // set the properties value
            properties.setProperty("Aantal", String.valueOf(aantal+1));

            //add new
            properties.setProperty(String.valueOf(aantal),korting.prop());

            // save properties to project root folder
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    public ArrayList<Korting> getKortingen() {
        return kortingen;
    }
}

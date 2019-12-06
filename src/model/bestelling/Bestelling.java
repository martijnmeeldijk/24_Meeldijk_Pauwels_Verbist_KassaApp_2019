package model.bestelling;

import controller.Observer;
import database.ArtikelDbStrat.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Artikelgroep;
import model.Subject;
import model.bestelling.state.Actief;
import model.bestelling.state.Afgesloten;
import model.bestelling.state.BestellingState;
import model.bestelling.state.OnHold;
import model.korting.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Bestelling {
    private ObservableList<Artikel> artikels;
    private ArrayList<Observer> observers;
    private DataInMemory dataInMemory;

    private BestellingState actief;
    private BestellingState onHold;
    private BestellingState afgesloten;
    private BestellingState betaald;
    private BestellingState currentState;

    private ArrayList<Korting> kortingen = new ArrayList<>();

    private Properties properties;
    private boolean firstKorting = true;

    public ArrayList<Korting> getKortingen() {
        return kortingen;
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

    public Bestelling() {
        observers=new ArrayList<>();
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();

        actief = new Actief(this);
        onHold = new OnHold(this);
        afgesloten = new Afgesloten(this);
        this.currentState = actief;

        loadKortingen();
    }

    public void addArtikel(int code){
        currentState.addArtikel(code);
    }

    public void removeArtikel(int code){
        currentState.removeArtikel(code);
    }

    public boolean itemBestaat(int getal){
        return dataInMemory.getArtikel(getal) != null;
    }

    public DataInMemory getDataInMemory() {
        return dataInMemory;
    }

    public ObservableList<Artikel> getArtikels() {
        return artikels;
    }


    /** STATE **/
    public void zetOnHold(){
        currentState.zetOnHold();
    }
    public void zetActief(){
        currentState.zetActief();
    }
    public void sluitAf(){
        if(!artikels.isEmpty()){
            currentState.sluitAf();
        }
        else{
            throw new NotPossibleException("Je kan geen bestelling afsluiten met een leeg winkelmandje");
        }
    }
    public BestellingState getCurrentState(){
        return currentState;
    }

    public void setCurrentState(BestellingState bestellingState){
        this.currentState = bestellingState;
    }

    public BestellingState getActief() {
        return actief;
    }

    public BestellingState getOnHold() {
        return onHold;
    }

    public boolean isActief(){
        return currentState.getClass() == Actief.class;
    }

    public BestellingState getAfgesloten() {
        return afgesloten;
    }

    public void betaal() {
        currentState.betaal();
    }

    public BestellingState getBetaald() {
        return betaald;
    }
}

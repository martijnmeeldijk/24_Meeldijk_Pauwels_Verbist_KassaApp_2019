package model.bestelling;

import controller.Observer;
import database.ArtikelDbStrat.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Subject;
import model.bestelling.state.Actief;
import model.bestelling.state.Afgesloten;
import model.bestelling.state.BestellingState;
import model.bestelling.state.OnHold;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;

import java.util.ArrayList;

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

    public ArrayList<Korting> getKortingen() {
        return kortingen;
    }

    public void addKorting(Korting korting) {
        kortingen.add(korting);
        for(Korting k:kortingen){
            System.out.println(k.toString());
        }
        //notifyObserver();
    }

    public Bestelling() {
        observers=new ArrayList<>();
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();

        actief = new Actief(this);
        onHold = new OnHold(this);
        afgesloten = new Afgesloten(this);
        this.currentState = actief;

        kortingen.add(KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Nummer));
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

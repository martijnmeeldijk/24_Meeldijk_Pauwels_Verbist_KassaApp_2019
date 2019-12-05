package model.bestelling;

import controller.Observer;
import database.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Subject;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;

import java.util.ArrayList;

public class Bestelling implements Subject {
    private ObservableList<Artikel> artikels;
    private ArrayList<Observer> observers;
    private DataInMemory dataInMemory;

    private BestellingState actief;
    private BestellingState onHold;
    private BestellingState afgesloten;
    private BestellingState currentState;

    private Korting korting = KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Nummer);

    public Korting getKorting() {
        return korting;
    }

    public void setKorting(Korting korting) {
        this.korting = korting;
        notifyObserver();
    }


    public Bestelling() {
        observers=new ArrayList<>();
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();

        actief = new Actief(this);
        onHold = new OnHold(this);
        afgesloten = new Afgesloten(this);
        this.currentState = actief;
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

    @Override
    public void add(Observer observer) {
        observers.add(observer);
        notifyObserver();
    }

    @Override
    public void remove(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void notifyObserver() {
        for(Observer observer:observers){
            observer.update();
        }
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
    public void sluitAf(){currentState.sluitAf();}
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
}

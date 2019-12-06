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

public class Bestelling implements Subject {
    private ObservableList<Artikel> artikels;
    private ArrayList<Observer> observers;
    private DataInMemory dataInMemory;

    private BestellingState actief;
    private BestellingState onHold;
    private BestellingState afgesloten;
    private BestellingState betaald;
    private BestellingState currentState;

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

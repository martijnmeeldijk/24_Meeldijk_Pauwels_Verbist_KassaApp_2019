package model.bestelling;

import controller.Observer;
import database.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Subject;

import java.util.ArrayList;

public class Bestelling implements Subject {
    private ObservableList<Artikel> artikels;
    private ArrayList<Observer> observers;
    private DataInMemory dataInMemory;


    public Bestelling() {
        observers=new ArrayList<>();
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();
    }

    public void addArtikel(int code){
        artikels.add(dataInMemory.getArtikel(code));
        notifyObserver();
    }

    public void removeArtikel(int code){
        artikels.remove(dataInMemory.getArtikel(code));
        notifyObserver();
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
}

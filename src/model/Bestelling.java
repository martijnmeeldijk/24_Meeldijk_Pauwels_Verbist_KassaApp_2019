package model;

import controller.Observer;
import database.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Bestelling implements Subject {
    private ObservableList<Artikel> artikels;
    private ArrayList<Observer> observers;
    DataInMemory dataInMemory;


    public Bestelling() {
        observers=new ArrayList<>();
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();
    }

    public void addArtikel(int code){
        artikels.add(dataInMemory.getArtikel(code));
        notifyObserver();
    }


    public boolean itemBestaat(int getal){
        if(dataInMemory.getArtikel(getal)==null){
            return false;
        }
        return true;
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

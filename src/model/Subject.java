package model;

import controller.Observer;

public interface Subject {
    void add(Observer observer);
    void remove(Observer observer);
    void notifyObserver();


    /* implementatie
    ArrayList<Observer>observers;

    public ArtikelOverviewController() {
        observers= new ArrayList<>();
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {

    }

     */
}

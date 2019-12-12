package model.artikel;

import controller.Observer;

public interface Subject {
    void add(Observer observer);
    void remove(Observer observer);
    void notifyObserver();

}

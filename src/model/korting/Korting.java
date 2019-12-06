package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public interface Korting {
    double getKorting(ObservableList<Artikel> list);
    void setKortingspercentage(int korting);
}

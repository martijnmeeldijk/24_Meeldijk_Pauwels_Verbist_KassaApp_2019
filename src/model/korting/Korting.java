package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public interface Korting {
    double korting(ObservableList<Artikel> list);
    void setKorting(int korting);
}

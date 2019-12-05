package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public interface Korting {
    double PrijsNaKorting(ObservableList<Artikel> list);
    void setKorting(int korting);
}

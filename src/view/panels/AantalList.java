package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;

public class AantalList {
    public static ObservableList<Artikel> getList(Winkel winkel) {
        ObservableList<Artikel> tijdelijk= FXCollections.observableArrayList();
        for(Artikel artikel:winkel.getActieveBestelling().getArtikels()){
            if(tijdelijk.contains(artikel)){
                int index= tijdelijk.indexOf(artikel);
                tijdelijk.get(index).setAantal(tijdelijk.get(index).getAantal()+1);
            }
            else {
                artikel.setAantal(1);
                tijdelijk.add(artikel);
            }
        }
        return tijdelijk;
    }
}

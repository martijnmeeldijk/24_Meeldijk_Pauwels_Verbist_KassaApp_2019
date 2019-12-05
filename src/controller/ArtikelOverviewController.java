package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import model.OmschrijvingComparable;
import view.panels.ArtikelOverviewPane;

import java.util.Comparator;
import java.util.HashMap;

public class ArtikelOverviewController implements Observer {
    private static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();
    private Winkel winkel;

    public ArtikelOverviewController(Winkel winkel) {
        this.winkel = winkel;
    }

    public void setArtikelOverviewPane(ArtikelOverviewPane artikelOverviewPane) {
        update();
    }

    public ObservableList<Artikel> getList(){
        ObservableList<Artikel>artikels= FXCollections.observableArrayList();
        HashMap<Integer,Artikel>hashartikels=winkel.getActieveBestelling().getDataInMemory().getArtikels();
        for(int key:hashartikels.keySet()){
            artikels.add(hashartikels.get(key));
        }
        artikels.sort(omschrijvingcomperator);
        return artikels;
    }

    public Bestelling getBestelling() {
        return winkel.getActieveBestelling();
    }


    @Override
    public void update() {
    }
}

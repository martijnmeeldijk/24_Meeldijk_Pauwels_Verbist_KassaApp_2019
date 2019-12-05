package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.bestelling.Bestelling;
import model.OmschrijvingComparable;
import view.panels.ArtikelOverviewPane;

import java.util.Comparator;
import java.util.HashMap;

public class ArtikelOverviewController implements Observer {
    private static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();
    private Bestelling bestelling;

    public ArtikelOverviewController(Bestelling bestelling) {
        this.bestelling=bestelling;
    }

    public void setArtikelOverviewPane(ArtikelOverviewPane artikelOverviewPane) {
        update();
    }

    public ObservableList<Artikel> getList(){
        ObservableList<Artikel>artikels= FXCollections.observableArrayList();
        HashMap<Integer,Artikel>hashartikels=bestelling.getDataInMemory().getArtikels();
        for(int key:hashartikels.keySet()){
            artikels.add(hashartikels.get(key));
        }
        artikels.sort(omschrijvingcomperator);
        return artikels;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }


    @Override
    public void update() {
        //artikelOverviewPane.setList(getList());
    }
}

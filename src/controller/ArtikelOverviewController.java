package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Subject;
import view.panels.ArtikelOverviewPane;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtikelOverviewController implements Observer {
    private ArtikelOverviewPane artikelOverviewPane;

    public ArtikelOverviewController() {
    }

    public void setArtikelOverviewPane(ArtikelOverviewPane artikelOverviewPane) {
        this.artikelOverviewPane = artikelOverviewPane;
    }

    @Override
    public void update() {

    }
}

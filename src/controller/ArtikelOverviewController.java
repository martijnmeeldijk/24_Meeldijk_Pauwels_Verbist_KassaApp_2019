package controller;

import model.Subject;
import view.panels.ArtikelOverviewPane;

import java.util.ArrayList;

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

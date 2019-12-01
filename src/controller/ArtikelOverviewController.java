package controller;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.OmschrijvingComparable;
import model.Subject;
import view.panels.ArtikelOverviewPane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ArtikelOverviewController implements Observer {
    private ArtikelOverviewPane artikelOverviewPane;
    public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();
    ArtikelDbStrategy artikelDbStrategy=new ExcelAdapter();
    ArtikelDbContext artikelDbContext= new ArtikelDbContext(artikelDbStrategy);
    DataInMemory data = new DataInMemory(artikelDbContext);
    public ArtikelOverviewController() {

    }

    public void setArtikelOverviewPane(ArtikelOverviewPane artikelOverviewPane) {
        this.artikelOverviewPane = artikelOverviewPane;
        update();
    }

    /*
    ObservableList<Artikel>artikels= FXCollections.observableArrayList();
		HashMap<Integer,Artikel>hashartikels=data.getArtikels();
		for(int key:hashartikels.keySet()){
			artikels.add(hashartikels.get(key));
		}
		artikels.sort(omschrijvingcomperator);
     */
    private ObservableList<Artikel> getList(){
        ObservableList<Artikel>artikels= FXCollections.observableArrayList();
        HashMap<Integer,Artikel>hashartikels=data.getArtikels();
        for(int key:hashartikels.keySet()){
            artikels.add(hashartikels.get(key));
        }
        artikels.sort(omschrijvingcomperator);
        return artikels;
    }

    @Override
    public void update() {
        System.out.println("ArtikelOverviewController: updated");
        artikelOverviewPane.setList(getList());
    }
}

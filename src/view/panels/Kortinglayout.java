package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import controller.InstellingenOverviewController;
import javafx.scene.layout.VBox;
import model.artikel.Artikelgroep;
import model.korting.*;

import java.util.ArrayList;

public class Kortinglayout {
    //interactie met Pane
    // var
    private InstellingenOverviewController instellingenOverviewController;
    //layout
    private VBox vb;
    private ComboBox<String> kortingstype;

    //var voor elke methode
    private TextField kortinghoeveelheid;
    private HBox kortingHb;


    public Kortinglayout(InstellingenOverviewController instellingenOverviewController, VBox vb, ComboBox<String> kortingstype){
        this.instellingenOverviewController=instellingenOverviewController;
        this.vb=vb;
        this.kortingstype=kortingstype;
    }



}

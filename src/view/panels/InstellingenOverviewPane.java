package view.panels;

import controller.InstellingenOverviewController;
import database.LoadSaveStrat.LoadSaveStrategies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.korting.Korting;
import model.korting.Kortingsmogelijkheden;

import java.util.ArrayList;

public class InstellingenOverviewPane extends GridPane {
    private InstellingenOverviewController instellingenOverviewController;

    public InstellingenOverviewPane(InstellingenOverviewController instellingenOverviewController){
        // dit stelt voor de meegegeven controller deze view in
        this.instellingenOverviewController=instellingenOverviewController;
        this.instellingenOverviewController.setInstellingenOverviewPane(this);

        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer box
        VBox vb= new VBox();
        vb.setSpacing(10);
        this.getChildren().add(vb);

        //creeer Hbox
        HBox laadHb= new HBox();
        laadHb.setSpacing(10);

        //creeer titel
        Label tabelDb = new Label("Hoe tabel inladen:");

        //creeer Strings arraylist van laadmogelijkheden
        ArrayList<String> laad = new ArrayList<>();
        for(LoadSaveStrategies s: LoadSaveStrategies.values()){
            laad.add(s.toString());
        }

        //in combobox
        ObservableList<String> laadOptions =
                FXCollections.observableArrayList(
                        laad
                );
        ComboBox<String> laadtype = new ComboBox<>(laadOptions);
        laadtype.setValue(LoadSaveStrategies.EXCEL.toString());

        //creeer knop
        Button laadknop = new Button("Zet laadoptie");

        //plaats combobox en knop in Hbox
        laadHb.getChildren().addAll(laadtype,laadknop);

        //voeg titel en Hbox toe
        vb.getChildren().addAll(tabelDb,laadHb);

        //setLaadoptie
        laadknop.setOnAction(actief ->  instellingenOverviewController.setLaadoptie(LoadSaveStrategies.valueOf(laadtype.getValue()).getClassname()));

        // KASTICKET
        Label kasticket = new Label("Eigenschappen Kasticket");
        CheckBox customHeader = new CheckBox("Custom header");
        customHeader.setOnAction(doe -> {
            instellingenOverviewController.setHeader(customHeader.isSelected());
        });

        TextField customHeaderText = new TextField("Custom header text");
        Button customHeaderTextButton = new Button("Stel in");
        customHeaderTextButton.setOnAction(lambda ->{

        });
        customHeaderText.setDisable(!customHeader.isSelected());

        CheckBox customFooter = new CheckBox("Custom Footer");
        customHeader.setOnAction(doe -> {
            instellingenOverviewController.setFooter(customFooter.isSelected());
        });



        vb.getChildren().addAll(kasticket, customHeader, customHeaderText);



        //huidige opties
        StringBuilder huidige = new StringBuilder("Huidige korting");
        if(instellingenOverviewController.getKortingen().size()>1) huidige.append("en:\n");
        else if(instellingenOverviewController.getKortingen().size()>0) huidige.append(":\n");
        else huidige.append(": geen");

        for(Korting korting: instellingenOverviewController.getKortingen()){
            huidige.append(korting.toString());
            huidige.append("\n");
        }

        Label huidigeKorting = new Label(String.valueOf(huidige));
        vb.getChildren().add(huidigeKorting);

        //creeer titel
        Label korting = new Label("Korting:");

        //creeer Strings arraylist van kortingsmogelijkheden
        ArrayList<String> list = new ArrayList<>();
        for(Kortingsmogelijkheden k: Kortingsmogelijkheden.values()){
            list.add(k.toString());
        }

        //in combobox
        ObservableList<String> kortingOptions =
                FXCollections.observableArrayList(
                     list
                );
        ComboBox<String> kortingstype = new ComboBox<>(kortingOptions);

        vb.getChildren().addAll(korting,kortingstype);

        //pas aan aan gekozen optie
        Kortinglayout layout = new Kortinglayout(instellingenOverviewController,vb,kortingstype);

        kortingstype.setOnAction(actie -> {
            layout.kies(kortingstype.getValue());
            kortingstype.setDisable(true);
        });




    }
}

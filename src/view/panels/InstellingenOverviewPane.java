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
import model.kasticket.TicketFacade;
import model.korting.Korting;
import model.korting.Kortingsmogelijkheden;

import java.util.ArrayList;

public class InstellingenOverviewPane extends GridPane {
    private InstellingenOverviewController instellingenOverviewController;

    private VBox vb;
    private Button laadknop;
    private ComboBox<String> laadtype;

    private ComboBox<String> kortingstype;

    public InstellingenOverviewPane(InstellingenOverviewController instellingenOverviewController){
        // dit stelt voor de meegegeven controller deze view in
        this.instellingenOverviewController=instellingenOverviewController;
        this.instellingenOverviewController.setInstellingenOverviewPane(this);

        elementen();

        //setLaadoptie
        laadknop.setOnAction(actief ->  instellingenOverviewController.setLaadoptie(LoadSaveStrategies.valueOf(laadtype.getValue()).getClassname()));

        // --- KASTICKET ---
        VBox ticket = new VBox();
        ticket.setPadding(new Insets(0, 0, 0, 350));
        ticket.setSpacing(10);
        this.getChildren().add(ticket);

        Label kasticket = new Label("Eigenschappen Kasticket");

        // header
        CheckBox customHeader = new CheckBox("Custom header");
        customHeader.setSelected(instellingenOverviewController.getCustomHeader());
        customHeader.setOnAction(doe -> instellingenOverviewController.setHeader(customHeader.isSelected()));
        TextField customHeaderText = new TextField(instellingenOverviewController.getCustomHeaderText());
        Button customHeaderTextButton = new Button("Stel in");
        customHeaderTextButton.setOnAction(lambda -> instellingenOverviewController.setCustomHeader(customHeaderText.getText()));

        ticket.getChildren().addAll(kasticket, customHeader, customHeaderText, customHeaderTextButton);

        // footer
        CheckBox customFooter = new CheckBox("Custom Footer");
        customFooter.setSelected(instellingenOverviewController.getCustomFooter());
        customFooter.setOnAction(doe -> instellingenOverviewController.setFooter(customFooter.isSelected()));
        TextField customFooterText = new TextField(instellingenOverviewController.getCustomFooterText());
        Button customFooterTextButton = new Button("Stel in");
        customFooterTextButton.setOnAction(lambda -> instellingenOverviewController.setCustomFooter(customFooterText.getText()));

        ticket.getChildren().addAll(customFooter,customFooterText, customFooterTextButton);

        //btw footer
        CheckBox btwFooter = new CheckBox("Btw Footer");
        btwFooter.setSelected(instellingenOverviewController.getBtwFooter());
        btwFooter.setOnAction(doe -> instellingenOverviewController.setBtwFooter(btwFooter.isSelected()));

        //datum header
        CheckBox datumHeader = new CheckBox("Datum header");
        datumHeader.setSelected(instellingenOverviewController.getDatumHeader());
        datumHeader.setOnAction(doe -> instellingenOverviewController.setDatumHeader(datumHeader.isSelected()));

        //korting footer
        CheckBox kortingFooter = new CheckBox("Korting Footer");
        kortingFooter.setSelected(instellingenOverviewController.getKortingFooter());
        kortingFooter.setOnAction(doe -> instellingenOverviewController.setKortingFooter(kortingFooter.isSelected()));

        ticket.getChildren().addAll(btwFooter, datumHeader, kortingFooter);

        
        //pas aan aan gekozen optie
        Kortinglayout layout = new Kortinglayout(instellingenOverviewController,vb,kortingstype);

        kortingstype.setOnAction(actie -> {
            layout.kies(kortingstype.getValue());
            kortingstype.setDisable(true);
        });
    }

    private void elementen(){
        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer hoofdbox
        vb= new VBox();
        vb.setSpacing(10);
        this.getChildren().add(vb);

        // --- Laadoptie ---
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
        laadtype = new ComboBox<>(laadOptions);
        laadtype.setValue(LoadSaveStrategies.EXCEL.toString());

        //creeer knop
        laadknop = new Button("Zet laadoptie");

        //plaats combobox en knop in Hbox
        laadHb.getChildren().addAll(laadtype,laadknop);

        //voeg titel en Hbox toe
        vb.getChildren().addAll(tabelDb,laadHb);


        // --- kortingen ---
        //huidige kortingen
        Label huidigeKorting = new Label(huidigekortingText());
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
        kortingstype = new ComboBox<>(kortingOptions);

        //voeg titel en combobox toe
        vb.getChildren().addAll(korting,kortingstype);
    }

    private String huidigekortingText(){
        //huidige opties
        StringBuilder huidige = new StringBuilder("Huidige korting");
        if(instellingenOverviewController.getKortingen().size()>1) huidige.append("en:\n");
        else if(instellingenOverviewController.getKortingen().size()>0) huidige.append(":\n");
        else huidige.append(": geen");

        for(Korting korting: instellingenOverviewController.getKortingen()){
            huidige.append(korting.toString());
            huidige.append("\n");
        }
        return String.valueOf(huidige);
    }
}

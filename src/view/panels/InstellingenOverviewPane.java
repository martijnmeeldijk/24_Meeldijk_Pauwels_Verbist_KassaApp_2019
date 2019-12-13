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
import model.artikel.Artikelgroep;
import model.kasticket.TicketFacade;
import model.korting.*;

import java.util.ArrayList;

public class InstellingenOverviewPane extends GridPane {
    private InstellingenOverviewController instellingenOverviewController;

    private HBox hb;
    private VBox vb;
    private Button laadknop;
    private ComboBox<String> laadtype;

    private CheckBox customHeader;
    private TextField customHeaderText;
    private Button customHeaderTextButton;
    private CheckBox customFooter;
    private TextField customFooterText;
    private Button customFooterTextButton;
    private CheckBox btwFooter;
    private CheckBox datumHeader;
    private CheckBox kortingFooter;

    private ComboBox<String> kortingstype;
    private TextField kortinghoeveelheid;
    private HBox kortingHb;

    public InstellingenOverviewPane(InstellingenOverviewController instellingenOverviewController){
        // dit stelt voor de meegegeven controller deze view in
        this.instellingenOverviewController=instellingenOverviewController;
        this.instellingenOverviewController.setInstellingenOverviewPane(this);

        elementen();

        // --- laadoptie ---
        //kies laadoptie
        laadknop.setOnAction(actief ->  instellingenOverviewController.setLaadoptie(LoadSaveStrategies.valueOf(laadtype.getValue()).getClassname()));

        // --- kasticket ---
        customHeader.setOnAction(doe -> instellingenOverviewController.setHeader(customHeader.isSelected()));
        customHeaderTextButton.setOnAction(lambda -> instellingenOverviewController.setCustomHeader(customHeaderText.getText()));

        customFooter.setOnAction(doe -> instellingenOverviewController.setFooter(customFooter.isSelected()));
        customFooterTextButton.setOnAction(lambda -> instellingenOverviewController.setCustomFooter(customFooterText.getText()));

        btwFooter.setOnAction(doe -> instellingenOverviewController.setBtwFooter(btwFooter.isSelected()));

        datumHeader.setOnAction(doe -> instellingenOverviewController.setDatumHeader(datumHeader.isSelected()));

        kortingFooter.setOnAction(doe -> instellingenOverviewController.setKortingFooter(kortingFooter.isSelected()));


        // --- korting ---
        //pas aan aan gekozen optie
        kortingstype.setOnAction(actie -> {
            kiesKorting(kortingstype.getValue());
            kortingstype.setDisable(true);
        });
    }

    private void elementen(){
        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer hoofdbox
        hb= new HBox();
        hb.setSpacing(20);
        this.getChildren().add(hb);

        vb= new VBox();
        vb.setSpacing(10);
        hb.getChildren().add(vb);

        laadelementen();
        kasticketelementen();
        kortingelementen();
    }
    private void laadelementen(){
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
    }
    private void kasticketelementen(){
        // --- kasticket ---
        VBox ticket = new VBox();
        ticket.setSpacing(10);
        this.getChildren().add(ticket);

        Label kasticket = new Label("Eigenschappen Kasticket");
        ticket.getChildren().add(kasticket);

        // header
        customHeader = new CheckBox("Custom header");
        customHeader.setSelected(instellingenOverviewController.getCustomHeader());

        customHeaderText = new TextField(instellingenOverviewController.getCustomHeaderText());
        customHeaderTextButton = new Button("Stel in");

        ticket.getChildren().addAll(customHeader, customHeaderText, customHeaderTextButton);

        // footer
        customFooter = new CheckBox("Custom Footer");
        customFooter.setSelected(instellingenOverviewController.getCustomFooter());

        customFooterText = new TextField(instellingenOverviewController.getCustomFooterText());
        customFooterTextButton = new Button("Stel in");

        ticket.getChildren().addAll(customFooter,customFooterText, customFooterTextButton);

        //btw footer
        btwFooter = new CheckBox("Btw Footer");
        btwFooter.setSelected(instellingenOverviewController.getBtwFooter());

        //datum header
        datumHeader = new CheckBox("Datum header");
        datumHeader.setSelected(instellingenOverviewController.getDatumHeader());

        //korting footer
        kortingFooter = new CheckBox("Korting Footer");
        kortingFooter.setSelected(instellingenOverviewController.getKortingFooter());

        ticket.getChildren().addAll(btwFooter, datumHeader, kortingFooter);
        hb.getChildren().add(ticket);
    }
    private void kortingelementen(){
        // --- kortingen ---
        //huidige kortingen
        Label huidigeKorting = new Label(huidigekortingText());
        vb.getChildren().add(huidigeKorting);

        //creeer titel
        Label korting = new Label("Nieuwe kortingen toevoegen:");

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

    private void kiesKorting(String string){
        switch (string){
            case "Groep": Groep();
                break;
            case "Drempel": Drempel();
                break;
            case "Duurst": Duurst();
                break;
        }
    }
    private void Duurst(){
        kortingHoeveelheid("25");

        //creeer knop
        Button kortingknop = new Button("Zet korting");
        vb.getChildren().add(kortingknop);

        //setKorting
        kortingknop.setOnAction(actief -> {
            Duurstkorting korting = (Duurstkorting) KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Duurst);
            korting.setKortingspercentage(Integer.parseInt(kortinghoeveelheid.getText()));
            instellingenOverviewController.addKorting(korting);

            algemeneWeiziging();
            vb.getChildren().remove(kortingknop);
        });
    }
    private void Drempel(){
        kortingHoeveelheid("5");
        //creeer Hbox
        HBox drempelHb= new HBox();
        drempelHb.setSpacing(10);

        //creeer drempelInput
        Label drempelInputText = new Label("drempelwaarde");
        TextField drempelInputhoeveelheid = new TextField("100");

        //plaats drempelInput in Hbox
        drempelHb.getChildren().addAll(drempelInputText,drempelInputhoeveelheid);

        //creeer knop
        Button kortingknop = new Button("Zet korting");

        vb.getChildren().addAll(drempelHb,kortingknop);

        //setKorting
        kortingknop.setOnAction(actief ->
        {
            Drempelkorting korting = (Drempelkorting) KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Drempel);
            korting.setKortingspercentage(Integer.parseInt(kortinghoeveelheid.getText()));
            korting.setDrempel(Double.parseDouble(drempelInputhoeveelheid.getText()));
            instellingenOverviewController.addKorting(korting);

            algemeneWeiziging();
            vb.getChildren().removeAll(drempelHb,kortingknop);
        });
    }
    private void Groep(){
        kortingHoeveelheid("5");
        //creeer Hbox
        HBox groepHb= new HBox();
        groepHb.setSpacing(10);

        //creeer groepInput
        Label groepInputText = new Label("groep:");

        //creeer Strings arraylist van kortingsmogelijkheden
        ArrayList<String> list = new ArrayList<>();
        for(Artikelgroep a: Artikelgroep.values()){
            list.add(a.toString());
        }

        //in combobox
        ObservableList<String> groepOptions =
                FXCollections.observableArrayList(
                        list
                );
        ComboBox<String> groepstype = new ComboBox<>(groepOptions);
        groepstype.setValue("gr1");

        //plaats groepInput in Hbox
        groepHb.getChildren().addAll(groepInputText,groepstype);

        //creeer knop
        Button kortingknop = new Button("Zet korting");

        vb.getChildren().addAll(groepHb,kortingknop);

        //setKorting
        kortingknop.setOnAction(actief ->
        {
            Groepkorting korting = (Groepkorting)  KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Groep);
            korting.setKortingspercentage(Integer.parseInt(kortinghoeveelheid.getText()));
            korting.setGroep(Artikelgroep.valueOf(groepstype.getValue()));
            instellingenOverviewController.addKorting(korting);

            algemeneWeiziging();
            vb.getChildren().removeAll(groepHb,kortingknop);
        });
    }
    private void kortingHoeveelheid(String standaart){
        //creeer Hbox
        kortingHb= new HBox();
        kortingHb.setSpacing(10);

        //creeer kortingInput
        Label kortingInputText = new Label("korting:");
        kortinghoeveelheid = new TextField(standaart);

        //plaats kortingsInput in Hbox
        kortingHb.getChildren().addAll(kortingInputText,kortinghoeveelheid);
        vb.getChildren().add(kortingHb);
    }
    private void algemeneWeiziging(){
        vb.getChildren().remove(kortingHb);
        kortingstype.setValue("");
        kortingstype.setDisable(false);
    }
}

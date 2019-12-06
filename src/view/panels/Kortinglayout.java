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
import model.Artikelgroep;
import model.korting.*;

import java.util.ArrayList;

public class Kortinglayout {
    //interactie met Pane
    private InstellingenOverviewController instellingenOverviewController;
    private VBox vb;
    private ComboBox<String> kortingstype;
    private Label kortingen;

    //var voor elke methode
    private TextField kortinghoeveelheid;
    private HBox kortingHb;

    public Kortinglayout(InstellingenOverviewController instellingenOverviewController, VBox vb, ComboBox<String> kortingstype, Label kortingen){
        this.instellingenOverviewController=instellingenOverviewController;
        this.vb=vb;
        this.kortingstype=kortingstype;
        this.kortingen=kortingen;
    }

    public void kies(String string){
        switch (string){
            case "Nummer": Number();
            break;
            case "Groep": Groep();
                break;
            case "Drempel": Drempel();
                break;
            case "Duurst": Duurst();
                break;
        }
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
        kortingstype.setDisable(false);
        schrijfKorting();
    }

    private void schrijfKorting(){
        StringBuilder tekst = new StringBuilder();
        ArrayList<Korting> list = instellingenOverviewController.getKortingen();
        for(int i=0;i<list.size();i++){
            if(tekst.toString().equals("")) tekst = new StringBuilder(list.get(i).toString());
            else tekst.append("\n").append(list.get(i).toString());
        }
        kortingen.setText(tekst.toString());
    }

    private void Number(){
        kortingHoeveelheid("0");

        //creeer knop
        Button kortingknop = new Button("Zet korting");
        vb.getChildren().add(kortingknop);

        //setKorting
        kortingknop.setOnAction(actief -> {
            Nummerkorting korting = (Nummerkorting) KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Nummer);
            korting.setKorting(Integer.parseInt(kortinghoeveelheid.getText()));
            instellingenOverviewController.addKorting(korting);

            algemeneWeiziging();
            vb.getChildren().remove(kortingknop);
        });
    }

    private void Duurst(){
        kortingHoeveelheid("0");

        //creeer knop
        Button kortingknop = new Button("Zet korting");
        vb.getChildren().add(kortingknop);

        //setKorting
        kortingknop.setOnAction(actief -> {
            Duurstkorting korting = (Duurstkorting) KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Duurst);
            korting.setKorting(Integer.parseInt(kortinghoeveelheid.getText()));
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
            korting.setKorting(Integer.parseInt(kortinghoeveelheid.getText()));
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
            korting.setKorting(Integer.parseInt(kortinghoeveelheid.getText()));
            korting.setGroep(Artikelgroep.valueOf(groepstype.getValue()));
            instellingenOverviewController.addKorting(korting);

            algemeneWeiziging();
            vb.getChildren().removeAll(groepHb,kortingknop);
        });
    }

}

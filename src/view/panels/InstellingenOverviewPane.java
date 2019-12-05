package view.panels;

import controller.InstellingenOverviewController;
import database.LoadSaveStrategies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        //creeer titel
        Label korting = new Label("Eerste korting:");

        //creeer Hbox
        HBox kortingHb= new HBox();
        kortingHb.setSpacing(10);

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
        kortingstype.setValue(Kortingsmogelijkheden.Nummer.toString());

        //creeer kortingInput
        TextField kortingInput = new TextField();

        //creeer knop
        Button kortingknop = new Button("Zet korting");

        //plaats combobox, kortingsInput en knop in Hbox
        kortingHb.getChildren().addAll(kortingstype,kortingInput,kortingknop);

        //voeg titel en Hbox toe
        vb.getChildren().addAll(korting,kortingHb);

        //setKorting
        kortingknop.setOnAction(actief ->
        {
            if(kortingInput.getText().equals("")){
                instellingenOverviewController.setKorting(kortingstype.getValue());
            }else{
                instellingenOverviewController.setKorting(kortingstype.getValue(),Integer.parseInt(kortingInput.getText()));
            }
        });

    }
}

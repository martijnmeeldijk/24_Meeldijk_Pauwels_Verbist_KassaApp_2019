package view.panels;

import controller.InstellingenOverviewController;
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

        //creeer titel
        Label korting = new Label("Korting:");

        //creeer Hbox
        HBox hb= new HBox();
        hb.setSpacing(10);

        //creeer Strings arraylist van kortingsmogelijkheden
        ArrayList<String> list = new ArrayList<>();
        for(Kortingsmogelijkheden k: Kortingsmogelijkheden.values()){
            list.add(k.toString());
        }

        //in combobox
        ObservableList<String> options =
                FXCollections.observableArrayList(
                     list
                );
        ComboBox<String> kortingstype = new ComboBox<>(options);
        kortingstype.setValue(Kortingsmogelijkheden.Nummer.toString());

        //creeer kortingInput
        TextField kortingInput = new TextField("0");

        //creeer knop
        Button knop = new Button("Zet korting");

        //plaats combobox, kortingsInput en knop in Hbox
        hb.getChildren().addAll(kortingstype,kortingInput);

        //voeg titel en combobox toe
        vb.getChildren().addAll(korting,hb);

    }
}

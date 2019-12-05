package view.panels;

import controller.InstellingenOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class InstellingenOverviewPane extends GridPane {
    private InstellingenOverviewController instellingenOverviewController;

    public InstellingenOverviewPane(InstellingenOverviewController instellingenOverviewController){
        // dit stelt voor de meegegeven controller deze view in
        this.instellingenOverviewController=instellingenOverviewController;
        this.instellingenOverviewController.setInstellingenOverviewPane(this);

        VBox vb= new VBox();
        vb.setSpacing(10);

        //korting
        Label korting = new Label("Korting:");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        ComboBox<String> kortingstype = new ComboBox<>(options);
        vb.getChildren().addAll(korting,kortingstype);
    }
}

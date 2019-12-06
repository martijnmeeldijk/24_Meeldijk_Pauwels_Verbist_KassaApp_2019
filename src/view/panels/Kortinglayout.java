package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import controller.InstellingenOverviewController;
import javafx.scene.layout.VBox;

public class Kortinglayout {
    private InstellingenOverviewController instellingenOverviewController;
    private VBox vb;

    public Kortinglayout(InstellingenOverviewController instellingenOverviewController, VBox vb){
        this.instellingenOverviewController=instellingenOverviewController;
        this.vb=vb;
    }

    public void Number(){
        //creeer Hbox
        HBox kortingHb= new HBox();
        kortingHb.setSpacing(10);

        //creeer kortingInput
        TextField kortingInput = new TextField();

        //creeer knop
        Button kortingknop = new Button("Zet korting");

        //plaats combobox, kortingsInput en knop in Hbox
        kortingHb.getChildren().addAll(kortingInput,kortingknop);

        vb.getChildren().add(kortingHb);

        //setKorting
        kortingknop.setOnAction(actief ->
        {
            if(kortingInput.getText().equals("")){
                instellingenOverviewController.setKorting(model.korting.Kortingsmogelijkheden.valueOf("Nummer"));
            }else{
                instellingenOverviewController.setKorting(model.korting.Kortingsmogelijkheden.valueOf("Nummer"),Integer.parseInt(kortingInput.getText()));
            }
        });
    }

}

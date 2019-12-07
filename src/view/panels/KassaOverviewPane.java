package view.panels;

import controller.KassaViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Artikel;

import java.util.Optional;

public class KassaOverviewPane extends GridPane {
    //controller
    private KassaViewController kassaViewController;

    //tabel en inputveld
    private TableView<Artikel> table;
    private TextField inputCode;

    //prijs en korting
    private Label prijswaarde;
    private Label kortingwaarde;
    private Label betaalwaarde;

    //on hold
    private boolean onHold = false;
    private Button hold;

    //afsluiten
    private boolean betalen = false;
    private Button sluitAf;

    //anulleren
    private Button annuleer;

    //eerste Artikkel
    private boolean eerst = true;

    public KassaOverviewPane(KassaViewController kassaViewController) {
        /// dit stelt voor de meegegeven controller deze view in
        this.kassaViewController = kassaViewController;
        this.kassaViewController.setKassaView(this);

        //creeer elementen
        elementen();

        //registreer input code
        inputCode();

        //verwijder item uit tabel bij dubbelklik
        verwijder();

        //zet bestelling on hold of actief
        hold();

        //afsluiten
        afsluiten();

        //annulleren
        annuleren();

    }

    //creeer elementen
    private void elementen(){
        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer box
        VBox vb = new VBox();
        vb.setSpacing(10);
        this.getChildren().add(vb);

        //display inputveld code toevoegen
        Label code = new Label("Code:");
        inputCode = new TextField();

        vb.getChildren().addAll(code, inputCode);

        //tabel en titel toevoegen
        Label lblHeading = new Label("artikels");
        lblHeading.setFont(new Font("Arial", 20));

        tabel();

        vb.getChildren().addAll(lblHeading, table);

        // zet on hold-, betaal- en anulleer-knoppen en voeg ze toe
        HBox knoppenBox = new HBox();
        knoppenBox.setSpacing(20);
        hold = new Button("Zet on hold");
        hold.setDisable(true);
        sluitAf = new Button("Sluit Af");
        sluitAf.setDisable(true);
        annuleer = new Button("Annuleer");
        annuleer.setDisable(true);
        knoppenBox.getChildren().addAll(hold,sluitAf,annuleer);
        vb.getChildren().add(knoppenBox);

        //prijs, korting en totaal toevoegen
        prijswaarde = new Label("0.0");
        Label prijs = new Label("prijs: ");
        HBox prijsbox = new HBox();
        prijsbox.getChildren().addAll(prijs, prijswaarde);

        kortingwaarde = new Label("0.0");
        Label korting = new Label("korting: ");
        HBox kortingbox = new HBox();
        kortingbox.getChildren().addAll(korting, kortingwaarde);

        betaalwaarde = new Label("0.0");
        Label betaal = new Label("te betalen: ");
        HBox betaalbox = new HBox();
        betaalbox.getChildren().addAll(betaal, betaalwaarde);

        vb.getChildren().addAll(prijsbox,kortingbox,betaalbox);
    }

    //tabel
    private void inputCode () {
        //registreer input code
        inputCode.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    kassaViewController.addArtikkel(Integer.parseInt(inputCode.getText()));
                } catch (Exception e) {
                    displayErrorMessage("niet bestaande code");
                }
                inputCode.clear();
                if(!onHold) hold.setDisable(false);
                else hold.setDisable(true);
                sluitAf.setDisable(false);
                annuleer.setDisable(false);
            }
        });
    }
    private void tabel () {
        //creeer tabel
        table = TabelArtikels.create(kassaViewController.getArtikels());
    }
    private void verwijder () {
        //verwijder item uit tabel bij dubbelklik
        table.setRowFactory(tv -> {
            TableRow<Artikel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Verwijderen");
                    alert.setHeaderText("Artikel verwijderen");
                    alert.setContentText("Bent u zeker dat u dit artikel uit de bestelling wilt verwijderen?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK){
                        int codeInt = row.getItem().getCode();
                        kassaViewController.removeArtikkel(codeInt);
                    }
                }
            });
            return row;
        });
    }
    private void refresh () {
        table.refresh();
    }

    public void setArtikels(ObservableList<Artikel> list){
        table.setItems(list);
        refresh();
    }

    //prijzen tonen
    public void setPrijzen (String prijs,String korting,String betaal){
        this.prijswaarde.setText(prijs);
        this.kortingwaarde.setText(korting);
        this.betaalwaarde.setText(betaal);
    }

    //on hold
    private void hold(){
        hold.setOnAction(actief ->
        {
            if(onHold) actief();
            else onHold();
        });
    }
    private void actief(){
        onHold=false;
        hold.setText("zet on hold");

        kassaViewController.zetActief();
        table.setItems(kassaViewController.getArtikels());
        refresh();

        sluitAf.setDisable(false);
        annuleer.setDisable(false);
    }
    private void onHold(){
        onHold=true;
        hold.setText("zet actief");

        kassaViewController.zetOnHold();
        table.setItems(kassaViewController.getArtikels());
        refresh();

        sluitAf.setDisable(true);
        annuleer.setDisable(true);
    }

    //betalen
    private void afsluiten(){
        sluitAf.setOnAction(sluit -> {
            if(betalen){
                sluitAf.setText("Sluit af");
                betalen = false;

                sluitAf.setDisable(true);
                annuleer.setDisable(true);
                if(onHold) hold.setDisable(false);
                else hold.setDisable(true);

            }else{
                sluitAf.setText("Betalen");
                betalen = true;
            }
            kassaViewController.handelBestellingAf();
        });
    }

    //annuleren
    private void annuleren(){
        annuleer.setOnAction(an -> {
            kassaViewController.annuleer();
            table.setItems(kassaViewController.getArtikels());

            annuleer.setDisable(true);
            sluitAf.setDisable(true);
            if(onHold) hold.setDisable(false);
            else hold.setDisable(true);
        });
    }

    //error tonen
    public void displayErrorMessage (String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void onHoldVerlopen(){
        hold.setDisable(true);
        hold.setText("Zet on Hold");
        onHold = false;
    }
}
	
	


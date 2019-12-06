package view.panels;

import controller.KassaViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Artikel;

public class KassaOverviewPane extends GridPane {
    private KassaViewController kassaViewController;
    private TableView<Artikel> table;
    private Label prijswaarde;
    private Label kortingwaarde;
    private TextField inputCode;
    //public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();

    public KassaOverviewPane(KassaViewController kassaViewController) {
        /// dit stelt voor de meegegeven controller deze view in
        this.kassaViewController = kassaViewController;
        this.kassaViewController.setKassaView(this);

        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer box
        VBox vb = new VBox();
        vb.setSpacing(10);
        this.getChildren().add(vb);

        //creer display inputveld code, totaalprijs en kortingtotaalprijs
        Label code = new Label("Code:");
        inputCode = new TextField();

        prijswaarde = new Label("0.0");
        Label prijs = new Label("prijs: ");
        HBox prijsbox = new HBox();
        prijsbox.getChildren().addAll(prijs, prijswaarde);

        kortingwaarde = new Label("0.0");
        Label korting = new Label("korting: ");
        HBox kortingprijsbox = new HBox();
        kortingprijsbox.getChildren().addAll(korting, kortingwaarde);

        //voeg inputveld code en totaalprijs toe
        vb.getChildren().addAll(code, inputCode, prijsbox, kortingprijsbox);

        //creeer titel
        Label lblHeading = new Label("artikels");
        lblHeading.setFont(new Font("Arial", 20));

        //creeer tabel
        tabel();

        //voeg titel en tabel toe
        vb.getChildren().addAll(lblHeading, table);

        //registreer input code
        inputCode();

        //verwijder item uit tabel bij dubbelklik
        verwijder();

        // zet on hold
        Button zetOnHold = new Button("Zet on hold");
        zetOnHold.setOnAction(onHold -> {
            kassaViewController.zetOnHold();
            table.setItems(kassaViewController.getArtikels());
            refresh();
        });
        vb.getChildren().addAll(zetOnHold);

        // Zet on hold artikel terug actief
        Button zetActief = new Button("Zet Actief");
        zetActief.setOnAction(actief ->
        {
            kassaViewController.zetActief();
            table.setItems(kassaViewController.getArtikels());
            refresh();
        });
        vb.getChildren().add(zetActief);

        Button sluitAf = new Button("Sluit Af");
        sluitAf.setOnAction(sluit -> {
            sluitAf.setText("Betaal");
            kassaViewController.sluitAf();
        });
        vb.getChildren().add(sluitAf);

        Button annuleer = new Button("annuleer");
        annuleer.setOnAction(an -> {
            kassaViewController.annuleer();
            table.setItems(kassaViewController.getArtikels());
        });
        vb.getChildren().addAll(annuleer);
    }

        private void tabel () {
            //creeer tabel
            table = Tabel.create(kassaViewController.getArtikels());
        }

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
                }
            });
        }

        private void verwijder () {
            //verwijder item uit tabel bij dubbelklik
            table.setRowFactory(tv -> {
                TableRow<Artikel> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        int codeInt = row.getItem().getCode();
                        kassaViewController.removeArtikkel(codeInt);
                    }
                });
                return row;
            });
        }

        public void displayErrorMessage (String errorMessage){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information Alert");
            alert.setContentText(errorMessage);
            alert.show();
        }

        public void setOriginelePrijs (String prijs){
            this.prijswaarde.setText(prijs);
        }

        public void setKorting (String korting){
            this.kortingwaarde.setText(korting);
        }

        private void refresh () {
            table.refresh();
        }
    }
	
	


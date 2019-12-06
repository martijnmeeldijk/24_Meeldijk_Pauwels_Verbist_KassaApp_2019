package view.klantview;

import controller.KlantOverviewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Artikel;
import model.OmschrijvingComparable;
import controller.AantalList;

import java.util.Comparator;

public class KlantOverviewPane  extends GridPane {
    private KlantOverviewController klantOverviewController;
    private TableView<Artikel> table;
    private Label prijswaarde;
    public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();


    public KlantOverviewPane(KlantOverviewController klantOverviewController) {
        // dit stelt voor de meegegeven controller deze view in
        this.klantOverviewController=klantOverviewController;
        this.klantOverviewController.setKlantOverviewPane(this);

        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer box
        VBox vb= new VBox();
        vb.setSpacing(10);

        //creeer display totaalprijs
        prijswaarde=new Label("0.0");
        Label prijs = new Label("prijs: ");
        HBox prijsbox=new HBox();
        prijsbox.getChildren().addAll(prijs,prijswaarde);

        //voeg totaalprijs toe
        vb.getChildren().add(prijsbox);

        //creeer titel
        Label lblHeading = new Label("artikels");
        lblHeading.setFont(new Font("Arial", 20));

        //creeer tabel
        tabel();

        //voeg titel en tabel toe
        vb.getChildren().add(table);
        this.getChildren().addAll(lblHeading, vb);
    }

    private void tabel(){
        //creeer tabel
        table = new TableView<>();
        table.setItems(AantalList.getList(klantOverviewController.getWinkel()));

        //creeer kolommen
        TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
        colOmschrijving.setMinWidth(300);
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));

        TableColumn<Artikel, Integer> colAantal = new TableColumn<>("aantal");
        colAantal.setMinWidth(100);
        colAantal.setCellValueFactory(new PropertyValueFactory<>("aantal"));

        TableColumn<Artikel, Double> colVerkoopprijs = new TableColumn<>("verkoopprijs");
        colVerkoopprijs.setMinWidth(100);
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs"));

        //voeg kolommen toe aan tabel
        table.getColumns().addAll(colOmschrijving,colAantal, colVerkoopprijs);
    }

    public void setArtikels(ObservableList<Artikel> list){
        table.setItems(list);
        refresh();
    }

    public void setPrijs(String prijs) {
        this.prijswaarde.setText(prijs);
    }

    private void refresh(){
        table.refresh();
    }

    /*public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }

    public TableView<Artikel> getTable() {
        return table;
    }*/
}

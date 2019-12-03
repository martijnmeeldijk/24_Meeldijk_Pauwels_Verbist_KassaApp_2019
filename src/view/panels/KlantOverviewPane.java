package view.panels;

import controller.ArtikelOverviewController;
import controller.KlantOverviewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Comparator;

public class KlantOverviewPane  extends GridPane {
    private TableView<Artikel> table;
    private Label prijs;
    private Label prijswaarde;
    public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();


    public KlantOverviewPane(KlantOverviewController klantOverviewController) {
        // dit stelt voor de meegegeven controller deze view in
        prijswaarde=new Label("0.0");
        prijs=new Label("prijs: ");
        HBox prijsbox=new HBox();
        prijsbox.getChildren().addAll(prijs,prijswaarde);
        VBox vb= new VBox();
        vb.getChildren().add(prijsbox);

        klantOverviewController.setKlantOverviewPane(this);
        this.setPadding(new Insets(10, 10, 10, 10));
        Label lblHeading = new Label("artikels");
        lblHeading.setFont(new Font("Arial", 20));
        table = new TableView<Artikel>();

        table.setItems(klantOverviewController.getList());

        TableColumn<Artikel, String> colOmschrijving = new TableColumn<Artikel, String>("Omschrijving");
        colOmschrijving.setMinWidth(300);
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Omschrijving"));

        TableColumn<Artikel, Double> colVerkoopprijs = new TableColumn<Artikel, Double>("verkoopprijs");
        colVerkoopprijs.setMinWidth(100);
        colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("verkoopprijs"));

        TableColumn<Artikel, Integer> colAantal = new TableColumn<Artikel, Integer>("aantal");
        colAantal.setMinWidth(100);
        colAantal.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("aantal"));


        table.getColumns().addAll(colOmschrijving,colAantal, colVerkoopprijs);
        vb.getChildren().add(table);
        this.getChildren().addAll(lblHeading, vb);
    }

    public void displayErrorMessage(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information Alert");
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void setArtikels(ObservableList<Artikel>list){
        table.setItems(list);
        refresh();
    }

    public TableView<Artikel> getTable() {
        return table;
    }
    public void setPrijs(String prijs) {
        this.prijswaarde.setText(prijs);
    }


    public void refresh(){
        table.refresh();
    }
}

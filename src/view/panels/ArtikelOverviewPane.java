package view.panels;
import controller.ArtikelOverviewController;
import database.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Artikel;
import model.OmschrijvingComparable;

import java.util.Comparator;
import java.util.HashMap;

public class ArtikelOverviewPane extends GridPane {
	private TableView<Artikel> table;
	private ArtikelOverviewController artikelOverviewController;
	public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();


	public ArtikelOverviewPane(ArtikelOverviewController artikelOverviewController) {
		// dit stelt voor de meegegeven controller deze view in
		this.artikelOverviewController=artikelOverviewController;
		artikelOverviewController.setArtikelOverviewPane(this);

		this.setPadding(new Insets(10, 10, 10, 10));
		Label lblHeading = new Label("artikels");
		lblHeading.setFont(new Font("Arial", 20));
		table = new TableView<Artikel>();

		table.setItems(artikelOverviewController.getList());
		TableColumn<Artikel, Integer> colcode = new TableColumn<Artikel, Integer>("code");
		colcode.setMinWidth(100);
		colcode.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("code"));

		TableColumn<Artikel, String> colArtikelgroep = new TableColumn<Artikel, String>("artikelgroep");
		colArtikelgroep.setMinWidth(100);
		colArtikelgroep.setCellValueFactory(new PropertyValueFactory<Artikel, String>("artikelgroep"));

		TableColumn<Artikel, String> colOmschrijving = new TableColumn<Artikel, String>("Omschrijving");
		colOmschrijving.setMinWidth(300);
		colOmschrijving.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Omschrijving"));

		TableColumn<Artikel, Double> colVerkoopprijs = new TableColumn<Artikel, Double>("verkoopprijs");
		colVerkoopprijs.setMinWidth(100);
		colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("verkoopprijs"));

		TableColumn<Artikel, Integer> colVoorraad = new TableColumn<Artikel, Integer>("voorraad");
		colVoorraad.setMinWidth(100);
		colVoorraad.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("voorraad"));
		table.getColumns().addAll(colcode,colOmschrijving,colArtikelgroep, colVerkoopprijs,colVoorraad);
		this.getChildren().addAll(lblHeading, table);
	}

	public void displayErrorMessage(String errorMessage){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(errorMessage);
		alert.show();
	}

	public void refresh(){
		table.refresh();
	}
}
	
	


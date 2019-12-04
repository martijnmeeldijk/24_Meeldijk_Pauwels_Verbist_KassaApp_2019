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
	//public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();


	public ArtikelOverviewPane(ArtikelOverviewController artikelOverviewController) {
		// dit stelt voor de meegegeven controller deze view in
		this.artikelOverviewController=artikelOverviewController;
		this.artikelOverviewController.setArtikelOverviewPane(this);

		//layout
		this.setPadding(new Insets(10, 10, 10, 10));

		//creeer titel
		Label lblHeading = new Label("artikels");
		lblHeading.setFont(new Font("Arial", 20));

		//creeer tabel
		tabel();

		//voeg titel en tabel toe
		this.getChildren().addAll(lblHeading, table);
	}

	private void tabel(){
		//creeer tabel
		table = Tabel.create(artikelOverviewController.getList());
	}

	/*public void displayErrorMessage(String errorMessage){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(errorMessage);
		alert.show();
	}

	public void refresh(){
		table.refresh();
	}*/
}
	
	


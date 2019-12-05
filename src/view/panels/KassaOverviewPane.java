package view.panels;

import controller.ArtikelOverviewController;
import controller.KassaViewController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Artikel;
import model.OmschrijvingComparable;

import java.util.Comparator;

public class KassaOverviewPane extends GridPane {
	private KassaViewController kassaViewController;
	private TableView<Artikel> table;
	private Label prijs;
	private Label prijswaarde;
	private TextField textField;
	//public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();

	public KassaOverviewPane(KassaViewController kassaViewController) {
		/// dit stelt voor de meegegeven controller deze view in
		this.kassaViewController=kassaViewController;
		this.kassaViewController.setKassaView(this);

		//layout
		this.setPadding(new Insets(10, 10, 10, 10));

		//creer display inputveld code en totaalprijs
		Label label1 = new Label("Code:");
		textField = new TextField ();
		prijswaarde=new Label("0.0");
		prijs=new Label("prijs: ");
		HBox prijsbox=new HBox();
		prijsbox.getChildren().addAll(prijs,prijswaarde);

		//voeg inputveld code en totaalprijs toe
		VBox vb = new VBox();
		vb.getChildren().addAll(label1,textField,prijsbox);
		vb.setSpacing(10);

		//creeer titel
		Label lblHeading = new Label("artikels");
		lblHeading.setFont(new Font("Arial", 20));

		//creeer tabel
		tabel();

		//voeg titel en tabel toe
		vb.getChildren().addAll(lblHeading,table);
		this.getChildren().addAll(vb);

		//registreer input
		textField.setOnKeyPressed(ke -> {
			if (ke.getCode().equals(KeyCode.ENTER))
			{
				try{kassaViewController.addArtikkel(Integer.parseInt(textField.getText()));}
				catch (Exception e){
					displayErrorMessage("niet bestaande code");
				}
				textField.clear();
			}
		});

		//verwijder item uit tabel bij dubbelklik
		table.setRowFactory( tv -> {
			TableRow<Artikel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					int code = row.getItem().getCode();
					kassaViewController.removeArtikkel(code);
				}
			});
			return row ;
		});
	}

	private void tabel(){
		//creeer tabel
		table = new TableView<>();
		table.setItems(kassaViewController.getArtikels());

		//creeer kolommen
		TableColumn<Artikel, Integer> colcode = new TableColumn<>("code");
		colcode.setMinWidth(100);
		colcode.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn<Artikel, String> colOmschrijving = new TableColumn<>("Omschrijving");
		colOmschrijving.setMinWidth(300);
		colOmschrijving.setCellValueFactory(new PropertyValueFactory<>("Omschrijving"));

		TableColumn<Artikel, String> colArtikelgroep = new TableColumn<>("artikelgroep");
		colArtikelgroep.setMinWidth(100);
		colArtikelgroep.setCellValueFactory(new PropertyValueFactory<>("artikelgroep"));

		TableColumn<Artikel, Double> colVerkoopprijs = new TableColumn<>("verkoopprijs");
		colVerkoopprijs.setMinWidth(100);
		colVerkoopprijs.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs"));

		TableColumn<Artikel, Integer> colVoorraad = new TableColumn<>("voorraad");
		colVoorraad.setMinWidth(100);
		colVoorraad.setCellValueFactory(new PropertyValueFactory<>("voorraad"));

		//voeg kolommen toe aan tabel
		table.getColumns().addAll(colcode,colOmschrijving,colArtikelgroep, colVerkoopprijs,colVoorraad);
	}

	public void displayErrorMessage(String errorMessage){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(errorMessage);
		alert.show();
	}

	public void setPrijs(String prijs) {
		this.prijswaarde.setText(prijs);
	}
}
	
	


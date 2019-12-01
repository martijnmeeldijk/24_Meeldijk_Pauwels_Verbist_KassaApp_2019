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
	private TableView<Artikel> table;
	private ObservableList<Artikel>list;
	public static Comparator<Artikel> omschrijvingcomperator = new OmschrijvingComparable();

	public KassaOverviewPane(KassaViewController kassaViewController) {
		Label label1 = new Label("Code:");
		TextField textField = new TextField ();
		VBox vb = new VBox();
		vb.getChildren().addAll(label1, textField);
		vb.setSpacing(10);

		textField.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent ke)
			{
				if (ke.getCode().equals(KeyCode.ENTER))
				{
					System.out.println(textField.getText());
					System.out.println("test");
				}
			}
		});





		/// dit stelt voor de meegegeven controller deze view in
		kassaViewController.setKassaView(this);
		this.setPadding(new Insets(10, 10, 10, 10));
		Label lblHeading = new Label("artikels");
		lblHeading.setFont(new Font("Arial", 20));
		table = new TableView<Artikel>();

		table.setItems(list);
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
		vb.getChildren().add(table);
		this.getChildren().addAll(vb);
	}

	public void displayErrorMessage(String errorMessage){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");
		alert.setContentText(errorMessage);
		alert.show();
	}


}
	
	


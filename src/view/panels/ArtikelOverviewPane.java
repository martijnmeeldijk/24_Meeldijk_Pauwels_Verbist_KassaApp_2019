package view.panels;
import controller.ArtikelOverviewController;
import database.ArtikelDbStrategy;
import database.ArtikelTextLoadSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Artikel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtikelOverviewPane extends GridPane {
	private TableView<Artikel> table;
	ArtikelDbStrategy artikelTextLoadSave;


	public ArtikelOverviewPane(ArtikelDbStrategy artikelDbStrategy, ArtikelOverviewController artikelOverviewController) {
		// dit stelt voor de meegegeven controller deze view in
		artikelOverviewController.setArtikelOverviewPane(this);
		
		this.artikelTextLoadSave = artikelDbStrategy;
		this.setPadding(new Insets(10, 10, 10, 10));
		Label lblHeading = new Label("artikels");
		lblHeading.setFont(new Font("Arial", 20));
		table = new TableView<Artikel>();
		ObservableList<Artikel>artikels= FXCollections.observableArrayList();
		HashMap<Integer,Artikel>hashartikels=artikelDbStrategy.load();
		for(int key:hashartikels.keySet()){
			artikels.add(hashartikels.get(key));
		}

		table.setItems(artikels);
		table.setRowFactory( tv -> {
			TableRow<Artikel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					Artikel artikel = row.getItem();
					String movieInfo= artikel.getOmschrijving()+" \nRecent price is "+ artikel.getVerkoopprijs()+" Euro: ";
					//detail view aanmaken hier
					//new MovieDetailView(MovieOverview.this,movieInfo,artikel);
				}
			});
			return row;
		});
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

	/*
	class AddDummyFilmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			movieCompany.addDummyMovie();
			TableViewSelectionModel <Movie> tsm = table.getSelectionModel();
			tsm.select(movieCompany.getAantalMovies());
		}
	}

	 */
/*
	class UpdatePriceHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			TableViewSelectionModel <Movie> tsm = table.getSelectionModel();
			Movie movie = tsm.getSelectedItem();
			String movieInfo= movie.getTitle()+" \nRecent price is "+ movie.getPrice()+" Euro: ";
			new MovieDetailView(MovieOverview.this,movieInfo,movie);
		}
	}

 */
}
	
	


package view.panels;

import database.ArtikelTextLoadSave;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.io.FileNotFoundException;


public class ArtikelOverviewPane extends GridPane {
	private TableView<Artikel> table;
	ArtikelTextLoadSave artikelTextLoadSave = new ArtikelTextLoadSave();
	
	public ArtikelOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		try {
			table.setItems((ObservableList<Artikel>) artikelTextLoadSave.load());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.add(new Label("Artikelen:"), 0, 0, 1, 1);
		this.add(table,0,1,1,1);
		
	}
	
	

}

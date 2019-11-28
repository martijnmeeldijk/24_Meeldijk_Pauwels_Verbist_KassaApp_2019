package view.panels;

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


public class ArtikelOverviewPane extends GridPane {
	private TableView<Artikel> table;
	
	
	public ArtikelOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        //table.set
		this.add(new Label("Artikelen:"), 0, 0, 1, 1);
		this.add(table,0,1,1,1);
		
	}
	
	

}

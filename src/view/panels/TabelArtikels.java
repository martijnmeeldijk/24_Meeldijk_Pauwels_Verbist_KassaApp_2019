package view.panels;

import controller.Observer;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artikel;

import java.util.List;

public class TabelArtikels {
    public static TableView<Artikel> create(ObservableList<Artikel> list){
        //creeer tabel
        TableView<Artikel> table = new TableView<>();
        table.setItems(list);

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
        return table;
    }
}

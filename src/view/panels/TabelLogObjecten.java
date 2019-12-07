package view.panels;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artikel;
import model.LogObject;

import java.util.Date;

public class TabelLogObjecten {
    public static TableView<LogObject> create(ObservableList<LogObject> list){
        //creeer tabel
        TableView<LogObject> table = new TableView<>();
        table.setItems(list);

        //creeer kolommen
        TableColumn<LogObject, Date> coldateTime = new TableColumn<>("dateTime");
        coldateTime.setMinWidth(100);
        coldateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
/*
        TableColumn<LogObject, Double> coltotaal = new TableColumn<>("totaal");
        coltotaal.setMinWidth(100);
        coltotaal.setCellValueFactory(new PropertyValueFactory<>("totaal"));

        TableColumn<LogObject, Double> colkorting = new TableColumn<>("korting");
        colkorting.setMinWidth(100);
        colkorting.setCellValueFactory(new PropertyValueFactory<>("korting"));

        TableColumn<LogObject, Double> coltebetalen = new TableColumn<>("tebetalen");
        coltebetalen.setMinWidth(100);
        coltebetalen.setCellValueFactory(new PropertyValueFactory<>("tebetalen"));

 */



        //voeg kolommen toe aan tabel
        //table.getColumns().addAll(coldateTime,coltotaal,colkorting,coltebetalen);
        table.getColumns().addAll(coldateTime);
        return table;
    }
}

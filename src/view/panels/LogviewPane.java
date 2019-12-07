package view.panels;

import controller.LogViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Artikel;
import model.LogObject;

public class LogviewPane extends GridPane {
    private LogViewController logViewController;
    private TableView<LogObject> table;



    public LogviewPane(LogViewController logViewController) {
        // dit stelt voor de meegegeven controller deze view in
        this.logViewController=logViewController;
        this.logViewController.setLogviewPane(this);

        //layout
        this.setPadding(new Insets(10, 10, 10, 10));

        //creeer titel
        Label lblHeading = new Label("logs");
        lblHeading.setFont(new Font("Arial", 20));

        //creeer tabel
        tabel();

        //voeg titel en tabel toe
        this.getChildren().addAll(lblHeading, table);
    }

    private void tabel(){
        //creeer tabel
        table = TabelLogObjecten.create(logViewController.getList());
    }
    private void refresh () {
        table.refresh();
        System.out.println("items in tabel : "+ table.getItems());
    }

    public void setLogList(ObservableList<LogObject> list){
        table.setItems(list);
        refresh();
    }
}

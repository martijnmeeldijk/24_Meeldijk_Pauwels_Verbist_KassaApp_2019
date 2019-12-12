package view.panels;

import controller.LogViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.artikel.LogObject;

public class LogviewPane extends GridPane {
    private LogViewController logViewController;
    private TableView<LogObject> table;
    private Label logs;




    public LogviewPane(LogViewController logViewController) {
        logs =new Label("");
        VBox vb= new VBox();
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
        //vb.getChildren().addAll(logs,lblHeading,table);
        vb.getChildren().addAll(lblHeading,logs);
        this.getChildren().addAll(vb);
    }

    private void tabel(){
        //creeer tabel
        table = TabelLogObjecten.create(logViewController.getList());
    }
    private void refresh () {
        table.refresh();
    }

    public void setTextlog(String string){
        logs.setText(string);
    }

    public void setLogList(ObservableList<LogObject> list){
        table.setItems(list);
        refresh();
    }
}

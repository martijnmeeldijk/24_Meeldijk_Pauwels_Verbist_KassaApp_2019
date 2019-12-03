package controller;

import database.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.Artikel;
import view.KassaView;
import view.panels.KassaOverviewPane;

import java.awt.*;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    DataInMemory dataInMemory;

    public KassaViewController() {
        dataInMemory= new DataInMemory();
    }
    public void berekenPrice(){
        double totaal=0.0;
        for(Artikel artikel:kassaOverviewPane.getList()){
            totaal+=artikel.getVerkoopprijs();
        }
        kassaOverviewPane.setPrijs(String.valueOf(totaal));
    }



    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void verwerkInput(int code){
        ObservableList<Artikel>tijdelijk=kassaOverviewPane.getList();
        if(tijdelijk==null){
            tijdelijk= FXCollections.observableArrayList();
        }
        if(dataInMemory.getArtikel(code)==null){
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
        else {
            tijdelijk.add(dataInMemory.getArtikel(code));
            kassaOverviewPane.setList(tijdelijk);
            System.out.println(tijdelijk);
        }
        berekenPrice();
    }

    @Override
    public void update() {
    }
}

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Bestelling;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    private Bestelling bestelling;

    public KassaViewController(Bestelling bestelling) {
        this.bestelling=bestelling;
    }

    private void price(){
        double totaal=0.0;
        for(Artikel artikel:bestelling.getArtikels()){
            totaal+=artikel.getVerkoopprijs();
        }
        kassaOverviewPane.setPrijs(String.valueOf(totaal));
    }

    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void addArtikkel(int code){
        if(bestelling.itemBestaat(code)){
            bestelling.addArtikel(code);
            price();
        }
        else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code){
        if(bestelling.itemBestaat(code)){
            bestelling.removeArtikel(code);
            price();
        }
        else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    /*public Bestelling getBestelling() {
        return bestelling;
    }*/

    public ObservableList<Artikel> getArtikels(){
        return bestelling.getArtikels();
    }

    @Override
    public void update() {
    }
}

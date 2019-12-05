package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.KlantOverviewPane;
import view.panels.AantalList;

public class KlantOverviewController implements Observer {
    public Winkel getWinkel() {
        return winkel;
    }

    private Winkel winkel;

    private KlantOverviewPane klantOverviewPane;

    public KlantOverviewController(Winkel winkel) {
        this.winkel=winkel;
        winkel.getActieveBestelling().add(this);
        }


    private void kortingPrice(){
        double totaal=winkel.getActieveBestelling().getKorting().PrijsNaKorting(AantalList.getList(winkel));
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }

    public void setKlantOverviewPane(KlantOverviewPane klantOverviewPane) {
        this.klantOverviewPane = klantOverviewPane;
    }

    @Override
    public void update() {
        if(klantOverviewPane!=null){
            klantOverviewPane.setArtikels(AantalList.getList(winkel));
            kortingPrice();
        }
    }

}

package controller;

import model.Winkel;
import view.klantview.KlantOverviewPane;
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

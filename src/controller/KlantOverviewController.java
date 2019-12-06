package controller;

import model.Artikel;
import model.Winkel;
import model.korting.Korting;
import view.klantview.KlantOverviewPane;

public class KlantOverviewController implements Observer {
    public Winkel getWinkel() {
        return winkel;
    }

    private Winkel winkel;

    private KlantOverviewPane klantOverviewPane;

    public KlantOverviewController(Winkel winkel) {
        this.winkel=winkel;
        winkel.add(this);
        }

    private double totaal(){
        double totaal=0.0;
        for(Artikel artikel: winkel.getActieveBestelling().getArtikels()){
            totaal+=artikel.getVerkoopprijs();
        }
        return totaal;
    }

    private void kortingPrice(){
        double totaal=totaal();
        for(Korting k:winkel.getActieveBestelling().getKortingen()){
            totaal-=k.getKorting(AantalList.getList(winkel));
        }
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }

    public void setKlantOverviewPane(KlantOverviewPane klantOverviewPane) {
        this.klantOverviewPane = klantOverviewPane;
    }

    @Override
    public void update() {
        System.out.println("klantoverviewcontroller#####################");
        System.out.println(klantOverviewPane);

        if(klantOverviewPane!=null){
            klantOverviewPane.setArtikels(AantalList.getList(winkel));
            kortingPrice();
        }
    }

}

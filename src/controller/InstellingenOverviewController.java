package controller;

import model.Winkel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.panels.InstellingenOverviewPane;

public class InstellingenOverviewController implements Observer{
    //private Bestelling bestelling;
    private Winkel winkel;
    private InstellingenOverviewPane instellingenOverviewPane;

    public InstellingenOverviewController(Winkel winkel) {
        //this.bestelling = bestelling;
        this.winkel=winkel;
        winkel.getActieveBestelling().add(this);
        //bestelling.add(this);
    }

    public void setInstellingenOverviewPane(InstellingenOverviewPane instellingenOverviewPane){
        this.instellingenOverviewPane=instellingenOverviewPane;
    }

    public void setKorting(String thekorting, int hoeveelheid){
        Korting korting = KortingFactory.getInstance().createKortingString(thekorting);
        korting.setKorting(hoeveelheid);
        bestelling.setKorting(korting);
    }

    public void setKorting(String thekorting){
        Korting korting = KortingFactory.getInstance().createKortingString(thekorting);
        bestelling.setKorting(korting);
    }

    @Override
    public void update() {

    }
}

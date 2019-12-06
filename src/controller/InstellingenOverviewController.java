package controller;

import model.Winkel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.panels.InstellingenOverviewPane;

public class InstellingenOverviewController implements Observer{
    private Winkel winkel;
    private InstellingenOverviewPane instellingenOverviewPane;

    public InstellingenOverviewController(Winkel winkel) {
        this.winkel=winkel;
        winkel.getActieveBestelling().add(this);
    }

    public void setInstellingenOverviewPane(InstellingenOverviewPane instellingenOverviewPane){
        this.instellingenOverviewPane=instellingenOverviewPane;
    }

    public void setKorting(Kortingsmogelijkheden thekorting, int hoeveelheid){
        Korting korting = KortingFactory.getInstance().createKorting(thekorting);
        korting.setKorting(hoeveelheid);
        winkel.getActieveBestelling().setKorting(korting);
    }

    public void setKorting(Kortingsmogelijkheden thekorting){
        Korting korting = KortingFactory.getInstance().createKorting(thekorting);
        winkel.getActieveBestelling().setKorting(korting);
    }

    public void setLaadoptie(String laadoptie){
        winkel.getActieveBestelling().getDataInMemory().getArtikelDbContext().saveProperties(laadoptie);
    }

    @Override
    public void update() {

    }
}

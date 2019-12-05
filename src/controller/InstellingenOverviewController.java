package controller;

import model.Winkel;
import model.bestelling.Bestelling;
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

    @Override
    public void update() {

    }
}

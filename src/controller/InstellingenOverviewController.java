package controller;

import model.bestelling.Bestelling;
import view.panels.InstellingenOverviewPane;

public class InstellingenOverviewController implements Observer{
    private Bestelling bestelling;
    private InstellingenOverviewPane instellingenOverviewPane;

    public InstellingenOverviewController(Bestelling bestelling) {
        this.bestelling = bestelling;
        bestelling.add(this);
    }

    public void setInstellingenOverviewPane(InstellingenOverviewPane instellingenOverviewPane){
        this.instellingenOverviewPane=instellingenOverviewPane;
    }

    @Override
    public void update() {

    }
}

package controller;

import model.Winkel;
import model.bestelling.Bestelling;
import model.kasticket.TicketFacade;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.panels.InstellingenOverviewPane;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InstellingenOverviewController implements Observer{
    private Winkel winkel;
    private InstellingenOverviewPane instellingenOverviewPane;

    public InstellingenOverviewController(Winkel winkel) {
        this.winkel=winkel;
        winkel.add(this);
    }

    public void setInstellingenOverviewPane(InstellingenOverviewPane instellingenOverviewPane){
        this.instellingenOverviewPane=instellingenOverviewPane;
    }

    public void addKorting(Korting korting){
        winkel.addKorting(korting);
    }

    public ArrayList<Korting> getKortingen(){
        return winkel.getKortingen();
    }

    public void setLaadoptie(String laadoptie){
        winkel.getActieveBestelling().getDataInMemory().getArtikelDbContext().saveProperties(laadoptie);
    }



    @Override
    public void update() {

    }

    public void setHeader(boolean selected) {
        TicketFacade.setHeader(selected);
    }

    public void setFooter(boolean selected) {
        TicketFacade.setFooter(selected);

    }

    public void setCustomHeader(String text) {
        TicketFacade.setCustomHeader(text);
    }
    public void setCustomFooter(String text){
        TicketFacade.setCustomFooter(text);
    }

    public void setBtwFooter(boolean selected) {
        TicketFacade.setBtwFooter(selected);
    }

    public void setDatumHeader(boolean selected) {
        TicketFacade.setDatumHeader(selected);
    }

    public void setKortingFooter(boolean selected) {
        TicketFacade.setKortingFooter(selected);
    }

    //TICKET GETTERS
    public boolean getCustomHeader() {
        return TicketFacade.getHeader();
    }
    public boolean getCustomFooter(){
        return TicketFacade.getFooter();
    }

    public boolean getBtwFooter() {
        return TicketFacade.getBtwFooter();
    }

    public boolean getDatumHeader() {
        return TicketFacade.getDatumHeader();
    }

    public boolean getKortingFooter() {
        return TicketFacade.getKortingFooter();
    }

    public String getCustomHeaderText() {
        return TicketFacade.getCustomHeader();
    }
    public String getCustomFooterText(){
        return TicketFacade.getCustomFooter();
    }
    
}

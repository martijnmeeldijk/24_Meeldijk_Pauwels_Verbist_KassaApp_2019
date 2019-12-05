package controller;

import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    //private Bestelling bestelling;
    private Winkel winkel;
    private Bestelling altBestelling;

    public KassaViewController(Winkel winkel) {
        this.winkel = winkel;
    }

    private void price(){
        double totaal=0.0;
        for(Artikel artikel:getBestelling().getArtikels()){
            totaal+=artikel.getVerkoopprijs();
        }
        kassaOverviewPane.setPrijs(String.valueOf(totaal));
    }

    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void addArtikkel(int code){
        if(getBestelling().itemBestaat(code)){
            getBestelling().addArtikel(code);
            price();
        }
        else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code){
        if(getBestelling().itemBestaat(code)){
            getBestelling().removeArtikel(code);
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
        //return getBestelling().getArtikels();
        return winkel.getActieveBestelling().getArtikels();

    }

    @Override
    public void update() {
    }

    public void zetOnHold(){
        try {
            winkel.getActieveBestelling().zetOnHold();
            altBestelling = new Bestelling();
        }
        catch (Exception e){
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }

    }
    public void zetActief() {
        try {
            //bestelling.zetActief();
            winkel.getpassiveBestelling().zetActief();
        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }
    }

    //geeft de andere bestelling terug als de eerste on hold staat
    public Bestelling getBestelling() {
        //return bestelling.isActief()?bestelling:altBestelling;
        return winkel.getActieveBestelling();
    }

}

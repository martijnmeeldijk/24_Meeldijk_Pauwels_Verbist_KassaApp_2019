package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    //private Bestelling bestelling;
    private Winkel winkel;
    private Bestelling altBestelling;
    private Korting korting = KortingFactory.getInstance().createKorting(Kortingsmogelijkheden.Nummer);

    public KassaViewController(Winkel winkel) {
        this.winkel = winkel;
    }
    public void setKorting(Kortingsmogelijkheden korting){
        this.korting=KortingFactory.getInstance().createKorting(korting);
    }

    //voor prijs met korting
    /*private void price(){
        double totaal=korting.PrijsNaKorting(getList());
        kassaOverviewPane.setPrijs(String.valueOf(totaal));
    }

     private ObservableList<Artikel> getList() {
        ObservableList<Artikel> tijdelijk= FXCollections.observableArrayList();
        for(Artikel artikel:bestelling.getArtikels()){
            if(tijdelijk.contains(artikel)){
                int index= tijdelijk.indexOf(artikel);
                tijdelijk.get(index).setAantal(tijdelijk.get(index).getAantal()+1);
            }
            else {
                artikel.setAantal(1);
                tijdelijk.add(artikel);
            }
        }
        return tijdelijk;
    }*/

    private void originalPrice(){
        double totaal=0.0;
        for(Artikel artikel:getBestelling().getArtikels()){
            totaal+=artikel.getVerkoopprijs();
        }
        kassaOverviewPane.setOriginelePrijs(String.valueOf(totaal));
    }

    public KassaViewController(Bestelling bestelling) {
        this.bestelling=bestelling;
    }

    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void addArtikkel(int code){
        if(getBestelling().itemBestaat(code)){
            getBestelling().addArtikel(code);
            originalPrice();
        }
        else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code){
        if(getBestelling().itemBestaat(code)){
            getBestelling().removeArtikel(code);
            originalPrice();
        }
        else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

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

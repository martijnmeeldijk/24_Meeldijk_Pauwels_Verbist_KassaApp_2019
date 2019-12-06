package controller;

import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    private Winkel winkel;

    public KassaViewController(Winkel winkel) {
        this.winkel = winkel;
    }

    private void korting() {
        double totaal = 0.0;
        for (Korting k : winkel.getActieveBestelling().getKortingen()) {
            totaal += k.getKorting(AantalList.getList(winkel));
        }
        kassaOverviewPane.setKorting(String.valueOf(totaal));
    }

    private void originalPrice() {
        double totaal = 0.0;
        for (Artikel artikel : getBestelling().getArtikels()) {
            totaal += artikel.getVerkoopprijs();
        }
        kassaOverviewPane.setOriginelePrijs(String.valueOf(totaal));
    }


    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void addArtikkel(int code) {
        if (getBestelling().itemBestaat(code)) {
            getBestelling().addArtikel(code);
            originalPrice();
            korting();
        } else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code) {
        if (getBestelling().itemBestaat(code)) {
            getBestelling().removeArtikel(code);
            originalPrice();
            korting();
        } else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public ObservableList<Artikel> getArtikels() {
        return winkel.getActieveBestelling().getArtikels();
    }

    @Override
    public void update() {
    }

    public void zetOnHold() {
        try {
            winkel.getActieveBestelling().zetOnHold();
            winkel.addBestelling();
            originalPrice();
            korting();
            winkel.getActieveBestelling().notifyObserver();
        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }

    }

    public void zetActief() {
        try {
            winkel.getpassiveBestelling().zetActief();
            originalPrice();
            korting();

        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }
    }

    //geeft de andere bestelling terug als de eerste on hold staat
    public Bestelling getBestelling() {
        return winkel.getActieveBestelling();
    }

    public void sluitAf() {
        try {
            winkel.getActieveBestelling().sluitAf();
        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }
    }
}

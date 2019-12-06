package controller;

import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import model.bestelling.state.Actief;
import model.bestelling.state.Afgesloten;
import model.korting.Korting;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    private Winkel winkel;

    public KassaViewController(Winkel winkel) {
        this.winkel = winkel;
        winkel.add(this);
    }

    private void korting() {
        double totaal = 0.0;
        for (Korting k : winkel.getKortingen()) {
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
            winkel.addArtikel(code);
            originalPrice();
            korting();
        } else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code) {
        if (getBestelling().itemBestaat(code)) {
            winkel.removeArtikel(code);
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
        if(kassaOverviewPane!=null){
            kassaOverviewPane.setArtikels(AantalList.getList(winkel));
            viewLabelReset();
        }
    }

    public void viewLabelReset() {
        originalPrice();
        korting();
    }

    public void zetOnHold() {
        try {
            if(winkel.getpassiveBestelling()!=null){
                kassaOverviewPane.displayErrorMessage("er is al een bestelling on hold");
            }
            else {
                if (winkel.getActieveBestelling().getArtikels().size() != 0) {
                    winkel.getActieveBestelling().zetOnHold();
                    winkel.addBestelling();
                    viewLabelReset();
                    winkel.notifyObserver();
                } else {
                    kassaOverviewPane.displayErrorMessage("de bestelling is leeg");
                }
            }

        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }

    }


    public void zetActief() {
        try {
            if (winkel.getActieveBestelling().getArtikels().size() == 0) {
                winkel.removeActiveBestelling();
                winkel.getpassiveBestelling().zetActief();
                viewLabelReset();
                winkel.notifyObserver();

            } else {
                kassaOverviewPane.displayErrorMessage("er is een verkoop bezig");
            }
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

    public void annuleer() {
        winkel.removeActiveBestelling();
        viewLabelReset();
        winkel.notifyObserver();

    }

    public void handelBestellingAf() {

        if(winkel.getActieveBestelling().getCurrentState() instanceof Actief){
            sluitAf();
            kassaOverviewPane.setSluitAf("betaal");
        }
        else if(winkel.getActieveBestelling().getCurrentState() instanceof Afgesloten){
            winkel.removeActiveBestelling();
            winkel.addBestelling();
            winkel.notifyObserver();
            kassaOverviewPane.setSluitAf("Sluit Af");
        }
    }
}

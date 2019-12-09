package controller;

import javafx.collections.ObservableList;
import model.Artikel;
import model.LogObject;
import model.Winkel;
import model.bestelling.Bestelling;
import model.bestelling.state.Actief;
import model.bestelling.state.Afgesloten;
import model.kasticket.DecoratorFactory;
import model.kasticket.Ticket;
import model.korting.Korting;
import view.panels.KassaOverviewPane;

public class KassaViewController implements Observer {
    private KassaOverviewPane kassaOverviewPane;
    private Winkel winkel;

    public KassaViewController(Winkel winkel) {
        this.winkel = winkel;
        winkel.add(this);
    }

    private double getKorting() {
        double totaal = 0.0;
        for (Korting k : winkel.getKortingen()) {
            totaal += k.getKorting(AantalList.getList(winkel));
        }
        return totaal;
    }

    private void setPrijzen() {
        kassaOverviewPane.setPrijzen(String.valueOf(getOriginalPrice()),String.valueOf(getKorting()),String.valueOf(getOriginalPrice()-getKorting()));
    }

    private double getOriginalPrice() {
        double totaal = 0.0;
        for (Artikel artikel : getBestelling().getArtikels()) {
            totaal += artikel.getVerkoopprijs();
        }
        return totaal;
    }

    public void setKassaView(KassaOverviewPane kassaOverviewPane) {
        this.kassaOverviewPane = kassaOverviewPane;
    }

    public void addArtikkel(int code) {
        if (getBestelling().itemBestaat(code)) {
            winkel.addArtikel(code);
            setPrijzen();
        } else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public void removeArtikkel(int code) {
        if (getBestelling().itemBestaat(code)) {
            winkel.removeArtikel(code);
            setPrijzen();
        } else {
            kassaOverviewPane.displayErrorMessage("niet bestaande code");
        }
    }

    public ObservableList<Artikel> getArtikels() {
        return winkel.getActieveBestelling().getArtikels();
    }

    @Override
    public void update() {
        System.out.println("kassa update");
        if (kassaOverviewPane != null) {
            kassaOverviewPane.setArtikels(getArtikels());
            viewLabelReset();
        }
    }

    public void zetOnHold() {
        try {
            if (winkel.getpassiveBestelling() != null) {
                kassaOverviewPane.displayErrorMessage("er is al een bestelling on hold");
            } else {
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
            if(winkel.getpassiveBestelling()!=null){
            if (winkel.getActieveBestelling().getArtikels().size() == 0) {
                winkel.removeActiveBestelling();
                winkel.getpassiveBestelling().zetActief();
                viewLabelReset();
                winkel.notifyObserver();

            } else {
                kassaOverviewPane.displayErrorMessage("er is een verkoop bezig");
            }
            }
            else {
                kassaOverviewPane.displayErrorMessage("Geen on hold bestelling");
            }
        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }
    }

    //geeft de andere bestelling terug als de eerste on hold staat
    public Bestelling getBestelling() {
        return winkel.getActieveBestelling();
    }

    public void annuleer() {
        winkel.annuleerBestelling();
        viewLabelReset();
        winkel.notifyObserver();
    }

    private void viewLabelReset() {
        setPrijzen();
    }

    public void handelBestellingAf() {
        if (winkel.getActieveBestelling().getCurrentState() instanceof Actief) {
            sluitAf();
        } else if (winkel.getActieveBestelling().getCurrentState() instanceof Afgesloten) {
            // hier moet de code voor wat er gebeurt als er op de betaal knop gedrukt wordt
            LogObject logObject = new LogObject(getOriginalPrice(), getKorting(), (getOriginalPrice() - getKorting()));
            winkel.addLog(logObject);
            Ticket ticket = DecoratorFactory.getInstance().decorateTicket(winkel);
            System.out.print(ticket.print());
            //save vooraadd moet nog
            winkel.removeActiveBestelling();
            winkel.addBestelling();
            winkel.notifyObserver();
            boolean verlopen = winkel.checkHoldBestelling();
            if(verlopen) kassaOverviewPane.onHoldVerlopen();
        }
    }
    private void sluitAf() {
        try {
            winkel.getActieveBestelling().sluitAf();
        } catch (Exception e) {
            kassaOverviewPane.displayErrorMessage(e.getMessage());
        }
    }
}

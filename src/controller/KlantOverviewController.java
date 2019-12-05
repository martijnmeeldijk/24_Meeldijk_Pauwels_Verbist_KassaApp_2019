package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.bestelling.Bestelling;
import model.korting.Korting;
import model.korting.KortingFactory;
import model.korting.Kortingsmogelijkheden;
import view.KlantOverviewPane;

public class KlantOverviewController implements Observer {
    private Bestelling bestelling;
    private KlantOverviewPane klantOverviewPane;

    private void berekenPrice(){
        double totaal=bestelling.getKorting().PrijsNaKorting(getList());
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }

    public KlantOverviewController(Bestelling bestelling) {
        this.bestelling = bestelling;
        bestelling.add(this);
    }

    public void setKlantOverviewPane(KlantOverviewPane klantOverviewPane) {
        this.klantOverviewPane = klantOverviewPane;
    }

    @Override
    public void update() {
        if(klantOverviewPane!=null){
            klantOverviewPane.setArtikels(getList());
            berekenPrice();
        }
    }

    private void originalberekenPrice(){
        double totaal=0.0;

        for(Artikel artikel:getList()){
            totaal+=artikel.getVerkoopprijs() * artikel.getAantal();
        }
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }


    public ObservableList<Artikel> getList() {
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
    }

}

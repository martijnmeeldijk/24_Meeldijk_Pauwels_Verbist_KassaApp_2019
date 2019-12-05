package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;
import view.KlantOverviewPane;

public class KlantOverviewController implements Observer {
    //private Bestelling bestelling;
    private Winkel winkel;

    private KlantOverviewPane klantOverviewPane;

    public KlantOverviewController(Winkel winkel) {
        //this.bestelling = bestelling;
        this.winkel=winkel;
        winkel.getActieveBestelling().add(this);
        //bestelling.add(this);

    }

    public void setKlantOverviewPane(KlantOverviewPane klantOverviewPane) {
        this.klantOverviewPane = klantOverviewPane;
    }



    @Override
    public void update() {
        //check of dit null is voor tijdens het opstarten van de applicatie
        if(klantOverviewPane!=null){
            klantOverviewPane.setArtikels(getList());
            berekenPrice();
        }
    }

    private void berekenPrice(){
        double totaal=0.0;
        for(Artikel artikel:getList()){
            totaal+=artikel.getVerkoopprijs() * artikel.getAantal();
        }
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }


    public ObservableList<Artikel> getList() {
        System.out.println("hier");
        ObservableList<Artikel>tijdelijk= FXCollections.observableArrayList();
        for(Artikel artikel:winkel.getActieveBestelling().getArtikels()){
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

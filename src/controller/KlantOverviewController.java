package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Artikel;
import model.bestelling.Bestelling;
import view.KlantOverviewPane;

public class KlantOverviewController implements Observer {
    Bestelling bestelling;
    KlantOverviewPane klantOverviewPane;

    public KlantOverviewController(Bestelling bestelling) {
        this.bestelling = bestelling;
        bestelling.add(this);

    }

    public void setKlantOverviewPane(KlantOverviewPane klantOverviewPane) {
        this.klantOverviewPane = klantOverviewPane;
    }



    @Override
    public void update() {
        //System.out.println("klantoverview controller updated");
        if(klantOverviewPane!=null){
            klantOverviewPane.setArtikels(getList());
            berekenPrice();

        }
    }
    public void berekenPrice(){
        double totaal=0.0;

        for(Artikel artikel:getList()){
            totaal+=artikel.getVerkoopprijs() * artikel.getAantal();
        }
        klantOverviewPane.setPrijs(String.valueOf(totaal));
    }


    public ObservableList<Artikel> getList() {
        System.out.println("hier");
        ObservableList<Artikel>tijdelijk= FXCollections.observableArrayList();
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

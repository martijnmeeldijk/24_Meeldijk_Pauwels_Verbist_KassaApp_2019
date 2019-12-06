package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;
import model.Artikelgroep;

public class Groepkorting implements Korting{
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Groep;
    private Artikelgroep groep = Artikelgroep.gr1;
    private int kortingspercentage=5;

    public void setGroep(Artikelgroep groep){
        this.groep=groep;
    }

    @Override
    public double getKorting(ObservableList<Artikel> list) {
        double totaal=0.0;
        for(Artikel artikel: list){
            if(artikel.getArtikelgroep().equals(groep)){
                totaal+=artikel.getVerkoopprijs()*(0.01*kortingspercentage)*artikel.getAantal();
            }
        }
        return totaal;
    }

    @Override
    public void setKortingspercentage(int korting) {
        this.kortingspercentage=korting;
    }

    @Override
    public String toString(){
        return "Groepskorting"+" groep: "+groep+", korting: "+kortingspercentage;
    }
}

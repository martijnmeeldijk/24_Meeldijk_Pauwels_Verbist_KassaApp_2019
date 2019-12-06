package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Nummerkorting implements Korting{
    private Kortingsmogelijkheden korting= Kortingsmogelijkheden.Nummer;
    private int kortingspercentage=0;

    @Override
    public double getKorting(ObservableList<Artikel> list) {
        if(kortingspercentage==0) return 0;
        double totaal=0.0;
        for(Artikel artikel:list){
            totaal+=artikel.getVerkoopprijs()*(0.01*kortingspercentage)*artikel.getAantal();
        }
        return totaal;
    }

    @Override
    public void setKortingspercentage(int korting) {
        this.kortingspercentage=korting;
    }

    @Override
    public String toString(){
        return "Nummerkorting"+" korting: "+kortingspercentage;
    }
}

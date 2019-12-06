package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Nummerkorting implements Korting{
    private Kortingsmogelijkheden korting= Kortingsmogelijkheden.Nummer;

    @Override
    public double korting(ObservableList<Artikel> list) {
        if(korting.getKorting()==0) return 0;
        double totaal=0.0;
        for(Artikel artikel:list){
            totaal+=artikel.getVerkoopprijs()*(0.01*korting.getKorting())*artikel.getAantal();
        }
        return totaal;
    }

    @Override
    public void setKorting(int korting) {
        this.korting.setKorting(korting);
    }

    @Override
    public String toString(){
        return "Nummerkorting"+" korting: "+korting.getKorting();
    }
}

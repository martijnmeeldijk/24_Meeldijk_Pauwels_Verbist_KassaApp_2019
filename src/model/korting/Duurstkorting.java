package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Duurstkorting implements Korting{
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Duurst;

    private Artikel getMax(ObservableList<Artikel> list){
        Artikel max = null;
        for(Artikel artikel: list){
            if(max==null || max.getVerkoopprijs()<artikel.getVerkoopprijs()) max = artikel;
        }
        return max;
    }

    @Override
    public double korting(ObservableList<Artikel> list) {
        Artikel max = getMax(list);
        return max.getVerkoopprijs()*(0.01*korting.getKorting());
    }

    @Override
    public void setKorting(int korting) {
        this.korting.setKorting(korting);
    }

    @Override
    public String toString(){
        return "Duurstekorting"+" korting: "+korting.getKorting();
    }
}

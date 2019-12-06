package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Duurstkorting implements Korting{
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Duurst;
    private int kortingspercentage=25;

    private Artikel getMax(ObservableList<Artikel> list){
        Artikel max = null;
        for(Artikel artikel: list){
            if(max==null || max.getVerkoopprijs()<artikel.getVerkoopprijs()) max = artikel;
        }
        return max;
    }

    @Override
    public double getKorting(ObservableList<Artikel> list) {
        Artikel max = getMax(list);
        return max.getVerkoopprijs()*(0.01*kortingspercentage);
    }

    @Override
    public void setKortingspercentage(int korting) {
        this.kortingspercentage=korting;
    }

    @Override
    public String prop() {
        return korting+"/"+kortingspercentage;
    }

    @Override
    public String toString(){
        return "Duurstekorting"+" korting: "+kortingspercentage;
    }
}

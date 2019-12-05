package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Duurst implements Korting{
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Duurst;

    private Artikel getMax(ObservableList<Artikel> list){
        Artikel max = null;
        for(Artikel artikel: list){
            if(max==null || max.getVerkoopprijs()<artikel.getVerkoopprijs()) max = artikel;
        }
        return max;
    }

    @Override
    public double PrijsNaKorting(ObservableList<Artikel> list) {
        double totaal=0.0;
        Artikel max = getMax(list);
        for(Artikel artikel: list){
            if(artikel.equals(max)){
                totaal+=artikel.getVerkoopprijs()*(1-(0.01*korting.getKorting()));
            }
            else totaal+=artikel.getVerkoopprijs();
        }
        return totaal;
    }

    @Override
    public void setKorting(int korting) {
        this.korting.setKorting(korting);
    }
}

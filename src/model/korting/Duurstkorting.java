package model.korting;

import javafx.collections.ObservableList;
import model.artikel.Artikel;

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
        if(list!=null && list.size()>0){
            Artikel max = getMax(list);
            return max.getVerkoopprijs()*(0.01*kortingspercentage);
        }
        return 0;
    }

    @Override
    public void setKortingspercentage(int korting) {
        if(korting>100 || korting<0) throw new IllegalArgumentException();
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

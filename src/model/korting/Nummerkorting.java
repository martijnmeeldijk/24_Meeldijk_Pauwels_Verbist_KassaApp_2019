package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Nummerkorting implements Korting{
    private Kortingsmogelijkheden korting= Kortingsmogelijkheden.Nummer;

    @Override
    public double PrijsNaKorting(ObservableList<Artikel> list) {
        double totaal=0.0;
        for(Artikel artikel:list){
            totaal+=artikel.getVerkoopprijs()*(1-(0.01*korting.getKorting()));
        }
        return totaal;
    }

    @Override
    public void setKorting(int korting) {
        this.korting.setKorting(korting);
    }
}

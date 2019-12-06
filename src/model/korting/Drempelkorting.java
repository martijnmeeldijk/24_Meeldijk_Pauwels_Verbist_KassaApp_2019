package model.korting;

import javafx.collections.ObservableList;
import model.Artikel;

public class Drempelkorting implements Korting {
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Drempel;
    private double drempel=100;

    private double berekenTotaal(ObservableList<Artikel> list){
        double totaal=0.0;
        for(Artikel artikel: list){
            totaal+=artikel.getVerkoopprijs()*artikel.getAantal();
        }
        return totaal;
    }

    public double getDrempel() {
        return drempel;
    }

    public void setDrempel(double drempel) {
        this.drempel = drempel;
    }

    @Override
    public double korting(ObservableList<Artikel> list) {
        double totaal = berekenTotaal(list);
        if(totaal>drempel){
            return totaal*(0.01*korting.getKorting());
        }
        return totaal;
    }

    @Override
    public void setKorting(int korting) {
        this.korting.setKorting(korting);
    }

    @Override
    public String toString(){
        return "Drempelkorting"+" drempel: "+drempel+", korting: "+korting.getKorting();
    }
}

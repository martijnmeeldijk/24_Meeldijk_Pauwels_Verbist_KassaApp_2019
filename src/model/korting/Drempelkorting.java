package model.korting;

import javafx.collections.ObservableList;
import model.artikel.Artikel;

public class Drempelkorting implements Korting {
    private Kortingsmogelijkheden korting = Kortingsmogelijkheden.Drempel;
    private double drempel=100;
    private int kortingspercentage=5;

    private double berekenTotaal(ObservableList<Artikel> list){
        double totaal=0.0;
        if(list!=null){
            for(Artikel artikel: list){
                totaal+=artikel.getVerkoopprijs()*artikel.getAantal();
            }
        }
        return totaal;
    }

    public void setDrempel(double drempel) {
        this.drempel = drempel;
    }

    @Override
    public double getKorting(ObservableList<Artikel> list) {
        double totaal = berekenTotaal(list);
        if(totaal>drempel){
            return totaal*(0.01*kortingspercentage);
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
        return korting+"/"+kortingspercentage+"/"+drempel;
    }

    @Override
    public String toString(){
        return "Drempelkorting"+" drempel: "+drempel+", korting: "+kortingspercentage;
    }
}

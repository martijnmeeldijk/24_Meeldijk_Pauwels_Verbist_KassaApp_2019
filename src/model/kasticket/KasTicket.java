package model.kasticket;

import controller.AantalList;
import javafx.collections.ObservableList;
import model.Artikel;
import model.Winkel;
import model.bestelling.Bestelling;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Observable;

public class KasTicket extends Ticket {
    private Winkel winkel;

    public KasTicket(Winkel winkel) {
        this.winkel = winkel;
    }

    public String print(){
        List artikels =  AantalList.getList(winkel);
        StringBuilder totaal = new StringBuilder(String.format("%-20s %10s %10s%n", "Omschrijving", "Aantal", "Prijs"));
        totaal.append(repeatString("*", 42));
        for(Object b : artikels){
            Artikel a = (Artikel) b;
            totaal.append(String.format("%n%-20s %10d %10.2f", a.getOmschrijving(), a.getAantal(), a.getVerkoopprijs() * a.getAantal()));

        }
        return totaal.toString();
    }



    private static String printDemo(){
        Winkel winkel = new Winkel();
        winkel.addArtikel(1);
        winkel.addArtikel(10);
        winkel.addArtikel(2);
        winkel.addArtikel(1);
        winkel.addArtikel(10);
        List artikels =  AantalList.getList(winkel);

        String totaal = String.format("%-20s %10s %10s%n", "Omschrijving", "Aantal", "Prijs");
        totaal += repeatString("*",42);

        for(Object b : artikels){
            Artikel a = (Artikel) b;
            totaal += String.format("%n%-20s %10d %10.2f", a.getOmschrijving(), a.getAantal(), a.getVerkoopprijs()*a.getAantal());

        }
        return totaal;

    }

    public static void main(String[] args) {
        System.out.print(printDemo());

    }
    private static String repeatString(String string, int amount){
        String result ="";
        for(int i = 0; i<amount; i++){
            result += string;
        }
        return result;
    }

    public Winkel getWinkel() {
        return winkel;
    }
}

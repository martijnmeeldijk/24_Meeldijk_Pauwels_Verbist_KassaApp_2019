package model.kasticket;

import controller.AantalList;
import model.artikel.Artikel;
import model.Winkel;

import java.util.List;

public class KasTicket extends Ticket {

    public KasTicket(Winkel winkel) {
        super(winkel);

    }

    public String print(){
        List artikels =  AantalList.getList(winkel);
        String  totaal = String.format("%n%-20s %10s %10s%n", "Omschrijving", "Aantal", "Prijs");
        totaal += (repeatString("*", 42));
        double sum = 0.0;
        for(Object b : artikels){
            Artikel a = (Artikel) b;
            totaal += (String.format("%n%-20s %10d %10.2f", a.getOmschrijving(), a.getAantal(), a.getVerkoopprijs() * a.getAantal()));
            sum += a.getVerkoopprijs() * a.getAantal();

        }
        totaal += ("\n" + repeatString("*", 42) + "\nBetaald (inclusief korting): €" + sum + "\n");
        return totaal;
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

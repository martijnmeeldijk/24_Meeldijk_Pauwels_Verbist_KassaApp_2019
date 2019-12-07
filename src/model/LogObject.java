package model;

import jxl.write.DateTime;
import model.bestelling.Bestelling;
import model.korting.Korting;

import java.util.Date;

public class LogObject {
    Date dateTime;
    double totaal;
    double korting;
    double tebetalen;

    public LogObject(double totaal, double korting, double tebetalen) {
        dateTime=new Date();
        this.totaal = totaal;
        this.korting = korting;
        this.tebetalen = tebetalen;
    }
}

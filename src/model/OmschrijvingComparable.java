package model;

import java.util.Comparator;

public class OmschrijvingComparable implements Comparator<Artikel> {
    @Override
    public int compare(Artikel o1, Artikel o2) {
        return o1.getOmschrijving().toLowerCase().compareTo(o2.getOmschrijving().toLowerCase());
    }
}

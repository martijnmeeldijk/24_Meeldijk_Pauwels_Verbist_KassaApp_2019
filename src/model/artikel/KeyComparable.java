package model.artikel;


import java.util.Comparator;

public class KeyComparable implements Comparator<Artikel> {
    @Override
    public int compare(Artikel o1, Artikel o2) {
        return Integer.compare(o1.getCode(),o2.getCode());
    }
}

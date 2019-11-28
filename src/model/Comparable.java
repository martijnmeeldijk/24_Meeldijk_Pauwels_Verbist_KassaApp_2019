package model;


import java.util.Comparator;

public class Comparable implements Comparator<Artikel> {
    @Override
    public int compare(Artikel o1, Artikel o2) {
        if(o1.getCode()==o2.getCode()) return 0;
        if(o1.getCode()>o2.getCode()) return 1;
        return -1;
    }
}

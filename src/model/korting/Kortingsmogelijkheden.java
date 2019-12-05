package model.korting;

public enum Kortingsmogelijkheden {
    Groep(5),
    Drempel(5),
    Nummer(0),
    Duurst(25);

    private int korting;

    Kortingsmogelijkheden(int korting){this.korting=korting;}

    public void setKorting(int korting){this.korting=korting;}
    public int getKorting(){return korting;}

}

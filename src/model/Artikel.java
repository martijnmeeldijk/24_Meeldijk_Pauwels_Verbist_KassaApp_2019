package model;

public class Artikel {
    private int code;
    private String omschrijving;
    private String artikelgroep;
    private double verkoopprijs;
    private int voorraad;

    public Artikel(int code, String omschrijving, String artikelgroep, double verkoopprijs, int voorraad) {
        this.code = code;
        this.omschrijving = omschrijving;
        this.artikelgroep = artikelgroep;
        this.verkoopprijs = verkoopprijs;
        this.voorraad = voorraad;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getArtikelgroep() {
        return artikelgroep;
    }

    public void setArtikelgroep(String artikelgroep) {
        this.artikelgroep = artikelgroep;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setVerkoopprijs(double verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
}

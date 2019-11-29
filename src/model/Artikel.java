package model;

public class Artikel {
    private int code;
    private String omschrijving;
    private String artikelgroep;
    private double verkoopprijs;
    private int voorraad;
    private final static int btw = 6;

    public Artikel(int code, String omschrijving, String artikelgroep, double verkoopprijs, int voorraad) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelgroep(artikelgroep);
        setVerkoopprijs(verkoopprijs);
        setVoorraad(voorraad);
    }
    public static Artikel MaakArtikel(String code, String omschrijving, String artikelgroep, String verkoopprijs, String voorraad) {
        try {
            return new Artikel(Integer.parseInt(code), omschrijving, artikelgroep,
                    Double.parseDouble(verkoopprijs), Integer.parseInt(voorraad));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if(code<0){
            throw new IllegalArgumentException("code mag niet negatief zijn");
        }
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        if(omschrijving==null || omschrijving.length()==0){
            throw new IllegalArgumentException("omschrijving mag niet leeg zijn of null zijn");
        }
        this.omschrijving = omschrijving;
    }

    public String getArtikelgroep() {
        return artikelgroep;
    }

    public void setArtikelgroep(String artikelgroep) {
        if(artikelgroep==null || artikelgroep.length()==0){
            throw new IllegalArgumentException("artikelgroep mag niet leeg zijn of null zijn");
        }
        this.artikelgroep = artikelgroep;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setVerkoopprijs(double verkoopprijs) {
        if(verkoopprijs<0){
            throw new IllegalArgumentException("verkoopsprijs mag niet 0 zijn");
        }
        this.verkoopprijs = verkoopprijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        if(verkoopprijs<0){
            throw new IllegalArgumentException("voorraad mag niet 0 zijn");
        }
        this.voorraad = voorraad;
    }



    @Override
    public String toString(){
        return code+" "+omschrijving+" "+artikelgroep+" "+verkoopprijs+" "+voorraad;
    }
}

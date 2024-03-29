package model.artikel;

public class Artikel {
    private int code;
    private String omschrijving;
    private Artikelgroep artikelgroep;
    private double verkoopprijs;
    private int voorraad;
    private int aantal;
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

    private void setCode(int code) {
        if(code<0){
            throw new IllegalArgumentException("code mag niet negatief zijn");
        }
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    private void setOmschrijving(String omschrijving) {
        if(omschrijving==null || omschrijving.length()==0){
            throw new IllegalArgumentException("omschrijving mag niet leeg zijn of null zijn");
        }
        this.omschrijving = omschrijving;
    }

    public static int getBtw() {
        return btw;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Artikelgroep getArtikelgroep() {
        return artikelgroep;
    }

    private void setArtikelgroep(String artikelgroep) {
        if(artikelgroep==null || artikelgroep.length()==0){
            throw new IllegalArgumentException("artikelgroep mag niet leeg zijn of null zijn");
        }
        this.artikelgroep = Artikelgroep.valueOf(artikelgroep);
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    private void setVerkoopprijs(double verkoopprijs) {
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
    public boolean equals(Object obj){
        if(obj instanceof Artikel) {
            Artikel artikel = (Artikel) obj;
            if (artikel.code == getCode()) return true;
        }
        return false;
    }




    @Override
    public String toString(){
        return code+" "+omschrijving+" "+artikelgroep+" "+verkoopprijs+" "+voorraad;
    }
}

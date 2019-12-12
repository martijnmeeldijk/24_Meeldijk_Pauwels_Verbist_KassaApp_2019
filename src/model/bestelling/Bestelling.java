package model.bestelling;

import database.ArtikelDbStrat.DataInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.artikel.Artikel;
import model.bestelling.state.*;

public class Bestelling {
    private ObservableList<Artikel> artikels;

    private DataInMemory dataInMemory;

    private BestellingState actief;
    private BestellingState onHold;
    private BestellingState afgesloten;
    private BestellingState betaald;
    private BestellingState currentState;

    public Bestelling() {
        dataInMemory= new DataInMemory();
        artikels= FXCollections.observableArrayList();

        actief = new Actief(this);
        onHold = new OnHold(this);
        afgesloten = new Afgesloten(this);
        betaald = new Betaald(this);
        this.currentState = actief;
    }

    public ObservableList<Artikel> getArtikels() {
        return artikels;
    }
    public void addArtikel(int code){
        currentState.addArtikel(code);
    }
    public void removeArtikel(int code){
        currentState.removeArtikel(code);
    }

    public boolean itemBestaat(int getal){
        return dataInMemory.getArtikel(getal) != null;
    }
    public DataInMemory getDataInMemory() {
        return dataInMemory;
    }


    /** STATE **/
    public void zetOnHold(){
        currentState.zetOnHold();
    }
    public void zetActief(){
        currentState.zetActief();
    }
    public void sluitAf(){
        if(!artikels.isEmpty()){
            currentState.sluitAf();
        }
        else{
            throw new NotPossibleException("Je kan geen bestelling afsluiten met een leeg winkelmandje");
        }
    }
    public void betaal() {
        currentState.betaal();
    }

    public BestellingState getCurrentState(){
        return currentState;
    }
    public void setCurrentState(BestellingState bestellingState){
        this.currentState = bestellingState;
    }

    public BestellingState getActief() {
        return actief;
    }
    public BestellingState getOnHold() {
        return onHold;
    }
    public BestellingState getAfgesloten() {
        return afgesloten;
    }
    public BestellingState getBetaald() {
        return betaald;
    }

    public boolean isActiefOfAfgesloten(){
        return currentState.getClass() == Actief.class || currentState.getClass() == Afgesloten.class  ;
    }
}

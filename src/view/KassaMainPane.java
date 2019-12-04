package view;


import controller.ArtikelOverviewController;
import controller.KassaViewController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.bestelling.Bestelling;
import view.panels.ArtikelOverviewPane;
import view.panels.KassaOverviewPane;

public class KassaMainPane extends BorderPane {

	public KassaMainPane(Bestelling bestelling){
        ArtikelOverviewController artikelOverviewController= new ArtikelOverviewController(bestelling);

	    TabPane tabPane = new TabPane();

	    //kassa
	    KassaViewController kassaViewController= new KassaViewController(bestelling);
        KassaOverviewPane kassaOverviewPane=new KassaOverviewPane(kassaViewController);
        Tab kassaTab = new Tab("Kassa", kassaOverviewPane);
        tabPane.getTabs().add(kassaTab);

        //artikkelen
        ArtikelOverviewPane artikelOverviewPane = new ArtikelOverviewPane(artikelOverviewController);
        Tab artikelTab = new Tab("Artikelen",artikelOverviewPane);
        tabPane.getTabs().add(artikelTab);

        //instellingen
        Tab instellingTab = new Tab("Instellingen");
        tabPane.getTabs().add(instellingTab);

        //log
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(logTab);

	    this.setCenter(tabPane);
	}
}

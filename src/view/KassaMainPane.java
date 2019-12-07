package view;

import controller.ArtikelOverviewController;
import controller.InstellingenOverviewController;
import controller.KassaViewController;
import controller.LogViewController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Winkel;
import model.bestelling.Bestelling;
import view.panels.ArtikelOverviewPane;
import view.panels.InstellingenOverviewPane;
import view.panels.KassaOverviewPane;
import view.panels.LogviewPane;

public class
KassaMainPane extends BorderPane {

	public KassaMainPane(Winkel winkel){
	    TabPane tabPane = new TabPane();

	    //kassa
	    KassaViewController kassaViewController= new KassaViewController(winkel);
        KassaOverviewPane kassaOverviewPane=new KassaOverviewPane(kassaViewController);
        LogViewController logViewController= new LogViewController(winkel);
        LogviewPane logviewPane= new LogviewPane(logViewController);
        Tab kassaTab = new Tab("Kassa", kassaOverviewPane);
        tabPane.getTabs().add(kassaTab);

        //artikkelen
        ArtikelOverviewController artikelOverviewController= new ArtikelOverviewController(winkel);
        ArtikelOverviewPane artikelOverviewPane = new ArtikelOverviewPane(artikelOverviewController);
        Tab artikelTab = new Tab("Artikelen",artikelOverviewPane);
        tabPane.getTabs().add(artikelTab);

        //instellingen
        InstellingenOverviewController instellingenOverviewController = new InstellingenOverviewController(winkel);
        InstellingenOverviewPane instellingenOverviewPane = new InstellingenOverviewPane(instellingenOverviewController);
        Tab instellingTab = new Tab("Instellingen",instellingenOverviewPane);
        tabPane.getTabs().add(instellingTab);

        //log
        Tab logTab = new Tab("Log", logviewPane);
        tabPane.getTabs().add(logTab);

	    this.setCenter(tabPane);
	}
}

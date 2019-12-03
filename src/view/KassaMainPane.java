package view;


import controller.ArtikelOverviewController;
import controller.KassaViewController;
import database.ArtikelDbContext;
import database.ArtikelDbStrategy;
import database.ArtikelTextLoadSave;
import database.DataInMemory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Bestelling;
import view.panels.ArtikelOverviewPane;
import view.panels.KassaOverviewPane;

public class KassaMainPane extends BorderPane {

	public KassaMainPane(Bestelling bestelling){
        ArtikelOverviewController artikelOverviewController= new ArtikelOverviewController(bestelling);

	    TabPane tabPane = new TabPane();
	    KassaViewController kassaViewController= new KassaViewController(bestelling);
        KassaOverviewPane kassaOverviewPane=new KassaOverviewPane(kassaViewController);
        Tab kassaTab = new Tab("Kassa", kassaOverviewPane);
        ArtikelOverviewPane artikelOverviewPane = new ArtikelOverviewPane(artikelOverviewController);
        Tab artikelTab = new Tab("Artikelen",artikelOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}

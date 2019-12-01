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
import view.panels.ArtikelOverviewPane;

public class KassaMainPane extends BorderPane {

	public KassaMainPane(){
        ArtikelOverviewController artikelOverviewController= new ArtikelOverviewController();

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa");
        // artikeloverviewpane moet niet gewoon een new artikeltextloadsave() object aanmaken! het is om te testen
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

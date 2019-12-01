package view;


import controller.ArtikelOverviewController;
import database.ArtikelDbStrategy;
import database.ArtikelTextLoadSave;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.ArtikelOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa");
        ArtikelOverviewController artikelOverviewController= new ArtikelOverviewController();
        // artikeloverviewpane moet niet gewoon een new artikeltextloadsave() object aanmaken! het is om te testen
        ArtikelOverviewPane productOverviewPane = new ArtikelOverviewPane(new ArtikelTextLoadSave(),artikelOverviewController);
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}

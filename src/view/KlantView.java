package view;

import controller.ArtikelOverviewController;
import controller.KlantOverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bestelling;
import view.panels.KlantOverviewPane;

public class KlantView  {
	private Stage stage = new Stage();


	public KlantView(Bestelling bestelling){

		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		KlantOverviewController klantOverviewController= new KlantOverviewController(bestelling);

		root.getChildren().add(new KlantOverviewPane(klantOverviewController));
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}

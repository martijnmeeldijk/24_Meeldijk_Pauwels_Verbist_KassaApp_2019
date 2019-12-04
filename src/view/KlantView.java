package view;

import controller.KlantOverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.bestelling.Bestelling;
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

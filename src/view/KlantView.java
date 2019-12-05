package view;

import controller.KlantOverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Winkel;
import model.bestelling.Bestelling;

public class KlantView  {
	private Stage stage = new Stage();


	public KlantView(Winkel winkel){

		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);

		//positie
		stage.setX(760);
		stage.setY(20);

		Group root = new Group();
		Scene scene = new Scene(root, 520, 500);

		KlantOverviewController klantOverviewController= new KlantOverviewController(winkel);

		GridPane gridPane = new KlantOverviewPane(klantOverviewController);
		gridPane.prefHeightProperty().bind(scene.heightProperty());
		gridPane.prefWidthProperty().bind(scene.widthProperty());

		root.getChildren().add(gridPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}

package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.bestelling.Bestelling;

public class KassaView extends GridPane {
	private Stage stage = new Stage();

	public KassaView(Bestelling bestelling){

		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);

		//positie
		stage.setX(20);
		stage.setY(20);

		Group root = new Group();
		Scene scene = new Scene(root, 725, 500);

		BorderPane borderPane = new KassaMainPane(bestelling);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

	}

}

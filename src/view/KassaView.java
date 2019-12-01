package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class KassaView extends GridPane {
	private Stage stage = new Stage();
	private Label label = new Label("test");


	public KassaView(){			
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		BorderPane borderPane = new KassaMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		this.add(label,6,6,1,1);
		root.getChildren().add(label);
		stage.setScene(scene);
		stage.sizeToScene();

		stage.show();

	}

	public void setLabel(Label label) {
		this.label = label;
	}
}

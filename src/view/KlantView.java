package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KlantView extends GridPane {
	private Stage stage = new Stage();
	private Label label = new Label("test");


	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		this.add(label,0,0,1,1);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}

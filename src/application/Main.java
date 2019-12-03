package application;
	
import controller.KassaViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Bestelling;
import view.KassaView;
import view.KlantView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Bestelling bestelling= new Bestelling();
		KassaView kassaView = new KassaView(bestelling);
		KlantView klantView = new KlantView(bestelling);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
}

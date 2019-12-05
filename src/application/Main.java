package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Winkel;
import model.bestelling.Bestelling;
import view.KassaView;
import view.KlantView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Winkel winkel= new Winkel();
		KassaView kassaView = new KassaView(winkel);
		KlantView klantView = new KlantView(winkel);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
}

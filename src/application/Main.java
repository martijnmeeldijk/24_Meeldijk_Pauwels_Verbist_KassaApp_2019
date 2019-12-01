package application;
	
import controller.KassaViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		System.out.println("hier");
		KassaViewController kassaViewController= new KassaViewController();
		KassaView kassaView = new KassaView(kassaViewController);
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
}

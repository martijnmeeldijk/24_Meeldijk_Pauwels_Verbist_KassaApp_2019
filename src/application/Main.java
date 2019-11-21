package application;
	
import database.ArtikelLezer;
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ArtikelLezer lezer = new ArtikelLezer();
		lezer.read("artikel.txt");
		//launch(args);
	}
}

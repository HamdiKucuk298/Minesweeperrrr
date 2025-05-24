package application;

import javafx.fxml.FXML;
import javafx.scene.Parent;

import javafx.scene.Scene;

public class SampleController {

	@FXML
	private void playGame() {
	    GameExe game = new GameExe();//playe basarsak oyuna gecıyor
	    
	    Parent gameRoot = game.createContect();//parent
	    Scene gameScene = new Scene(gameRoot);//ve sonra oyunu sahneye verıyor

	    game.setScene(gameScene);  // Bu satır önemli, sahne referansını veriyoruz.
	    Main.getPrimaryStage().setScene(gameScene);//gereklı uyarı verdı
	    
	    Main.getPrimaryStage().setTitle("Minesweeper Game");//Oyun ısmı
	}



    @FXML
    private void quitGame() {
        System.exit(0);//oyundan dırekt cıkıyor
    }
}

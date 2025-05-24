package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    
	private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        
    	return primaryStage;//gereklıydı kendı ekledı
    }

    @Override
    public void start(Stage stage) throws Exception {//kendi ekledi
        primaryStage = stage;
        
        loadScene("/application/Login.fxml", "Login"); //direkt login ekranını vermıs
    }

    public static void loadScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
           
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            
            primaryStage.setTitle(title);

    primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

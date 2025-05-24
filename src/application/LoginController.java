package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();//Strıng ıle ıstedıgımızı yazıcam

        if ("hamdi".equals(username) && "1907".equals(password)) {//eger bu sartlar dogru ıse Sample ekranını yukler
            
           
        	Main.loadScene("Sample.fxml", "PLAY / QUIT");
        } else {
           
        	
        	messageLabel.setText("Eror!!");
        }
    }
}

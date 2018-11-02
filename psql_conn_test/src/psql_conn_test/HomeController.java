package psql_conn_test;

import java.io.IOException;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	private Button logOut_btn;
	
	private String email = FXMLController.useremail;
	
	private User user = new User(email);
	
	
	@FXML
	private Label welcome_lbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcome_lbl.setAlignment(Pos.CENTER);
		welcome_lbl.setText("Welcome " + user.getName());	
	} 
	
	@FXML
	public void logOut(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("GettingStarted.fxml"));
		Scene scene = new Scene(parent);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		window.setScene(scene);
		window.show();
	}
	
	
	

}

package psql_conn_test;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLController implements Initializable {
	
	@FXML
	private Button login_btn1, joinUs_btn1, login_btn2, singup_btn1, signUp_btn2, login_btn3;
	@FXML
	private TextField email_tf1, pass_tf1, email_tf3;
	@FXML
	private AnchorPane welcome_pane, login_pane1, joinUs_pane1, login_form1, signup_form1, forgotPass_pane1;
	@FXML
	private Text forgot_pass_txt;
	@FXML
	private ImageView back_btn1;
	
	@FXML
	private void joinUs(ActionEvent event) {
		login_pane1.setVisible(true);
		signup_form1.setVisible(true);
		welcome_pane.setVisible(false);
	}
	
	@FXML
	private void logIn(ActionEvent event) {
		joinUs_pane1.setVisible(true);
		login_form1.setVisible(true);
		welcome_pane.setVisible(false);
	}
	
	@FXML
	private void joinUs2(ActionEvent event) {
		login_pane1.setVisible(true);
		signup_form1.setVisible(true);
		joinUs_pane1.setVisible(false);
		login_form1.setVisible(false);
	}
	
	@FXML
	private void logIn2(ActionEvent event) {
		joinUs_pane1.setVisible(true);
		login_form1.setVisible(true);
		login_pane1.setVisible(false);
		signup_form1.setVisible(false);
	}
	
	@FXML
	private void forgotPass(MouseEvent event) {
		joinUs_pane1.setVisible(false);
		login_form1.setVisible(false);
		forgotPass_pane1.setVisible(true);
	}
	
	@FXML
	private void forgotPassBack(MouseEvent event) {
		joinUs_pane1.setVisible(true);
		login_form1.setVisible(true);
		forgotPass_pane1.setVisible(false);
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcome_pane.setVisible(true);
		login_pane1.setVisible(false);
		joinUs_pane1.setVisible(false);
		login_form1.setVisible(false);
		signup_form1.setVisible(false);
		forgotPass_pane1.setVisible(false);
		email_tf1.setFocusTraversable(false);
		pass_tf1.setFocusTraversable(false);
		email_tf3.setFocusTraversable(false);
	}

}

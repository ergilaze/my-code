package psql_conn_test;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginContext;
import javax.xml.stream.events.StartDocument;

import org.mindrot.jbcrypt.BCrypt;
import org.omg.CORBA.PRIVATE_MEMBER;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;

public class FXMLController implements Initializable {
	
	@FXML
	private Button login_btn1, joinUs_btn1, login_btn2, singup_btn1, signUp_btn2, login_btn3;
	@FXML
	public TextField email_tf1, pass_tf1, email_tf3, name_tf1, lastname_tf1, email_tf2, pass_tf2, confirmPass_tf1;
	@FXML
	private AnchorPane welcome_pane, login_pane1, joinUs_pane1, login_form1, signup_form1, forgotPass_pane1;
	@FXML
	private Text forgot_pass_txt;
	@FXML
	private ImageView back_btn1;
	@FXML
	private Label signUp_lbl, logIn_lbl;

	private DBConnection dc;
	java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
	public static String useremail;
	
	
	@FXML
	private void goTojoinUs(ActionEvent event) {
		login_pane1.setVisible(true);
		signup_form1.setVisible(true);
		welcome_pane.setVisible(false);
	}
	
	@FXML
	private void goTologIn(ActionEvent event) {
		joinUs_pane1.setVisible(true);
		login_form1.setVisible(true);
		welcome_pane.setVisible(false);
	}
	
	@FXML
	private void goTojoinUs2(ActionEvent event) {
		login_pane1.setVisible(true);
		signup_form1.setVisible(true);
		joinUs_pane1.setVisible(false);
		login_form1.setVisible(false);
	}
	
	@FXML
	private void goTologIn2(ActionEvent event) {
		joinUs_pane1.setVisible(true);
		login_form1.setVisible(true);
		login_pane1.setVisible(false);
		signup_form1.setVisible(false);
	}
	
	@FXML
	private void goToforgotPass(MouseEvent event) {
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
	
	@FXML
	private void signUp(ActionEvent event){
		String name = name_tf1.getText();
		String lastname = lastname_tf1.getText();
		String email = email_tf2.getText();
		String password = pass_tf2.getText();
		String password_hashed = BCrypt.hashpw(pass_tf2.getText(), BCrypt.gensalt());
		String confirmPass = confirmPass_tf1.getText();
		if(!name.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPass.isEmpty()) {
			if(!isEmailRegistered(email)) {
				if(password.equals(confirmPass)) {
					try {
						Connection connection = dc.Connect();
						ResultSet rSet = connection.createStatement().executeQuery("INSERT INTO user_db (email, hashed_password, name, last_name, created_at) "
								                                                 + "VALUES ('" + email + "', '" + password_hashed + "', '" + name + "', '" + lastname + "', '" + timestamp + "')");
					} catch (SQLException e) {
						System.err.println(e);
					}
					signUp_lbl.setVisible(false);
				} else {
					signUp_lbl.setText("Passwords do not match");
				}
			} else {
				signUp_lbl.setText("This email is already registered");
			}
		} else {
			signUp_lbl.setText("Please complete the missing field(s)");
		}
	}
	
	@FXML
	private void logIn(ActionEvent event) throws IOException, SQLException {
		String email = email_tf1.getText();
		useremail = email;
		String password = pass_tf1.getText();
		
		if(!email.isEmpty() && !password.isEmpty()) {
			if (isUserRegistered(email, password)) {
				Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
				Scene scene = new Scene(parent);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();

			} else {
				logIn_lbl.setText("Wrong email or password");
			}
		} else {
			logIn_lbl.setText("Please complete the missing field(s)");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc = new DBConnection();
		welcome_pane.setVisible(true);
		login_pane1.setVisible(false);
		joinUs_pane1.setVisible(false);
		login_form1.setVisible(false);
		signup_form1.setVisible(false);
		forgotPass_pane1.setVisible(false);
		email_tf1.setFocusTraversable(false);
		pass_tf1.setFocusTraversable(false);
		email_tf3.setFocusTraversable(false);
		signUp_lbl.setAlignment(Pos.CENTER);
		logIn_lbl.setAlignment(Pos.CENTER);
	}

	public boolean isEmailRegistered(String email){
		try {
			Connection connection = dc.Connect();
			ResultSet resultSet = null;
			resultSet = connection.createStatement().executeQuery("SELECT email FROM user_db WHERE email= '" + email +"'");
			if(resultSet != null) {
				if(resultSet.next()) {
					connection.close();
					resultSet.close();
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	public boolean isUserRegistered(String email, String password) {
		try {
			Connection connection = dc.Connect();
			ResultSet resultSet = null;
			resultSet = connection.createStatement().executeQuery("SELECT email, hashed_password FROM user_db WHERE email= '" + email +"'");
			if(resultSet != null) {
				if(resultSet.next()) {
					String email_db = resultSet.getString(1);
					String pass_db = resultSet.getString(2);
					if(BCrypt.checkpw(password, pass_db)) {
						connection.close();
						resultSet.close();
						return true;
					} else {
						return false;
					}
					
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}
	
	public String getNameWithEmail(String email) {
		String name = "";
		try {
			Connection connection = dc.Connect();
			ResultSet resultSet = null;
			resultSet = connection.createStatement().executeQuery("SELECT name FROM user_db WHERE email= '" + email +"'");
			if(resultSet.next()) {
				name += resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return name;
	}
	
}

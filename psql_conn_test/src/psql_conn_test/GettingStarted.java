package psql_conn_test;

import java.awt.BorderLayout;
import java.awt.Color;
import javafx.geometry.Rectangle2D;
import java.io.IOException;

import com.sun.prism.paint.Paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GettingStarted extends Application {
	
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("GettingStarted.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setMinHeight(360);
		stage.setMinWidth(510);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	


    public static void main(String[] args) {
        launch(args);
    }
}

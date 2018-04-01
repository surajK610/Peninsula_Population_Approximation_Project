package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {

		Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/background.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("KoolAid");

		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	  private Circle createCircle() {
	    final Circle circle = new Circle(200, 150, 2, Color.BLUEVIOLET);
	    circle.setOpacity(0.7);
	    return circle;
	  }
	  
	  
}

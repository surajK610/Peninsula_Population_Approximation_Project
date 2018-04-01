
package application;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BGController
{
	@FXML
	Pane pane;
	
	private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
	private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);
	ArrayList<Circle> circleList = new ArrayList<Circle>();
	private static final int CIRCLES = 500;
	Random r = new Random();
	ArrayList<Cluster> clusterList = new ArrayList<Cluster>();

	public  void genClusterList() // CHANGE THIS AS NECESSARY
	{
		//		clusterList.add(new Cluster(100, 200, 150, 225));
		//		clusterList.add(new Cluster(300, 400, 400, 500));
		clusterList.add(new Cluster(100, 100, 100));
	}

	public void initialize()
	{
		System.out.println("init");
		ArrayList<TranslateTransition> transitionList = new ArrayList<TranslateTransition>();
		Group group = new Group();
		int[] point = new int[2];

		genClusterList();

		for (int i = 0; i<CIRCLES; i++)
		{

			point = clusterList.get(r.nextInt(clusterList.size())).getPoint();
			Circle circle =  createCircle(point[0], point[1]);
			circleList.add(circle);
			TranslateTransition transition = createTranslateTransition(circle);
			transitionList.add(transition);

			group.getChildren().add(circle);
			pane.getChildren().add(group);
		}

//		StackPane root = new StackPane();
//
//		root.setId("pane");

		final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
		moveCircleOnKeyPress(scene);

		for (int i = 0; i<CIRCLES; i++)
		{

			moveCircleOnMousePress(scene, transitionList.get(i));
		}

//		scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());

		//		stage.setScene(scene);
		//		stage.show();
	}


	private Label createInstructions() {
		Label instructions = new Label(
				"Use the arrow keys to move the circle in small increments\n" +
						"Click the mouse to move the circle to a given location\n" +
						"Ctrl + Click the mouse to slowly translate the circle to a given location"      
				);
		instructions.setTextFill(Color.FORESTGREEN);
		return instructions;
	}

	private Circle createCircle() {
		final Circle circle = new Circle(200, 150, 2, Color.BLUEVIOLET);
		circle.setOpacity(0.7);
		return circle;
	}

	private Circle createCircle(int x, int y) {
		final Circle circle = new Circle(x, y, 2, Color.BLUEVIOLET);
		circle.setOpacity(0.7);
		return circle;
	}

	private TranslateTransition createTranslateTransition(final Circle circle) {
		final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
		transition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
				circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
				circle.setTranslateX(0);
				circle.setTranslateY(0);
			}
		});
		return transition;
	}

	private void moveCircleOnKeyPress(Scene scene)
	{

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override public void handle(KeyEvent event)
			{
				System.out.println("sup");
				switch (event.getCode()) 
				{
				case DOWN:
					for (Circle circle: circleList)
					{
						circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA);
					}
					; 
					break;
				case LEFT: 
					for (Circle circle: circleList)
					{circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA);}; break;
				case UP:  
					for (Circle circle: circleList)
					{circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA);}; break;
				case RIGHT:  
					for (Circle circle: circleList)
					{circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA);}; break;
				case A:
					System.out.println("lol");
					for (Circle circle: circleList)
					{
						TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
						transition.setOnFinished(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent t) {
								circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
								circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
								circle.setTranslateX(0);
								circle.setTranslateY(0);
							}});
						//						transition.setToX(event.getSceneX() - circle.getCenterX());
						//						transition.setToY(event.getSceneY() - circle.getCenterY());
						transition.playFromStart();
					}
					break;
				default:
					System.out.println("damn");
					break;
				}
			}


		});

	}

	private void moveCircleOnMousePress(Scene scene, final TranslateTransition transition) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				if (!event.isControlDown()) {
					for (Circle circle: circleList)
					{
						circle.setCenterX(event.getSceneX());
						circle.setCenterY(event.getSceneY());
					}
				} else {
					for (Circle circle: circleList)
					{
						
						
						TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
						transition.setOnFinished(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent t) {
								circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
								circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
								circle.setTranslateX(0);
								circle.setTranslateY(0);
							}});
						
						transition.setToX(r.nextInt(600) - circle.getCenterX());
						transition.setToY(r.nextInt(400) - circle.getCenterY());
						transition.playFromStart();
					}
				}  
			}
		});
	}


}
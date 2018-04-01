package application;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;




public class MovementEventsDemo extends Application {
	private static final int      KEYBOARD_MOVEMENT_DELTA = 2;
	private static final Double TRANSLATE_DURATION_DOUBLE = 0.5;
	private static final Duration TRANSLATE_DURATION      = Duration.seconds(TRANSLATE_DURATION_DOUBLE);
	private static final Duration TRANSLATE_DURATION_HALF      = Duration.seconds(TRANSLATE_DURATION_DOUBLE/2);
	private static final Double POP_RANDOM_VAR = 0.07;

	ArrayList<Circle> circleList = new ArrayList<Circle>();
	ArrayList<Integer> circleClusters = new ArrayList<Integer>();
	private static final int CIRCLES = 2600;
	double S_e = .18, H_e = .16, P_e1 = .09, P_e2 = .04, T_e = .015, e = .03; 
	Random r = new Random();
	ArrayList <Integer> clusterCount = new ArrayList <Integer>();
	int[] point = new int[2];
	int[] waypoint = new int[2];
	int[] waypoint1 = new int[2];
	int[] waypoint2 = new int[2];
	ArrayList<Cluster> clusterList = new ArrayList<Cluster>();
	ArrayList<Waypoint> waypointList = new ArrayList<Waypoint>();
	Integer clusterID = 0;
	private Double specTransDur = 0.5;
	int [] S=  {4, 5, 6, 6, 6, 6, 6, 6, 9, 9, 9, 9, 9, 9};
	int [] P = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	int [] Q = {2, 3, 3};
	int [] H = {7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11};

	int clickCount = 0;

	char oldBuilding = ' '; 
	char newBuilding = ' ';
	
	Cluster field = new Cluster(1016, 3, 1210, 1, 1354, 3, 1270, 95, 'F');

	public void instantiateTabulations()
	{
		clusterCount.clear();
		clusterCount.add((int) ((CIRCLES * S_e)+r.nextInt((int) (CIRCLES*S_e))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * S_e)+r.nextInt((int) (CIRCLES*S_e))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * H_e)+r.nextInt((int) (CIRCLES*H_e))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * H_e)+r.nextInt((int) (CIRCLES*H_e))*POP_RANDOM_VAR));

		clusterCount.add((int) ((CIRCLES * P_e1)+r.nextInt((int) (CIRCLES*P_e1))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * P_e1)+r.nextInt((int) (CIRCLES*P_e1))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * P_e2)+r.nextInt((int) (CIRCLES*P_e2))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * P_e2)+r.nextInt((int) (CIRCLES*P_e2))*POP_RANDOM_VAR));

		clusterCount.add((int) ((CIRCLES * T_e)+r.nextInt((int) (CIRCLES*T_e))*POP_RANDOM_VAR));
		clusterCount.add((int) ((CIRCLES * T_e)+r.nextInt((int) (CIRCLES*T_e))*POP_RANDOM_VAR));
		clusterCount.add((int) (CIRCLES * e));
		
		int sum = 0;
		for (int i : clusterCount)
		{
			sum += i;
		}
		if (sum > CIRCLES)
		{
			int diff = sum - CIRCLES;
			for (int j = 0; j < diff; j++)
			{
				int rand = r.nextInt(clusterCount.size());
				while (clusterCount.get(rand)<=0)
				{
					 rand = r.nextInt(clusterCount.size());
				}
				clusterCount.set(rand, clusterCount.get(rand) - 1);
			}
		}
		else if (sum < CIRCLES)
		{
			int diff = CIRCLES - sum;
			for (int j = 0; j < diff; j++)
			{
				int rand = r.nextInt(clusterCount.size());
				while (clusterCount.get(rand)<=0)
				{
					 rand = r.nextInt(clusterCount.size());
				}
				clusterCount.set(rand, clusterCount.get(rand)+  1);
			}
		}
		sum = 0;
		for (int i : clusterCount)
		{
			sum += i;
		}
		System.out.println(sum);
		
		
		//		if (r.nextInt(2)==0)
		//		{
		//			clusterCount.add(11);
		//			clusterCount.add(114);
		//			clusterCount.add(9);
		//			clusterCount.add(116);
		//
		//		}
		//		else
		//		{
		//			clusterCount.add(114);
		//			clusterCount.add(11);
		//			clusterCount.add(116);
		//			clusterCount.add(9);
		//
		//		}

	}

	public  void genClusterList() // CHANGE THIS AS NECESSARY
	{
		clusterList.add(new Cluster(205, 445, 375, 455, 330, 765, 155, 755, 'S'));
		clusterList.add(new Cluster(400, 480, 570, 495, 525, 800, 355, 790, 's'));

		clusterList.add(new Cluster(985, 605, 1100, 690, 765, 890, 640, 820, 'h'));
		clusterList.add(new Cluster(1210, 640, 1325, 725, 1000, 900, 875, 860, 'H'));

		clusterList.add(new Cluster(940, 550, 1050, 480, 1210, 595, 1100, 660, 'p'));
		clusterList.add(new Cluster(1355, 560, 1520, 670, 1400, 740, 1240, 620, 'q'));


		clusterList.add(new Cluster(915, 380, 1040, 380, 1030, 465, 915, 465, 'P'));
		clusterList.add(new Cluster(1080, 375, 1200, 375, 1200, 465, 1080, 460, 'Q'));

		clusterList.add(new Cluster(790, 30, 830, 30, 825, 100, 790, 100, 't'));
		clusterList.add(new Cluster(850, 30, 885, 30, 885, 105, 850, 105, 'T'));

		clusterList.add(new Cluster(0, 0, 1600, 0, 1600, 900, 0, 900, 'W'));


		ArrayList<Cluster> buildingS = new ArrayList<Cluster>();
		buildingS.add(clusterList.get(0));
		buildingS.add(clusterList.get(1));
		ArrayList<Cluster> buildingH = new ArrayList<Cluster>();
		buildingH.add(clusterList.get(2));
		buildingH.add(clusterList.get(3));
	}



	public void genWaypointList()
	{
		waypointList.add(new Waypoint(1145, 370, 5, "P1S1")); // 0
		waypointList.add(new Waypoint(1150, 460, 10, "P1S2")); // 1
		waypointList.add(new Waypoint(1455, 715, 5, "P2S1")); // 2
		waypointList.add(new Waypoint(1300, 580, 10, "P2S2")); // 3
		waypointList.add(new Waypoint(285, 450, 3, "S1S1")); // 4
		waypointList.add(new Waypoint(235, 760, 5, "S1S2")); // 5
		waypointList.add(new Waypoint(340, 680, 15, "S1S3")); // 6
		waypointList.add(new Waypoint(1260, 670, 5, "H1S1")); // 7
		waypointList.add(new Waypoint(910, 893, 5, "H1S1")); // 8
		waypointList.add(new Waypoint(360, 540, 15, "S1S4")); // 9
		waypointList.add(new Waypoint(1060, 888, 5, "H1S3")); // 10
		waypointList.add(new Waypoint(1160, 680, 5, "H1S4")); // 11
	}

	public static void main(String[] args) { launch(args); }

	@Override public void start(Stage stage) throws Exception {

		ArrayList<TranslateTransition> transitionList = new ArrayList<TranslateTransition>();
		Group group = new Group();
		point = new int[2];
		Integer clusterID = 0;


		Image image = new Image ("application/PVPHS Campus Map Jul2016-page-001.jpg.jpg");
		ImageView iv2 = new ImageView();
		iv2.setImage(image);
		iv2.setFitWidth(1600);
		iv2.setFitHeight(900);
		iv2.setSmooth(true);
		iv2.setCache(true);



		HBox box = new HBox();
		box.getChildren().add(iv2);
		group.getChildren().add(box);

		genClusterList();
		genWaypointList();


		instantiateTabulations();

		clickCount++;
		System.out.println("Click #" + clickCount);
		for (int i = 0; i<clusterCount.size(); i++)
		{
			for (int j = 0; j < clusterCount.get(i); j ++)
			{
				clusterID = i;
				point = clusterList.get(i).getPoint();
				Circle circle =  createCircle(point[0], point[1]);
				circleList.add(circle);
				circleClusters.add(clusterID);
				TranslateTransition transition = createTranslateTransition(circle);
				transitionList.add(transition);
				group.getChildren().add(circle);
			}
			System.out.println( clusterList.get(i) + " has " + clusterCount.get(i) + " circles");
		}
		System.out.println();


		StackPane root = new StackPane();

		root.setId("pane");

		final Scene scene = new Scene(group, 600, 400, Color.CORNSILK);
		moveCircleOnKeyPress(scene);

		for (int i = 0; i<CIRCLES; i++)
		{

			moveCircleOnMousePress(scene, transitionList.get(i));
		}

		scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());

		stage.setScene(scene);
		stage.show();
	}


	private Circle createCircle(int x, int y) {
		//		ArrayList<Color> colorList = new ArrayList<Color>();
		//		colorList.add(Color.BLUEVIOLET);
		//		colorList.add(Color.GREEN);
		//		colorList.add(Color.PURPLE);
		//		colorList.add(Color.BLUE);
		//		final Circle circle = new Circle(x, y, 2, colorList.get(r.nextInt(colorList.size())));

		final Circle circle = new Circle (x, y, 2, Color.rgb( r.nextInt(255) ,r.nextInt(255),r.nextInt(255)));
		circle.setOpacity(1.0);
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
				default:
					break;
				}
			}
		});

	}


	private void moveCircleOnMousePress(Scene scene, final TranslateTransition transition)
	{

		scene.setOnMousePressed(new EventHandler<MouseEvent>() 
		{
			@Override public void handle(MouseEvent event) 
			{
				clickCount++;
				System.out.println("Click #" + clickCount);


				if(event.isAltDown())
				{
					System.out.println("field");
					for (Circle circle: circleList)
					{
						point = field.getPoint();
						TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
						transition.setOnFinished(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent t) {
								circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
								circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
								circle.setTranslateX(0);
								circle.setTranslateY(0);
							}});
						transition.setToX(point[0]-circle.getCenterX());
						transition.setToY(point[1]-circle.getCenterY());
						specTransDur=TRANSLATE_DURATION_DOUBLE*(2*r.nextDouble()+3);
						transition.setDuration(Duration.seconds(specTransDur));
						transition.playFromStart();
					}
				}
				else if (!event.isControlDown()) 
				{
					for (Circle circle: circleList)
					{
						circle.setCenterX(event.getSceneX());
						circle.setCenterY(event.getSceneY());
					}
				} 
					
				else
				{

					ArrayList<Integer> suraj = new ArrayList<Integer>();
					Integer circleCount = 0;
					int k = -1;
					for (Integer i = 0; i < CIRCLES; i++)
					{
						suraj.add(i);
					}
					instantiateTabulations();

					for (int i = 0; i<clusterCount.size(); i++)
					{
						clusterID = i;
						for (int j = 0; j < clusterCount.get(i); j ++)
						{
							k++;

							circleCount= suraj.remove(r.nextInt(suraj.size()));
							Circle circle = circleList.get(circleCount);

							point = clusterList.get(clusterID).getPoint(); // new point to move to

							oldBuilding = clusterList.get(circleClusters.get(circleCount)).building;
							newBuilding = clusterList.get(clusterID).building;

							circleClusters.set(circleCount, clusterID);
													
//							System.out.println(clusterList.get(circleClusters.get(circleCount)).building);
//							System.out.println(oldBuilding);
							
							 clusterList.get(circleClusters.get(circleCount)).building = newBuilding; // broken
							
//							System.out.println(clusterList.get(circleClusters.get(circleCount)).building);
//							System.out.println(newBuilding);
//							
							
							if (oldBuilding==newBuilding)
							{
								TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
								transition.setOnFinished(new EventHandler<ActionEvent>() {
									@Override public void handle(ActionEvent t) {
										circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
										circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
										circle.setTranslateX(0);
										circle.setTranslateY(0);
									}});
								transition.setToX(point[0]-circle.getCenterX());
								transition.setToY(point[1]-circle.getCenterY());
								specTransDur=TRANSLATE_DURATION_DOUBLE*(2*r.nextDouble()+3);
								transition.setDuration(Duration.seconds(specTransDur));
								transition.playFromStart();
							}
							else 
							{
								SequentialTransition seqT = createTransition( oldBuilding,  newBuilding,  circle, point);
								if (seqT != null)
									seqT.playFromStart();
							}
						}
						System.out.println(clusterList.get(i)+" has " + clusterCount.get(i) + " circles");


					}

				}
				System.out.println();
			};
		});
	}

	public SequentialTransition createTransition(char oldBuilding, char newBuilding, Circle circle, int[] point)
	{


		String oldBuildingChar = new String (oldBuilding + "");
		String newBuildingChar = new String (newBuilding + "");
//		System.out.println(oldBuildingChar);
//		System.out.println(newBuildingChar);
 
		if (Character.toLowerCase(oldBuildingChar.charAt(0)) == Character.toLowerCase(newBuildingChar.charAt(0)))
//		if (oldBuilding == newBuilding)
		{
			// same building -- one waypoint
//			System.out.println("piranha");
			switch (oldBuilding)
			{
			case 's':
				waypoint1 = waypointList.get(6).getPoint();
				break;
			case 'S':
				waypoint1 = waypointList.get(4).getPoint();
				break;
			case 'h':
				waypoint1 = waypointList.get(8).getPoint();
				break;
			case 'H':
				waypoint1 = waypointList.get(8).getPoint();
				break;
			case 'P':
				waypoint1 = waypointList.get(0).getPoint();
				break;
			case 'p':
				waypoint1 = waypointList.get(1).getPoint();
				break;
			case 'Q':
				waypoint1 = waypointList.get(3).getPoint();
				break;
			case 'q':
				waypoint1 = waypointList.get(3).getPoint();
				break;
//			default:
//				waypoint1 = waypointList.get(0).getPoint();
			}
			TranslateTransition firstTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
			firstTransition.setToX(waypoint1[0]-circle.getCenterX());
			firstTransition.setToY(waypoint1[1]-circle.getCenterY());
			specTransDur=TRANSLATE_DURATION_DOUBLE*(1*r.nextDouble()+1.5);
			firstTransition.setDuration(Duration.seconds(specTransDur));

			TranslateTransition secondTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
			secondTransition.setToX(point[0]-circle.getCenterX());
			secondTransition.setToY(point[1]-circle.getCenterY());
			secondTransition.setDuration(Duration.seconds(specTransDur));
			// create new transition
			SequentialTransition seqT = new SequentialTransition (firstTransition, secondTransition);
			return seqT;
		}
		else if (oldBuilding >= 'A' && newBuilding >= 'A')
		{

			
			switch (oldBuilding)
			{
			case 'S':
				waypoint1 = waypointList.get(S[r.nextInt(S.length)]).getPoint();
				break;
			case 'H':
				waypoint1 = waypointList.get(H[r.nextInt(H.length)]).getPoint();
				break;
			case 'P':
				waypoint1 = waypointList.get(P[r.nextInt(P.length)]).getPoint();
				break;
			case 'Q':
				waypoint1 = waypointList.get(Q[r.nextInt(Q.length)]).getPoint();
				break;
//			default:
//				waypoint1 = waypointList.get(0).getPoint();
			}
			TranslateTransition firstTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
			firstTransition.setToX(waypoint1[0]-circle.getCenterX());
			firstTransition.setToY(waypoint1[1]-circle.getCenterY());
			specTransDur=TRANSLATE_DURATION_DOUBLE*(1*r.nextDouble()+1.5);
			firstTransition.setDuration(Duration.seconds(specTransDur));

			TranslateTransition secondTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
			secondTransition.setToX(point[0]-circle.getCenterX());
			secondTransition.setToY(point[1]-circle.getCenterY());
			secondTransition.setDuration(Duration.seconds(specTransDur));
			// create new transition
			SequentialTransition seqT = new SequentialTransition (firstTransition, secondTransition);
			return seqT; 
		}		
		else if (oldBuilding >= 'A' || newBuilding >= 'A')
		{

			
		
				if (oldBuilding >= 'A')
				{
				
					switch (oldBuilding)
					{
					case 'S':
						waypoint1 = waypointList.get(S[r.nextInt(S.length)]).getPoint();
						break;
					case 'H':
						waypoint1 = waypointList.get(H[r.nextInt(H.length)]).getPoint();
						break;
					case 'P':
						waypoint1 = waypointList.get(P[r.nextInt(P.length)]).getPoint();
						break;
					case 'Q':
						waypoint1 = waypointList.get(Q[r.nextInt(Q.length)]).getPoint();
						break;
					}
				}
				else
				{

					switch (newBuilding)
					{
					case 'S':
						waypoint1 = waypointList.get(S[r.nextInt(S.length)]).getPoint();
						break;
					case 'H':
						waypoint1 = waypointList.get(H[r.nextInt(H.length)]).getPoint();
						break;
					case 'P':
						waypoint1 = waypointList.get(P[r.nextInt(P.length)]).getPoint();
						break;
					case 'Q':
						waypoint1 = waypointList.get(Q[r.nextInt(Q.length)]).getPoint();
						break;
					}
				}
				TranslateTransition firstTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
				firstTransition.setToX(waypoint1[0]-circle.getCenterX());
				firstTransition.setToY(waypoint1[1]-circle.getCenterY());
				specTransDur=TRANSLATE_DURATION_DOUBLE*(1*r.nextDouble()+1.5);
				firstTransition.setDuration(Duration.seconds(specTransDur));

				TranslateTransition secondTransition = new TranslateTransition(TRANSLATE_DURATION_HALF, circle);
				secondTransition.setToX(point[0]-circle.getCenterX());
				secondTransition.setToY(point[1]-circle.getCenterY());
				secondTransition.setDuration(Duration.seconds(specTransDur));
				// create new transition
				SequentialTransition seqT = new SequentialTransition (firstTransition, secondTransition);
				return seqT; 
		}
		else
		{

		}

		return null;
	}

}


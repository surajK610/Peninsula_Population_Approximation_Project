package application;

import java.util.Random;

import javafx.scene.shape.Polygon;

public class Cluster {
	private   int      xPosition = 0;
	private   int yPosition = 0;
	private   int radius = 5;
	private int xEnd = 0;
	private int yEnd = 0;
	public char building = ' ';
	Polygon polygon = new Polygon();

	private int type = 0; // 0 point, 1 circle, 2 rectangle, 3 quadrilateral

	Random r = new Random();

	public String toString()
	{
		String b = "";
		if (building >= 'A' && building <= 'Z')
		{
			b =  building + " Building 2nd floor";
		}
		else
		{
			String w = "" + building;
			w = w.toUpperCase();
			b =  w + " Building 1st floor";
		}
		if (b.contains("Q"))
		{
			b = b.substring(1);
			b = "P2" + b;
		}
		return b;
	}
	
	public void setBuilding(char newBuilding)
	{
		 building = newBuilding;
	}
	
	public Cluster (int x2, int y2)
	{
		xPosition = x2;
		yPosition = y2;
		type = 0;
	}

	public Cluster (int x2, int y2, int rad)
	{
		xPosition = x2;
		yPosition = y2;
		radius = rad;
		type = 1;
	}

	public Cluster (int x2, int y2, int x22, int y22, char building2)
	{
		xPosition = x2;
		yPosition = y2;
		type = 2;
		xEnd = x22 + x2;
		yEnd = y22 + y2;
		building = building2;
	}
	

	public Cluster (int x1, int y1, int x2, int y2,
			int x3, int y3, int x4, int y4,
			char building2)
	{

		polygon.getPoints().addAll(new Double[]{
		    (double) x1, (double) y1,
		    (double) x2, (double) y2,
		    (double) x3, (double) y3,
		    (double) x4, (double) y4
		   });
		
		double xMin = Math.min(x1, x2);
		xMin = Math.min(xMin, x3);
		xMin = Math.min(xMin, x4);
		double xMax = Math.max(x1, x2);
		xMax = Math.max(xMax, x3);
		xMax = Math.max(xMax, x4);
		double yMin = Math.min(y1, y2);
		yMin = Math.min(yMin, y3);
		yMin = Math.min(yMin, y4);
		double yMax = Math.max(y1, y2);
		yMax = Math.max(yMax, y3);
		yMax = Math.max(yMax, y4);
		
		xPosition = (int) xMin;
		yPosition = (int) yMin;
		xEnd = (int) xMax;
		yEnd = (int) yMax;
		
		type = 3;

		building = building2;
	}
	
	public int[] getPoint ()
	{
		int[] point = new int[2];

		switch (type)
		{
		case 0:
			point[0] = xPosition;
			point[1] = yPosition;
			break;
		case 1:
//			 double random1 = r.nextDouble();
//			 double random2 = r.nextDouble();
//			 if (random2 < random1)
//			 {
//				 double temp = random2;
//				 random2 = random1;
//				 random1 = temp;
//			 }
//			 point[0] = (int) (radius*random2*Math.cos(Math.PI*random1/random2)+xPosition);
//			 point[1] = (int) (radius*random2*Math.sin(Math.PI*random1/random2)+yPosition);
//			 break;
			
			double t = 2*Math.PI*r.nextDouble();
			double u = r.nextDouble() + r.nextDouble();
			double r2;
			if (u>1)
			{
				r2 = 2-u;
			}
			else
			{
				r2=u;
			}
			point[0]=(int) (radius*r2*Math.cos(t)+xPosition);
			point[1]=(int) (radius*r2*Math.sin(t)+yPosition);
					
			break;
		case 2:
			point[0] = (int) (xPosition + (r.nextDouble())*(xEnd-xPosition));
			point[1] = (int) (yPosition + (r.nextDouble())*(yEnd-yPosition));
//			System.out.println("hi");
			break;
		case 3:
			point[0] = (int) (xPosition + (r.nextDouble())*(xEnd-xPosition));
			point[1] = (int) (yPosition + (r.nextDouble())*(yEnd-yPosition));
			while (!(polygon.contains(point[0], point[1])))
			{
				point[0] = (int) (xPosition + (r.nextDouble())*(xEnd-xPosition));
				point[1] = (int) (yPosition + (r.nextDouble())*(yEnd-yPosition));
			}
			break;
		default:
			point[0] = xPosition;
			point[1] = yPosition;
			break;
		}

		return point;
	}


}
package application;


public class Point {
	private   int      xPosition = 0;
	private   int yPosition = 0;
	private String name = "";



	public Point (int x2, int y2, String name2)
	{
		xPosition = x2;
		yPosition = y2;
		name2 = "";
	}



	public int getX ()
	{
		return xPosition;
	}
	
	public int getY()
	{
		return yPosition;
	}
	
	public String getName()
	{
		return name;
	}


}
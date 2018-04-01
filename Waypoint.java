package application;

import java.util.Random;

public class Waypoint extends Point {


	private int radius = 0;
	private String buildingClosest = "";
	Random r = new Random();


	public Waypoint(int x2, int y2, int rad2, String name2)
	{
		super(x2, y2, name2);
		radius = rad2;
	}

	public Waypoint (int x2, int y2, int rad2, String name2, String buildingClosest)
	{
		super(x2, y2, name2);
		radius = rad2;
		this.buildingClosest = buildingClosest;

	}
	public String getBuildingClosest()
	{
		return buildingClosest;
	}

	public int[] getPoint ()
	{
		int[] point = new int[2];
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
		point[0]=(int) (radius*r2*Math.cos(t)+super.getX());
		point[1]=(int) (radius*r2*Math.sin(t)+super.getY());
		return point;
	}



}

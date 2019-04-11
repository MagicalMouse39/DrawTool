package it.magical.drawtool.ai;

import it.magical.drawtool.Point;
import it.magical.drawtool.shapes.Hexagon;
import it.magical.drawtool.shapes.Rectangle;
import it.magical.drawtool.shapes.Shape;
import it.magical.drawtool.shapes.Triangle;

import java.util.ArrayList;

public class MagicalAI
{
	public static Shape shape;
	
	public static Shape processShape(ArrayList<Point> points)
	{
		if (points.size() == 3)
		{
			Triangle t = new Triangle(points);
			shape = t;
		}
		else if (points.size() == 4)
		{
			Rectangle t = new Rectangle(points);
			shape = t;
		}
		else if (points.size() == 6)
		{
			Hexagon t = new Hexagon(points);
			shape = t;
		}
		else
		{
			Rectangle t = new Rectangle(points);
			shape = t;
		}
		return shape;
	}
}

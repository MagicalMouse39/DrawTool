package it.magical.drawtool.ai;

import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;
import it.magical.drawtool.shapes.*;

import java.util.ArrayList;

public class MagicalAI
{
	public static Triangle.TriangleType getTriangleType(ArrayList<Point> points)
	{
		float diffX = points.get(0).x > points.get(1).x ? points.get(0).x - points.get(1).x : points.get(1).x - points.get(0).x;
		float diffY = points.get(0).y > points.get(1).y ? points.get(0).y - points.get(1).y : points.get(1).y - points.get(0).y;
		float l1 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		diffX = points.get(1).x > points.get(2).x ? points.get(1).x - points.get(2).x : points.get(2).x - points.get(1).x;
		diffY = points.get(1).y > points.get(2).y ? points.get(1).y - points.get(2).y : points.get(2).y - points.get(1).y;
		float l2 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		diffX = points.get(2).x > points.get(0).x ? points.get(2).x - points.get(0).x : points.get(0).x - points.get(2).x;
		diffY = points.get(2).y > points.get(0).y ? points.get(2).y - points.get(0).y : points.get(0).y - points.get(2).y;
		float l3 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		l1 = (int)(l1/10);
		l2 = (int)(l2/10);
		l3 = (int)(l3/10);

		Triangle.TriangleType tt;

		if (l1 == l2 && l2 == l3)
			tt = Triangle.TriangleType.Equilatero;
		else if (l1 == l2 || l2 == l3 || l3 == l1)
			tt = Triangle.TriangleType.Isoscele;
		else
			tt = Triangle.TriangleType.Scaleno;

		return tt;
	}

	public static float[] getTriangleSides(ArrayList<Point> points)
	{
		float diffX = points.get(0).x > points.get(1).x ? points.get(0).x - points.get(1).x : points.get(1).x - points.get(0).x;
		float diffY = points.get(0).y > points.get(1).y ? points.get(0).y - points.get(1).y : points.get(1).y - points.get(0).y;
		float l1 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		diffX = points.get(1).x > points.get(2).x ? points.get(1).x - points.get(2).x : points.get(2).x - points.get(1).x;
		diffY = points.get(1).y > points.get(2).y ? points.get(1).y - points.get(2).y : points.get(2).y - points.get(1).y;
		float l2 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		diffX = points.get(2).x > points.get(0).x ? points.get(2).x - points.get(0).x : points.get(0).x - points.get(2).x;
		diffY = points.get(2).y > points.get(0).y ? points.get(2).y - points.get(0).y : points.get(0).y - points.get(2).y;
		float l3 = (float)Math.sqrt(diffX * diffX + diffY * diffY);

		l1 = (int)(l1/10);
		l2 = (int)(l2/10);
		l3 = (int)(l3/10);

		return new float[] { l1, l2, l3 };
	}

	public static Shape processShape(ArrayList<Point> points)
	{
		if (points.size() == 3) //Triangle Gang
		{
			Triangle.TriangleType tt = getTriangleType(points);

			float l1 = getTriangleSides(points)[0];
			float l3 = getTriangleSides(points)[1];
			float l2 = getTriangleSides(points)[2];

			return new Triangle(points, tt, l1, l2, l3);
		}
		else if (points.size() == 4) //RectangleSquareParallelepipedo Gang
		{
			Point topLeft = points.get(0);
			Point bottomLeft = points.get(0);
			Point topRight = points.get(0);
			Point bottomRight = points.get(0);

			for (Point p : points)
				if (p.x <= topLeft.x && p.y >= topLeft.y)
					topLeft = p;

			for (Point p : points)
				if (p == topLeft)
					continue;
				else if (p.x <= bottomLeft.x && p.y <= bottomLeft.y)
						bottomLeft = p;

			for (Point p : points)
				if (p == topLeft || p == bottomLeft)
					continue;
				else if (p.x >= topRight.x && p.y >= topRight.y)
					topRight = p;

			for (Point p : points)
				if (p == topLeft || p == bottomLeft || p == topRight)
					continue;
				else if (p.x >= bottomRight.x && p.y <= bottomRight.y)
					bottomRight = p;

			float base1 = topRight.x - topLeft.x;
			float base2 = bottomRight.x - bottomLeft.x;
			float height1 = topLeft.y - bottomLeft.y;
			float height2 = topRight.y - bottomRight.y;

			if (base1 == base2 && height1 == height2)
				if (base1 == height1)
					return new Square(points);
				else
					return new Rectangle(points, topLeft, bottomLeft, topRight, bottomRight);
			return new Polygon(points);
		}
		else if (points.size() == 6)
			return new Hexagon(points);
		return new Polygon(points);
	}
}

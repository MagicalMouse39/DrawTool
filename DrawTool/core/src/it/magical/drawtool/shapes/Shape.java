package it.magical.drawtool.shapes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import it.magical.drawtool.Point;

import java.util.ArrayList;

public class Shape
{
	protected ShapeRenderer sr;
	protected BitmapFont font;
	protected float perimeter, area, base, height;
	protected String name;
	protected Point vertex;
	protected ArrayList<Point> points;
	public boolean splitted = false;

	public Shape()
	{
		this.sr = new ShapeRenderer();
		this.font = new BitmapFont();
		this.points = new ArrayList<Point>();
	}

	public Point getPointByPosition(float x, float y)
	{
		for (Point p : this.points)
			if (p.x == x && p.y == y)
				return p;
		return null;
	}

	public float getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(float perimeter) {
		this.perimeter = perimeter;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getBase() {
		return base;
	}

	public void setBase(float base) {
		this.base = base;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void split() {}

	public void onUpdate()
	{
		
	}
}

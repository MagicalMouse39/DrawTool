package it.magical.drawtool;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Point
{
	public static ArrayList<Point> points = new ArrayList<Point>();
	private ShapeRenderer sr;
	public float x, y;
	public int id;
	public Point linkedTo;
	public boolean linked;
	
	public Point(float x, float y, int id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.sr = new ShapeRenderer();
		this.linked = false;
	}
	
	public boolean isLinked()
	{
		return this.linked;
	}
	
	public void setLinkedTo(Point linked)
	{
		this.linked = true;
		this.linkedTo = linked;
	}
	
	public static Point getPointByID(int id)
	{
		for (Point p : points)
			if (p.id == id)
				return p;
		return null;
	}
	
	public static Point getPointByPosition(float x, float y)
	{
		for (Point p : points)
			if (p.x == x && p.y == y)
				return p;
		return null;
	}
	
	public void onUpdate()
	{
		this.sr.begin(ShapeRenderer.ShapeType.Filled);
		this.sr.setColor(Color.YELLOW);
		this.sr.circle(this.x, this.y, 8);
		if (this.linked)
			this.sr.rectLine(this.x, this.y, this.linkedTo.x, this.linkedTo.y, 3);
		else
			this.sr.rectLine(this.x, this.y, DrawTool.instance.getCursor().x + 5, DrawTool.instance.getCursor().y + 5, 3);
		this.sr.end();
	}
}
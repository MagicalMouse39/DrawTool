package it.magical.drawtool.shapes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import it.magical.drawtool.Point;

import java.util.ArrayList;

public class Hexagon extends Shape
{
	public Hexagon(ArrayList<Point> points)
	{
		super();
		this.points = points;
		this.sr = new ShapeRenderer();
		this.font = new BitmapFont();
	}
	
	@Override
	public void onUpdate()
	{

		this.sr.begin(ShapeType.Filled);
		this.sr.end();
	}
}

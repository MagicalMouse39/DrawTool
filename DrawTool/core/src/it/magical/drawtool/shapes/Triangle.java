package it.magical.drawtool.shapes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;

import java.util.ArrayList;

public class Triangle extends Shape
{
	public Triangle(ArrayList<Point> points)
	{
		super();
		this.points = points;
		Point p = this.points.get(0);
		float maxY = 0;
		for (Point pp : this.points)
			if (pp.y > maxY)
			{
				p = pp;
				maxY = pp.y;
			}
		this.vertex = p;
		this.name = "Triangle";
	}

	@Override
	public void onUpdate()
	{
		this.sr.begin(ShapeType.Filled);
		DrawTool.writeCentered(this.name, this.vertex.x, this.vertex.y + 30);
		this.sr.end();
	}
}

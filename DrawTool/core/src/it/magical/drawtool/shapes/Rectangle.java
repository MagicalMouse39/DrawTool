package it.magical.drawtool.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Rectangle extends Shape
{
	public Point topLeft = null;
	public Point bottomLeft = null;
	public Point topRight = null;
	public Point bottomRight = null;
	public float base = 0f;

	public Rectangle(ArrayList<Point> points, Point tl, Point bl, Point tr, Point br)
	{
		this.topLeft = tl;
		this.bottomLeft = bl;
		this.topRight = tr;
		this.bottomRight = br;
		this.sr = new ShapeRenderer();
		this.font = new BitmapFont();
		this.points = new ArrayList<Point>();
		for (Point p : points)
			this.points.add(p);
		this.height = this.topLeft.y - this.bottomLeft.y;
		this.base = this.bottomRight.x - this.bottomLeft.x;
		this.area = this.base * this.height;
		this.perimeter = this.base * 2 + this.height * 2;
		this.name = "Rettangolo [Area: " + this.area + " | Perimetro: " + this.perimeter + "]";
	}
	
	@Override
	public void onUpdate()
	{
		this.sr.begin(ShapeType.Filled);
		this.sr.setColor(Color.RED);
		for (int i = 0; i < this.points.size(); i++)
		{
			Point p = this.points.get(i);
			if (i != 0)
				this.sr.rectLine(p.x, p.y, this.points.get(i - 1).x, this.points.get(i - 1).y, 6);
			else
				this.sr.rectLine(p.x, p.y, this.points.get(this.points.size() - 1).x, this.points.get(this.points.size() - 1).y, 6);
			this.sr.circle(p.x, p.y, 8);
		}
		this.sr.end();

		Point l = this.points.get(0);
		for (Point p : this.points)
			if (p.x < l.x)
				l = p;

		Point r = this.points.get(0);
		for (Point p : this.points)
			if (p.x > r.x)
				r = p;

		Point t = this.points.get(0);
		for (Point p : this.points)
			if (p.y > t.y)
				t = p;

		float mid = l.x + (r.x - l.x) / 2;
		float h20 = t.y + 20;
		DrawTool.writeCentered(this.name, mid, this.splitted ? this.bottomLeft.y - 20 : h20);
	}

	@Override
	public void split()
	{
		Point splitTop = new Point(this.topLeft.x + (this.topRight.x - this.topLeft.x) / 2, this.topLeft.y, 21);
		Point splitBottom = new Point(this.topLeft.x + (this.topRight.x - this.topLeft.x) / 2, this.bottomLeft.y, 22);

		ArrayList<Point> points = new ArrayList<Point>();
		points.add(this.topLeft);
		points.add(this.bottomLeft);
		points.add(splitBottom);
		points.add(splitTop);

		Rectangle r = new Rectangle(points, this.topLeft, this.bottomLeft, splitBottom, splitTop);

		r.splitted = true;

		DrawTool.shapes.add(r);

		points.clear();
		points.add(splitTop);
		points.add(splitBottom);
		points.add(this.bottomRight);
		points.add(this.topRight);

		DrawTool.shapes.add(new Rectangle(points, splitTop, splitBottom, this.bottomRight, this.topRight));
		DrawTool.currentShape = r;
		DrawTool.shapes.remove(this);
	}
}

package it.magical.drawtool.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;
import it.magical.drawtool.ai.MagicalAI;

import java.util.ArrayList;

public class Triangle extends Shape
{
	public enum TriangleType
	{
		Isoscele,
		Scaleno,
		Equilatero
	}
	public TriangleType type;
	public float side1 = 0f;
	public float side2 = 0f;
	public float side3 = 0f;
	public float base = 0f;
	private Point midBase;
	private ArrayList<Point> novertex;

	public Triangle(ArrayList<Point> points, TriangleType type, float s1, float s2, float s3)
	{
		super();
		this.type = type;
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
		this.novertex = new ArrayList<Point>();

		for (Point p : points)
			this.points.add(p);
		Point p = this.points.get(0);

		float maxY = 0;
		for (Point pp : this.points)
			if (pp.y > maxY)
			{
				p = pp;
				maxY = pp.y;
			}
		this.vertex = p;

		ArrayList<Point> novertex = new ArrayList<Point>();

		for (Point pp : this.points)
			if (pp == this.vertex)
				continue;
			else
				novertex.add(pp);

		Point tmp = novertex.get(0);
		novertex.toArray()[0] = novertex.get(1).x < novertex.get(0).x ? novertex.get(1) : novertex.get(0);
		novertex.toArray()[1] = novertex.get(1).x > tmp.x ? novertex.get(1) : tmp;

		Point l = this.points.get(0);
		Point t = this.vertex;
		Point r = this.points.get(0);
		for (Point pp : this.points)
			if (pp.x < l.x && pp != this.vertex)
				l = pp;
		for (Point pp : this.points)
			if (pp.x > r.x && pp != this.vertex)
				r = pp;

		this.points.clear();
		this.points.add(l);
		this.points.add(t);
		this.points.add(r);

		float dx = novertex.get(0).x > novertex.get(1).x ? (novertex.get(0).x - novertex.get(1).x) / 2 : (novertex.get(1).x - novertex.get(0).x) / 2;
		float dy = novertex.get(0).y > novertex.get(1).y ? (novertex.get(0).y - novertex.get(1).y) / 2 : (novertex.get(1).y - novertex.get(0).y) / 2;

		this.base = novertex.get(1).x - novertex.get(0).x;

		this.midBase = new Point(this.points.get(0).x + dx, this.points.get(0).y + dy, 20);
		this.midBase.setLinkedTo(this.vertex);

		Point heightP = new Point(this.vertex.x, this.midBase.y, 21);
		this.height = this.vertex.y - heightP.y;
		this.area = (this.base * this.height) / 2;
		this.perimeter = this.side1 + this.side2 + this.side3;

		this.name = "Triangolo (" + this.type.toString() + ") [Area: " + this.area + " | Perimetro: " + this.perimeter + "]";
		this.novertex = novertex;
	}

	@Override
	public void onUpdate()
	{
		this.sr.begin(ShapeType.Filled);
		this.sr.setColor(Color.CYAN);
		this.sr.rectLine(this.vertex.x, !this.splitted ? this.vertex.y + 25 : this.novertex.get(0).y - 25, this.vertex.x, this.vertex.y, 2);
		this.sr.setColor(Color.RED);
		DrawTool.writeCentered(this.name, this.vertex.x, !this.splitted ? this.vertex.y + 30 : this.novertex.get(0).y - 30);
		for (int i = 0; i < this.points.size(); i++)
		{
			Point p = this.points.get(i);
			if (i != 0)
				this.sr.rectLine(p.x, p.y, this.points.get(i - 1).x, this.points.get(i - 1).y, 6);
			else
				this.sr.rectLine(p.x, p.y, this.points.get(this.points.size() - 1).x, this.points.get(this.points.size() - 1).y, 6);
			this.sr.circle(p.x, p.y, 8);
		}
		this.midBase.onUpdate();
		this.sr.end();
	}

	@Override
	public void split()
	{
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(this.novertex.get(0));
		points.add(this.vertex);
		points.add(this.midBase);

		Triangle t = new Triangle(points, MagicalAI.getTriangleType(points), MagicalAI.getTriangleSides(points)[0], MagicalAI.getTriangleSides(points)[1], MagicalAI.getTriangleSides(points)[2]);
		t.points.get(0).setLinkedTo(t.points.get(1));
		t.points.get(1).setLinkedTo(t.points.get(2));
		t.points.get(2).setLinkedTo(t.points.get(0));
		DrawTool.shapes.add(t);

		points.clear();
		points.add(this.novertex.get(1));
		points.add(this.vertex);
		points.add(this.midBase);

		t = new Triangle(points, MagicalAI.getTriangleType(points), MagicalAI.getTriangleSides(points)[0], MagicalAI.getTriangleSides(points)[1], MagicalAI.getTriangleSides(points)[2]);
		t.splitted = true;
		t.points.get(0).setLinkedTo(t.points.get(1));
		t.points.get(1).setLinkedTo(t.points.get(2));
		t.points.get(2).setLinkedTo(t.points.get(0));
		DrawTool.shapes.add(t);
		DrawTool.shapes.remove(this);
	}
}

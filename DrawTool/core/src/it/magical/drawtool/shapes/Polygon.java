package it.magical.drawtool.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;

import java.util.ArrayList;

public class Polygon extends Shape
{
    public Polygon(ArrayList<Point> points)
    {
        super();
        for (Point p : points)
            this.points.add(p);
        this.name = "Poligono";
    }

    @Override
    public void onUpdate()
    {
        this.sr.begin(ShapeRenderer.ShapeType.Filled);
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
        DrawTool.writeCentered(this.name, mid, h20);
    }
}

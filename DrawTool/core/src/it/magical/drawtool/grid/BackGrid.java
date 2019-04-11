package it.magical.drawtool.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class BackGrid
{
    private static ArrayList<GridPoint> points = new ArrayList<GridPoint>();
    private ShapeRenderer sr = new ShapeRenderer();

    public BackGrid()
    {
        for (int x = 10; x <= Gdx.graphics.getWidth(); x+=20)
            for (int y = 10; y <= Gdx.graphics.getHeight(); y+=20)
                points.add(new GridPoint(x, y));
    }

    public static ArrayList<GridPoint> getPoints()
    {
        return points;
    }

    public void onUpdate()
    {
        for (GridPoint p : this.points)
        {
            this.sr.begin(ShapeRenderer.ShapeType.Filled);
            this.sr.setColor(Color.WHITE);
            this.sr.rect(p.x - p.width / 2, p.y - p.height / 2, p.width, p.height);
            this.sr.end();
        }
    }
}

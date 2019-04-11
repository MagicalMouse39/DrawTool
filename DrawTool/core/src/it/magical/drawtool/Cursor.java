package it.magical.drawtool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import it.magical.drawtool.grid.BackGrid;
import it.magical.drawtool.grid.GridPoint;

public class Cursor
{
    public float x, y;
    private ShapeRenderer sr;

    public Cursor(float x, float y)
    {
        this.x = x;
        this.y = y;
        sr = new ShapeRenderer();
    }

   private void position()
   {
       float nearest = Gdx.graphics.getWidth() * Gdx.graphics.getHeight();
       for (GridPoint p : BackGrid.getPoints())
       {
           float mX = Gdx.input.getX();
           float mY = Gdx.graphics.getHeight() - Gdx.input.getY();
           if (Math.sqrt(Math.pow(mX - p.x, 2) + Math.pow(mY - p.y, 2)) < nearest)
           {
               nearest = (float) Math.sqrt(Math.pow(mX - p.x, 2) + Math.pow(mY - p.y, 2));
               this.x = p.x - 5;
               this.y = p.y - 5;
           }
       }
   }

    public void onUpdate()
    {
        position();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(this.x, this.y, 10, 10);
        sr.end();
    }
}

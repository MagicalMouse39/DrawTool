package it.magical.drawtool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Helper
{
    public boolean died = false;
    private ShapeRenderer sr;
    private BitmapFont font;

    public Helper()
    {
        this.sr = new ShapeRenderer();
        this.font = new BitmapFont();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try { Thread.sleep(5000l); } catch (Exception ex) {}
                died = true;
            }
        }).start();
    }

    public void onUpdate()
    {
        this.sr.begin(ShapeRenderer.ShapeType.Filled);
        this.sr.setColor(new Color(0xab, 0xab, 0xab, .3f));
        float mpx = Gdx.input.getX();
        float mpy = Gdx.graphics.getHeight() - Gdx.input.getY();
        this.sr.rect(mpx, mpy, 230, 50);
        this.sr.end();
        this.sr.begin(ShapeRenderer.ShapeType.Filled);
        DrawTool.write("Premi CANC o C per cancellare\nPremi S per dividere la figura", mpx + 5, mpy + 40);
        this.sr.end();
    }
}

package it.magical.drawtool;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import it.magical.drawtool.grid.BackGrid;
import it.magical.drawtool.Cursor;
import it.magical.drawtool.inputprocessor.MagicalInputProcessor;
import it.magical.drawtool.shapes.Shape;

import java.util.ArrayList;

public class DrawTool extends ApplicationAdapter
{
	public static SpriteBatch batch;
	public static BitmapFont font;
	static BackGrid bg;
	static Cursor cursor;
	public static ArrayList<Shape> shapes = new ArrayList<Shape>();

	public static int pointID = 0;
	public static DrawTool instance;

	public static BackGrid getBackGrid()
	{
		return bg;
	}

	public static Cursor getCursor()
	{
		return cursor;
	}

	@Override
	public void create()
	{
		Gdx.input.setInputProcessor(new MagicalInputProcessor());
		batch = new SpriteBatch();
		font = new BitmapFont();
		bg = new BackGrid();
		cursor = new Cursor(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		instance = this;
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(.33f, .33f, .4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.onUpdate();
		cursor.onUpdate();
		for (Point p : Point.points)
			p.onUpdate();
		for (Shape s : shapes)
			s.onUpdate();
		batch.end();
	}

	public static void write(String text, float x, float y)
	{
		try
		{
			batch.begin();
			font.setColor(Color.RED);
			font.draw(DrawTool.batch, text, x, y);
			batch.end();
		}
		catch (Exception ex)
		{
			batch.end();
			batch.begin();
			font.setColor(Color.RED);
			font.draw(DrawTool.batch, text, x, y);
			batch.end();
			batch.begin();
		}
	}

	public static void writeCentered(String text, float x, float y)
	{
		GlyphLayout gl = new GlyphLayout();
		gl.setText(font, text);
		try
		{
			batch.begin();
			font.setColor(Color.RED);
			font.draw(DrawTool.batch, text, x - gl.width / 2, y);
			batch.end();
		}
		catch (Exception ex)
		{
			batch.end();
			batch.begin();
			font.setColor(Color.RED);
			font.draw(DrawTool.batch, text, x - gl.width / 2, y);
			batch.end();
			batch.begin();
		}
	}

	
	@Override
	public void dispose()
	{
		batch.dispose();
	}
}

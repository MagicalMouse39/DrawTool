package it.magical.drawtool.inputprocessor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import it.magical.drawtool.DrawTool;
import it.magical.drawtool.Point;
import it.magical.drawtool.ai.MagicalAI;

import java.util.ArrayList;

public class MagicalInputProcessor implements InputProcessor {
	@Override
	public boolean touchDown (int x, int y, int pointer, int button)
	{
		float cursorX = DrawTool.instance.getCursor().x + 4, cursorY = DrawTool.instance.getCursor().y + 4;
		if (button == Input.Buttons.LEFT)
		{
			boolean add = true;
			boolean clear = false;
			Point p = new Point(cursorX, cursorY, DrawTool.pointID);
			if (Point.points.size() != 0)
				for (Point pp : Point.points)
					if (pp.x == p.x && pp.y == p.y)
					{
						pp.setLinkedTo(p);
						DrawTool.instance.shapes.add(MagicalAI.processShape(Point.points));
						clear = true;
						DrawTool.pointID = 0;
						add = false;
					}
			if (clear)
				Point.points.clear();
			System.out.println("ID: " + DrawTool.pointID);
			if (DrawTool.pointID != 0)
				Point.getPointByID(DrawTool.pointID - 1).setLinkedTo(p);
			if (add)
				Point.points.add(p);
			if (!clear)
				DrawTool.pointID++;
			return true;     
		}
		if (button == Input.Buttons.RIGHT)
		{
			boolean htr = false;
			Point ptr = null;
			for (Point p : Point.points)
				if (p.x == cursorX && p.y == cursorY)
				{
					htr = true;
					ptr = Point.getPointByPosition(p.x, p.y);
				}
			if (htr)
				Point.points.remove(ptr);
		}
	return false;
}

@Override
public boolean keyDown(int keycode) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean keyUp(int keycode) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean keyTyped(char character) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean touchDragged(int screenX, int screenY, int pointer) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean mouseMoved(int screenX, int screenY) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean scrolled(int amount) {
	// TODO Auto-generated method stub
	return false;
}
}
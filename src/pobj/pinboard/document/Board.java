package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board
{
	private List<Clip> l;
	
	public Board()
	{
		l=new ArrayList<Clip>();
	}
	public List<Clip> getContents()
	{
		return l;
	}
	
	public void addClip(Clip clip)
	{
		l.add(clip);
	}
	
	public void addClip(List<Clip> clip)
	{
		l.addAll(clip);
	}
	
	public void removeClip(Clip clip)
	{
		l.remove(clip);
	}
	public void removeClip(List<Clip> clip)
	{
		l.removeAll(clip);
	}
	public void draw(GraphicsContext gc)
	{
		ClipRect rectblanc=new ClipRect(0, 0,gc.getCanvas().getHeight(),gc.getCanvas().getWidth() , Color.WHITE);
		rectblanc.draw(gc);
		for (Clip c : l)
		{
			c.draw(gc);
		}
	}
}

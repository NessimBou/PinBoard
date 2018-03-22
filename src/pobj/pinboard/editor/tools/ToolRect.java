package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool 
{
	private double x1_press;
	private double y1_press;
	private double x2_release;
	private double y2_release;
	private String name;
	
	public ToolRect()
	{
		x1_press=0.;
		y1_press=0.;
		x2_release=0.;
		y2_release=0.;
		name="ToolRect";
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) 
	{
		x1_press=e.getX();
		y1_press=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) 
	{
		//drawFeedback (i,gc);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) 
	{
		double x2_release=e.getX();
		double y2_release=e.getY();
		//System.out.println(x1_press);
		//System.out.println(y1_press);
		//System.out.println(x2_release);
		//System.out.println(y2_release);
		i.getBoard().addClip(new ClipRect(x1_press, y1_press, x2_release, y2_release, Color.BLUE));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) 
	{
		i.getBoard().draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) 
	{
		return name;
	}

}

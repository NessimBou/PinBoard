package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool
{
	private double x_press;
	private double y_press;
	private double x_release; 
	private double y_release;
	private String name;
	private boolean shift;

	public ToolSelection ()
	{
		x_press =0;
		x_release = 0 ;
		y_press = 0 ;
		y_release = 0;
		name = "ToolSelection";
		shift=false;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) 
	{
		x_press = e.getX();
		y_press = e.getY();
		if (e.isShiftDown())
		{
			i.getSelection().toogleSelect(i.getBoard(),x_press,y_press);
			shift=true;
		}
		else
		{
			//Pour deselectionner
			if (shift)
			{
				i.getSelection().clear();
				shift=false;
			}
			i.getSelection().select(i.getBoard(),x_press,y_press);
		}
	}
	
	@Override
	public void drag(EditorInterface i, MouseEvent e) 
	{
		y_release = e.getY();
		x_release = e.getX();
		for(Clip c : i.getSelection().getContents())
		{
			Command co = new CommandMove(i,c,x_release-x_press,y_release-y_press);
			co.execute();
			i.getCommandStack().addCommand(co);
			//c.move(x_release-x_press, y_release-y_press);
		}
		y_press = e.getY();
		x_press = e.getX();
	}
	
	@Override
	public void release(EditorInterface i, MouseEvent e) 
	{
	}
	
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) 
	{
		i.getBoard().draw(gc);
		i.getSelection().drawFeedBack(gc);
	}

	@Override
	public String getName(EditorInterface editor) 
	{
		return name;
	}
}


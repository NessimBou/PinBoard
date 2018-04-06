package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command
{
	private List<Clip> listtoMove;
	//private EditorInterface editor;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) 
	{
		this.x=x;
		this.y=y;
		listtoMove=new ArrayList<>();
		listtoMove.add(toMove);
		//this.editor=editor;
	}
	
	public CommandMove(EditorInterface editor, List<Clip> toMove,double x, double y)
	{
		this.x=x;
		this.y=y;
		listtoMove=new ArrayList<>();
		listtoMove=toMove;
		//this.editor=editor;
	}
	
	@Override public void execute()
	{
		for (Clip c : listtoMove)
			c.move(x, y);
	}
	
	@Override public void undo()
	{
		for (Clip c : listtoMove)
			c.move(-x,-y);
	}
	
	
}

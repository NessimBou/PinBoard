package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command 
{
	private List<Clip> listtoAdd;
	private EditorInterface editor;
	
	public CommandAdd(EditorInterface editor, Clip toAdd) 
	{
		listtoAdd=new ArrayList<>();
		listtoAdd.add(toAdd);
		this.editor=editor;
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd)
	{
		listtoAdd=new ArrayList<>();
		listtoAdd=toAdd;
		this.editor=editor;
	}
	
	@Override public void execute()
	{
		editor.getBoard().addClip(listtoAdd);
	}
	
	@Override public void undo()
	{
		editor.getBoard().removeClip(listtoAdd);
	}
}
package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command
{
	private List<Clip> listtoGroup;
	private EditorInterface editor;
	//Grossomodo le cg sert de backup pour le undo
	private ClipGroup cg;
	
	public CommandGroup(EditorInterface editor, Clip toGroup) 
	{
		listtoGroup=new ArrayList<>();
		listtoGroup.add(toGroup);
		this.editor=editor;
		cg=new ClipGroup();
	}
	
	public CommandGroup(EditorInterface editor, List<Clip> toGroup)
	{
		listtoGroup=new ArrayList<>();
		listtoGroup=toGroup;
		this.editor=editor;
		cg=new ClipGroup();
	}
	
	@Override public void execute()
	{
		cg=new ClipGroup();
		for (Clip c : listtoGroup)
		{
			cg.addClip(c);
			editor.getBoard().removeClip(c);
		}
		editor.getBoard().addClip(cg);		
	}
	
	@Override public void undo()
	{
		for (Clip c : cg.getClips())
			editor.getBoard().addClip(c);
		editor.getBoard().removeClip(cg);
	}
}

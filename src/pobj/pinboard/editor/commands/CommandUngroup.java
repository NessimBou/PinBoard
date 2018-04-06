package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{
	private List<Clip> listtoUngroup;
	private EditorInterface editor;
	
	public CommandUngroup(EditorInterface editor, Clip toUngroup) 
	{
		listtoUngroup=new ArrayList<>();
		listtoUngroup.add(toUngroup);
		this.editor=editor;
	}
	
	public CommandUngroup(EditorInterface editor, List<Clip> toUngroup)
	{
		listtoUngroup=new ArrayList<>();
		listtoUngroup=toUngroup;
		this.editor=editor;
	}
	
	@Override public void execute()
	{
		for (Clip c : listtoUngroup)
		{
			//Le bail c'est que listtoUngroup est une liste qui peut contenir des ClipRect, ClipOval et des ClipGroup (qui correspond à une liste de clip)
			//Donc là jregarde si c'est un ClipGroup et si oui, jle "décompress"
			if (c instanceof ClipGroup)
			{
				for (Clip d : ((ClipGroup)c).getClips())
					editor.getBoard().addClip(d);
			}
			else
				editor.getBoard().addClip(c);
		}
		editor.getBoard().removeClip(listtoUngroup);		
	}
	
	@Override public void undo()
	{
		for (Clip c : listtoUngroup)
			if (c instanceof ClipGroup)
			{
				for (Clip d : ((ClipGroup)c).getClips())
					editor.getBoard().removeClip(d);
			}
			else
				editor.getBoard().removeClip(c);
		editor.getBoard().addClip(listtoUngroup);
	}
}

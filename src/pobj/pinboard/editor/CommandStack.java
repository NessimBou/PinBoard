package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Board;
import pobj.pinboard.editor.commands.Command;

public class CommandStack implements EditorInterface
{
	private List<Command> UndoStack;
	private List<Command> RedoStack;
	
	public CommandStack()
	{
		UndoStack=new ArrayList<>();
		RedoStack=new ArrayList<>();
	}
	
	public void addCommand(Command cmd)
	{
		UndoStack.add(cmd);
		RedoStack.clear();
	}
	
	public void undo()
	{
		UndoStack.get(UndoStack.size()-1).undo();
		RedoStack.add(UndoStack.get(UndoStack.size()-1));
		UndoStack.remove(UndoStack.size()-1);
	}
	
	public void redo()
	{
		RedoStack.get(RedoStack.size()-1).execute();
		UndoStack.add(RedoStack.get(RedoStack.size()-1));
		RedoStack.remove(RedoStack.size()-1);
	}
	
	public boolean isUndoEmpty()
	{
		if (UndoStack.size()==0)
			return true;
		return false;
	}
	public boolean isRedoEmpty() 
	{
		if (RedoStack.size()==0)
			return true;
		return false;
	}
	@Override
	public CommandStack getUndoStack()
	{
		return this;
	}
	@Override
	public CommandStack getRedoStack()
	{
		return this;
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandStack getCommandStack() {
		// TODO Auto-generated method stub
		return null;
	}
}

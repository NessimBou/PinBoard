package pobj.pinboard.editor;
import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard 
{
	private List<Clip> list;
	private static Clipboard cp;
	private String name="Clipboard";
	private List<ClipboardListener> cbl;
	
	private Clipboard()
	{
		list=new ArrayList<>();
		cbl=new ArrayList<>();
	}
	
	public static Clipboard getInstance()
	{
		if(cp == null)
			cp=new Clipboard();
		return cp;
	}
	
	
	public void copyToClipboard(List<Clip> clips)
	{
		for (Clip c: clips)
			list.add(c.copy());
		for (ClipboardListener cb: cbl)
		{
			cb.clipboardChanged();
		}
	}
	
	
	public List<Clip> copyFromClipboard()
	{
		List<Clip>list_copy=new ArrayList<>();
		for (Clip c : list)
			list_copy.add(c.copy());
		for (ClipboardListener cb: cbl)
			cb.clipboardChanged();
		return list_copy;
	}
	
	public void clear()
	{
		list.clear();
		for (ClipboardListener cb: cbl)
		{
			cb.clipboardChanged();
		}
	}
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addListener(ClipboardListener listener)
	{
		cbl.add(listener);
	}
	
	public void removeListener(ClipboardListener listener)
	{
		cbl.remove(listener);
	}
}
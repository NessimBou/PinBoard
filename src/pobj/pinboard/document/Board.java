package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {

	private List<Clip> list;
	
	public Board(){
		list = new ArrayList<Clip>();
	}
	
	public List<Clip> getContents(){
		return list;
	}
	
	public void addClip(Clip clip){
		list.add(clip);
	}
	
	public void addClip(List<Clip> clip){
//		for(Clip e : clip){
//			addClip(e);
//		}
		list.addAll(clip);
	}
	
	public void removeClip(Clip clip ){
		list.remove(clip);
	}
	
	public void removeClip(List<Clip> clip){
//		for(Clip e : clip){
//			removeClip(e);
//		}
		list.removeAll(clip);
	}
	
	public void draw(GraphicsContext ctx){
		ClipRect rectblanc=new ClipRect(0, 0,ctx.getCanvas().getWidth(),ctx.getCanvas().getHeight() , Color.WHITE);
		rectblanc.draw(ctx);
		for (Clip c : list){
			c.draw(ctx);
		}
	}
}

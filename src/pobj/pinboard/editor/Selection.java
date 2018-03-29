package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {

	private List<Clip> list = new ArrayList<>();

	
	public void select(Board board,double x, double y){
		clear();
		for(Clip e : board.getContents()){
			if(e.isSelected(x, y)){
				list.add(e);
				break;
			}
		}

	
	
	}
	
	public void toogleSelect(Board board, double x,double y){
		
	}
	
	public void clear(){
		list.clear();
	}
	
	public List<Clip> getContents(){
		return list;
	}
	
	public void drawFeedBack(GraphicsContext gc){
		
	}
	
}

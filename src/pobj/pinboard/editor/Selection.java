package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
	
	public void toogleSelect(Board board, double x,double y)
	{
		for(Clip e : board.getContents()){
			if(e.isSelected(x, y))
			{
				if (list.contains(e))
					list.remove(e);
				else
					list.add(e);
			}
		}
	}
	
	public void clear(){
		list.clear();
	}
	
	public List<Clip> getContents(){
		return list;
	}
	
	public void drawFeedBack(GraphicsContext gc)
	{
		gc.setStroke(Color.BLUE);
		double xmin=list.get(0).getLeft();
		double xmax=list.get(0).getLeft();
		double ymin=list.get(0).getTop();
		double ymax=list.get(0).getTop();				
		for (Clip e : list)
		{
			if (e.getLeft()<xmin)
				xmin=e.getLeft();
			if (e.getLeft()>xmax)
				xmax=e.getTop();
			if (e.getTop()<ymin)
				ymin=e.getTop();
			if (e.getTop()>ymax)
				ymax=e.getTop();
				
		}
		gc.strokeRect(xmin,ymin,xmax,ymax);
	}	
}

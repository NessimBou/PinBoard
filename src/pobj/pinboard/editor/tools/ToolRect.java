package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool {
	
	private double x_press;
	private double y_press;
	private double x_release; 
	private double y_release;
	private String name;
	
	public ToolRect(){
		x_press =0;
		x_release = 0 ;
		y_press = 0 ;
		y_release = 0;
		name = "ToolRect";
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x_press = e.getX();
		y_press = e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		y_release = e.getY();
		x_release = e.getX();
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if(x_press > x_release || y_press > y_release){
			Command c=new CommandAdd(i,new ClipRect(e.getX(),e.getY(),x_press,y_press,Color.BLACK));
			c.execute();
			i.getCommandStack().addCommand(c);
			//i.getBoard().addClip(new ClipRect(e.getX(),e.getY(),x_press,y_press,Color.BLACK));
		}else{
			Command c=new CommandAdd(i,new ClipRect(x_press,y_press,e.getX(),e.getY(),Color.BLACK));
			c.execute();
			i.getCommandStack().addCommand(c);
			//i.getBoard().addClip(new ClipRect(x_press,y_press,e.getX(),e.getY(),Color.BLACK));
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		
		if (x_press > x_release && y_press > y_release)
			gc.strokeRect(x_release,y_release,x_press-x_release,y_press-y_release);	 //OK
		
		if (x_press > x_release && y_press < y_release)
			gc.strokeRect(x_release,y_press,x_press-x_release,y_release-y_press);	 //OK
		
		if(x_press < x_release && y_press > y_release)
			gc.strokeRect(x_press,y_release,x_release-x_press,y_press-y_release);	//OK
		
		if(x_press < x_release && y_press < y_release)
			gc.strokeRect(x_press,y_press,(x_release - x_press),(y_release- y_press));  //OK
	}

	@Override
	public String getName(EditorInterface editor) {
		
		return name;
	}
	
	public double getXPress(){
		return x_press;
	}
	
	public double getYPress(){
		return y_press;
	}
	
	public double getXRelease(){
		return x_release;
	}
	
	public double getYRelease(){
		return y_release;
	}	

}

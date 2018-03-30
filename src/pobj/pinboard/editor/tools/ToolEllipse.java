package pobj.pinboard.editor.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool {

	private double x_press ;
	private double y_press  ;
	private double x_release ; 
	private double y_release ;
	private String name;
	
	public ToolEllipse(){
		x_press = 0;
		y_press = 0;
		x_release = 0;
		y_release =0;
		name ="ToolEllipse";
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x_press = e.getX();
		y_press = e.getY();
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		x_release = e.getX();
		y_release = e.getY();

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if(x_press > x_release || y_press > y_release){
			i.getBoard().addClip(new ClipEllipse(e.getX(), e.getY(),x_press, y_press,  Color.RED));
		}else{
			
			i.getBoard().addClip(new ClipEllipse(x_press, y_press, e.getX(), e.getY(), Color.RED));
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLACK);		
		if (x_press > x_release && y_press > y_release)
			gc.strokeOval(((x_release+x_press)/2) - ((x_press-x_release)/2),((y_release+y_press)/2) - ((y_press - y_release)/ 2) ,(x_press - x_release),(y_press - y_release));  //OK
		if (x_press > x_release && y_press < y_release)
			gc.strokeOval(((x_release+x_press)/2) - ((x_press-x_release)/2),((y_press+y_release)/2) - ((y_release - y_press)/ 2) ,(x_press - x_release),(y_release - y_press));  //OK
		if(x_press < x_release && y_press > y_release)
			gc.strokeOval(((x_press+x_release)/2) - ((x_release-x_press)/2),((y_release+y_press)/2) - ((y_press - y_release)/ 2) ,(x_release - x_press),(y_press - y_release));  //OK
		if(x_press < x_release && y_press < y_release)
			gc.strokeOval(((x_press+x_release)/2) - ((x_release-x_press)/2),((y_press+y_release)/2) - ((y_release - y_press)/ 2) ,(x_release - x_press),(y_release - y_press));  //OK
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

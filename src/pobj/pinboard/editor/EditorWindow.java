package pobj.pinboard.editor;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditorWindow implements EditorInterface
{
	private Board board;
	private Tool tool;
	
	public EditorWindow(Stage stage) 
	{
		this.board=new Board();
		
		Menu file=new Menu ("File");
		Menu edit=new Menu ("Edit");
		Menu tools=new Menu ("Tools");
		Button box=new Button ("Box");
		Button ellipse=new Button ("Ellipse");
		Button img=new Button ("Img...");
		MenuItem nouveau= new MenuItem("New");
		MenuItem close= new MenuItem("Close");
		file.getItems().addAll(nouveau,close);
		
		MenuBar mb=new MenuBar(file,edit,tools);
		ToolBar tb= new ToolBar(box,ellipse,img);
		Canvas canvas = new Canvas(500, 500);
		Separator sep= new Separator();
		Label bds= new Label(tool.getName(this));
		VBox vbox = new VBox();
		vbox.getChildren().addAll(mb,tb,canvas,sep,bds);
		stage.setScene(new javafx.scene.Scene(vbox));
	
		
		
		nouveau.setOnAction((e)-> { new EditorWindow(new Stage()); });
		close.setOnAction((e)-> { stage.close(); });
		
		canvas.setOnMousePressed((e)-> { press(e); });
		canvas.setOnMouseDragged((e)-> { drag(e); });
		canvas.setOnMouseReleased((e)-> { release(this,e); });
		
			
		
		//board.addClip(new ClipRect(200, 50, 300, 250, Color.BLACK));
		//board.addClip(new ClipEllipse(100, 150, 250, 270, Color.BLACK));
		
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
	}
	
	public void press (MouseEvent e)
	{
		tool.press(this, e);
	}
	
	public void drag (MouseEvent e)
	{
		tool.drag(this, e);
	}
	
	public void release(EditorInterface i, MouseEvent e)
	{
		tool.release(i, e);
	}

	@Override
	public Board getBoard() {
		return board;
	}
	
	
}

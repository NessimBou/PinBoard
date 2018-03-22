package pobj.pinboard.editor;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
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
		Label bds= new Label("");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(mb,tb,canvas,sep,bds);
		stage.setScene(new javafx.scene.Scene(vbox));
		
		nouveau.setOnAction((e)-> { new EditorWindow(new Stage()); });
		close.setOnAction((e)-> { stage.close(); });
		
		box.setOnAction((e)-> 
		{
			tool=new ToolRect();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(mb,tb,canvas,sep,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		}
		);
		ellipse.setOnAction((e)-> 
		{ 
			tool=new ToolEllipse();
			String name1=tool.getName(this);
			Label bds3= new Label(name1);
			VBox vbox2 = new VBox();
			vbox2.getChildren().addAll(mb,tb,canvas,sep,bds3);
			stage.setScene(new javafx.scene.Scene(vbox2));
		}
		);
		
		canvas.setOnMousePressed((e)->
		{
			press(e);
		}
		);
		canvas.setOnMouseDragged((e)-> 
		{ 
			drag(e);
			board.draw(canvas.getGraphicsContext2D());
		});
		canvas.setOnMouseReleased((e)-> 
		{ 
			release(this,e); 
			board.draw(canvas.getGraphicsContext2D());
		}
		);
		
		//board.addClip(new ClipEllipse(161, 268, 381, 453, Color.BLACK));
		//board.addClip(new ClipRect(200, 50, 300, 250, Color.BLACK));
		//board.addClip(new ClipEllipse(100, 150, 250, 270, Color.BLACK));
		//board.draw(canvas.getGraphicsContext2D());
		//stage.setScene(new javafx.scene.Scene(vbox));
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
		return this.board;
	}
	
	
}

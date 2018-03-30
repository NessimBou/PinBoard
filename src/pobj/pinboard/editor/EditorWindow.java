package pobj.pinboard.editor;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ClipboardListener {

	private Board board;
	private Tool tool;
	private Selection selection;
	private final Canvas canvas = new Canvas(800,400);
	private boolean disable;
	private MenuItem paste = new MenuItem("Paste");
	
	public EditorWindow(Stage stage){
		
		paste.setDisable(true);
		
		Clipboard.getInstance().addListener(this);
		board = new Board();
		selection = new Selection ();
		Menu file = new Menu("File");
		MenuItem nouveau = new MenuItem("New");
		MenuItem fermer = new MenuItem("Close");
		file.getItems().addAll(nouveau,fermer);
		
		Menu Edit = new Menu("Edit");
		MenuItem rectangle = new MenuItem("Rectangle");
		MenuItem Ellipse = new MenuItem("Ellipse");
		MenuItem copy = new MenuItem("Copy");
		
		MenuItem delete = new MenuItem("Delete");
		Edit.getItems().addAll(rectangle,Ellipse,copy,paste,delete);
		
		Menu tools = new Menu("Tools");
		MenuItem tool_select = new MenuItem("Selection");
		tools.getItems().addAll(tool_select);
		MenuBar menubar = new MenuBar(file,Edit,tools);
		
		//Boutons
		Button box = new Button("box");
		Button ellipse = new Button("Ellipse");
		Button img = new Button("Img");
		Button select = new Button("Select");
		ToolBar toolbar = new ToolBar(select,box,ellipse,img);
		
		Separator separator = new Separator();
		Label label = new Label();
		
		//Fonction Annonyme
		nouveau.setOnAction((e) -> { new EditorWindow(new Stage());});
		fermer.setOnAction((e) -> { stage.close();});
		
		select.setOnAction((e) ->
		{ 
			tool = new ToolSelection();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		});
		
		tool_select.setOnAction((e) ->
		{ 
			tool = new ToolSelection();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		});
		
		
		ellipse.setOnAction((e) ->
		{ 
			tool = new ToolEllipse();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		});
		
		box.setOnAction((e) -> 
		{
			tool = new ToolRect();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));

		});
		rectangle.setOnAction((e) -> 
		{
			tool = new ToolRect();
			String name=tool.getName(this);
			Label bds2= new Label("copy");
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		});
		Ellipse.setOnAction((e) -> 
		{ 
			tool = new ToolEllipse();
			String name=tool.getName(this);
			Label bds2= new Label(name);
			VBox vbox1 = new VBox();
			vbox1.getChildren().addAll(menubar,toolbar,canvas,separator,bds2);
			stage.setScene(new javafx.scene.Scene(vbox1));
		});
		
		copy.setOnAction((e) ->
		{ 
			Clipboard.getInstance().copyToClipboard(selection.getContents());
			clipboardChanged();
		});
		
		paste.setOnAction((e) ->
		{
			board.addClip(Clipboard.getInstance().copyFromClipboard());
			clipboardChanged();
		});
		
		delete.setOnAction((e) ->
		{ 
			board.removeClip(selection.getContents());
			clipboardChanged();
		});
		
		
		
		EventHandler<MouseEvent> drag=new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) 
			{
				tool.drag(EditorWindow.this, e);
	            draw();
	            
		   }
		};
	       
	    EventHandler<MouseEvent> press=new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent e) {
	  		   tool.press(EditorWindow.this, e);
	  		   selection.drawFeedBack(canvas.getGraphicsContext2D());
	  	       }
	    };
	    
	    EventHandler<MouseEvent> released= new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent e) {
	        		   tool.release(EditorWindow.this, e);
		        	   board.draw(canvas.getGraphicsContext2D());
	           }
	    };
	    
	    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,drag);
	    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, press);
	    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
		stage.setTitle("Titre");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menubar,toolbar,canvas,separator,label);
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public Tool getTool(){
		return tool;
	}

	@Override
	public Board getBoard() {
		
		return board;
	}
	
	public void draw(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		tool.drawFeedback(this, gc);
	}
	
	public Selection getSelection()
	{
		return selection;
	}

	@Override
	public void clipboardChanged() 
	{
		if ((Clipboard.getInstance().isEmpty())||(selection.getContents().isEmpty()))
			paste.setDisable(true);
		else
			paste.setDisable(false);
	}
}

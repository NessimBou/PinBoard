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

public class EditorWindow implements EditorInterface {

	private Board board;
	private Tool tool;
	private Selection selection;
	//Zone de dessin
	private final Canvas canvas = new Canvas(800,400);
	
	public EditorWindow(Stage stage){
		board = new Board();
		selection = new Selection ();
		
		//Liste des menus et sous-menu
		Menu file = new Menu("File");
		
		MenuItem nouveau = new MenuItem("New");
		MenuItem fermer = new MenuItem("Close");
		
		
		file.getItems().addAll(nouveau,fermer);
		
		
		Menu Edit = new Menu("Edit");
		
		MenuItem rectangle = new MenuItem("Rectangle");
		MenuItem Ellipse = new MenuItem("Ellipse");
		
		
		Edit.getItems().addAll(rectangle,Ellipse);
		
		Menu tools = new Menu("Tools");
		MenuItem tool_select = new MenuItem("Selection");
		tools.getItems().addAll(tool_select);
		
		//Barre de menu
		MenuBar menubar = new MenuBar(file,Edit,tools);
		
		//Liste des boutons
		Button box = new Button("box");
		Button ellipse = new Button("Ellipse");
		Button img = new Button("Img");
		Button select = new Button("Select");
		
		
		//Barre de Bouton
		ToolBar toolbar = new ToolBar(select,box,ellipse,img);
		
		
		
		//Separator
		Separator separator = new Separator();
		
		//Label
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
			Label bds2= new Label(name);
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
	    
	    
//		
//		canvas.setOnMousePressed(press);
//		canvas.setOnMouseDragged(drag);
//		canvas.setOnMouseReleased(released);
//		
	    
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
	

}

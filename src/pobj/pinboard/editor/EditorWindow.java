package pobj.pinboard.editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface {

	private Board board;
	private Tool tool;
	//Zone de dessin
	private final Canvas canvas = new Canvas(800,400);
	
	public EditorWindow(Stage stage){
		board = new Board();

		
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
		
		//Barre de menu
		MenuBar menubar = new MenuBar(file,Edit,tools);
		
		//Liste des boutons
		Button box = new Button("box");
		Button ellipse = new Button("Ellipse");
		Button img = new Button("Img");
		
		
		//Barre de Bouton
		ToolBar toolbar = new ToolBar(box,ellipse,img);
		
		
		
		//Separator
		Separator separator = new Separator();
		
		//Label
		Label label = new Label();
		
		//Fonction Annonyme
		nouveau.setOnAction((e) -> { new EditorWindow(new Stage());});
		fermer.setOnAction((e) -> { stage.close();});
		ellipse.setOnAction((e) -> { tool = new ToolEllipse();});
		box.setOnAction((e) -> {tool = new ToolRect();});
		rectangle.setOnAction((e) -> {tool = new ToolRect();});
		Ellipse.setOnAction((e) -> { tool = new ToolEllipse();});
		
		
		EventHandler<MouseEvent> drag=new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.drag(EditorWindow.this, e);
	            draw(canvas.getGraphicsContext2D());
		   }
		};
	       
	    EventHandler<MouseEvent> press=new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent e) {
	  		   tool.press(EditorWindow.this, e);
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
	
	public void draw(GraphicsContext gc){
		gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		tool.drawFeedback(this, gc);
	}

}

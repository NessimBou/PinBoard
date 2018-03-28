package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {


	public ClipRect(double left,double top, double right, double bottom, Color color){
		this.setLeft(left);
		this.setRight(right);
		this.setTop(top);
		this.setBottom(bottom);
		this.setColor(color);
	}
	
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(this.getColor());
		ctx.fillRect(this.getLeft(), this.getTop(), this.getRight()-this.getLeft(), this.getBottom()-this.getTop());
		
	}


	@Override
	public Clip copy() {
		 Clip clip_copy = new ClipRect(this.getLeft(),this.getTop(), this.getRight(), this.getBottom(), this.getColor());
		 return clip_copy;
	}

}

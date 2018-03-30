package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {

	//Si on garde le constructeur ci-dessous, on ne peut pas dessiner avec x_press<x_release et y_press>y_release
	//public ClipRect(double left,double top, double right, double bottom, Color color)
	//{
	//	this.setLeft(left);
	//	this.setRight(right);
	//	this.setTop(top);
	//	this.setBottom(bottom);
	//	this.setColor(color);
	//}
	
	public ClipRect(double left, double top, double right, double bottom, Color color)
	{
		if ((left > right) && (top>bottom))
		{
			this.setLeft(right);
			this.setTop(bottom);
			this.setRight(left);
			this.setBottom(top);
		}
		if ((left > right) && (top<bottom))
		{
			this.setLeft(right);
			this.setTop(top);
			this.setRight(left);
			this.setBottom(bottom);
		}
		if ((left < right) && (top<bottom))
		{
			this.setLeft(left);
			this.setTop(top);
			this.setRight(right);
			this.setBottom(bottom);
		}
		if ((left < right) && (top>bottom))
		{
			this.setLeft(left);
			this.setTop(bottom);
			this.setRight(right);
			this.setBottom(top);
		}
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

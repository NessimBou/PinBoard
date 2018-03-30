package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	//Si on garde le constructeur ci-dessous, on ne peut pas dessiner avec x_press<x_release et y_press>y_release
	//public ClipEllipse(double left , double top , double right , double bottom , Color color){
	//	this.setBottom(bottom);
	//	this.setLeft(left);
	//	this.setRight(right);
	//	this.setTop(top);
	//	this.setColor(color);
	//}
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color)
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
		ctx.fillOval(this.getLeft(), this.getTop(), this.getRight()-this.getLeft(), this.getBottom()-this.getTop());
		
	}

	@Override
	public Clip copy() {
		Clip clip_copy= new ClipEllipse(this.getLeft(),this.getTop() , this.getRight(),this.getBottom(),this.getColor()); 
		return clip_copy;
	
	}
	
	@Override
	public boolean isSelected(double x, double y){
		double cx = (this.getLeft() + this.getRight()) / 2;
		double cy = (this.getTop()+this.getBottom())/2;
		double rx = (this.getRight() - this.getLeft())/2;
		double ry = (this.getBottom() - this.getTop())/2;
		double somme = Math.pow(((x-cx)/rx), 2) + Math.pow(((y-cy)/ry), 2);
		if (somme <= 1){
			return true;
		}
		return false;
	}
	
}

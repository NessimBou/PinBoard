package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip 
{
	public ClipEllipse(double left, double top, double right, double bottom, Color color)
	{
		this.setLeft(left);
		this.setTop(top);
		this.setRight(right);
		this.setBottom(bottom);
		this.setColor(color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) 
	{
		ctx.setFill(this.getColor());
		ctx.fillOval(this.getLeft(),this.getTop(),this.getRight()-this.getLeft(),this.getBottom()-this.getTop());
	}

	@Override
	public Clip copy() 
	{
		return new ClipEllipse(this.getLeft(),this.getTop(),this.getRight(),this.getBottom(),this.getColor());
	}
	
	@Override
	public boolean isSelected(double x, double y) 
	{
		double cx = (this.getLeft()+this.getRight())/2;
		double cy = (this.getTop()+this.getBottom())/2;
		double rx = (this.getRight()-this.getLeft())/2;
		double ry = (this.getBottom()-this.getTop())/2;
		
		double r1 = (x-cx)/(rx);
		double r2 = (y-cy)/(ry);
		double r12=r1*r1;
		double r22=r2*r2;
		if (r12+r22<=1)
			return true;
		return false;
	}
}

package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip 
{
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public double getTop()
	{
		return top;
	}
	
	public double getLeft()
	{
		return left;
	}
	
	public double getBottom() 
	{
		return bottom;
	}

	public double getRight() 
	{
		return right;
	}
	
	public void setTop(double top)
	{
		this.top=top;
	}
	
	public void setLeft(double left)
	{
		this.left=left;
	}
	
	public void setBottom(double bottom) 
	{
		this.bottom=bottom;
	}

	public void setRight(double right) 
	{
		this.right=right;
	}
	
	
	
	public void setGeometry(double left, double top, double right, double bottom) 
	{
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}
	
	public void move(double x, double y) 
	{
		this.left+=x;
		this.right+=x;
		this.top+=y;
		this.bottom+=y;
	}
	
	public void setColor(Color c) 
	{
		this.color=c;
	}

	public Color getColor() 
	{
		return color;
	}
	
	public boolean isSelected(double x, double y) 
	{
		if ((x>=left)&&(x<=right)&&(y>=top)&&(y<=bottom))
			return true;
		return false;
	}
}

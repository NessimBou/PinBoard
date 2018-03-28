package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip {

	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	

	
	public double getTop() {
		return top; 
	}
	
	public void setTop(double top){
		this.top = top;
	}

	
	public double getLeft() {
		return left;
	}
	
	public void setLeft(double left){
		this.left = left;
	}

	
	public double getBottom() {
		return bottom;
	}
	
	public void setBottom(double bottom){
		this.bottom = bottom;
	}

	
	public double getRight() {
		return right;
	}

	public void setRight(double right){
		this.right = right;
	}
	
	public void setColor(Color c) {
		this.color = c ;
	}

	
	public Color getColor() {
		return color;
	}
	
	
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	
	public void move(double x, double y) {
		right += x;
		left += x;
		top += y;
		bottom += y;
	}

	
	public boolean isSelected(double x, double y) {
		if( (x < left) || (x > right) || (y <top) || (y> bottom)){
			return false;
		}
		return true;
	}

}


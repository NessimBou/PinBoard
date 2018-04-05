package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite{

	private List<Clip> clip;
	
	
	public ClipGroup()
	{
		this.clip=new ArrayList<Clip>();
	}
	
	@Override
	public void draw(GraphicsContext ctx) 
	{
		for (Clip c : clip)
			c.draw(ctx);
	}

	@Override
	public double getTop()
	{
		double min_top=999;
		for (Clip c : clip)
		{
			if (c.getTop()<min_top)
				min_top=c.getTop();
		}
		return min_top;
	}

	@Override
	public double getLeft() 
	{
		double min_left=999;
		for (Clip c : clip)
		{
			if (c.getLeft()<min_left)
				min_left=c.getLeft();
		}
		return min_left;
	}

	@Override
	public double getBottom() 
	{
		double max_bottom=-999;
		for (Clip c : clip)
		{
			if (c.getBottom()>max_bottom)
				max_bottom=c.getBottom();
		}
		return max_bottom;
	}

	@Override
	public double getRight() 
	{
		double max_right=-999;
		for (Clip c : clip)
		{
			if (c.getRight()>max_right)
				max_right=c.getRight();
		}
		return max_right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) 
	{
		double x=0;
		double y=0;
		
		if (left > right)
			x=left-right;
		if (left<=left)
			x=right-left;
		
		if (top > bottom)
			y=top-bottom;
		if (bottom<=top)
			y=bottom-top;
		
		for (Clip c : clip)
		{
			c.move(x,y);
		}
	}
	
	@Override
	public void move(double x, double y) 
	{
		for (Clip c : clip)
			c.move(x,y);
	}

	@Override
	public boolean isSelected(double x, double y) 
	{
		boolean b=true;
		for (Clip c : clip)
		{
			b=c.isSelected(x,y);
			if (!b)
				return false;
		}
		return true;
	}

	@Override
	public void setColor(Color c) 
	{
	}

	@Override
	public Clip copy() 
	{
		ClipGroup c_copy=new ClipGroup();
		for (Clip c : clip)
		{
			c_copy.getClips().add(c.copy());
		}
		return c_copy;
	}

	@Override
	public List<Clip> getClips() 
	{
		return clip;
	}

	@Override
	public void addClip(Clip toAdd) 
	{
		clip.add(toAdd);
	}

	@Override
	public void removeClip(Clip toRemove) 
	{
		clip.remove(toRemove);
	}

}

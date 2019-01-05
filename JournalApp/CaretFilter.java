package JournalApp;

import javax.swing.*;
import javax.swing.text.*;
public class CaretFilter extends  NavigationFilter
{
private int Dot;
private TagManager Manager;
	public CaretFilter(int d) 
	{
		this.Dot = d;
	}
	public CaretFilter( TagManager tagManager)
	{
		Manager = tagManager;
		if(!Manager.isEmpty())
		this.Dot = tagManager.getCurrentCaretDot();
	}
public void moveDot(NavigationFilter.FilterBypass Filter, int dot, Position.Bias Bias)
{
	super.moveDot(Filter, Dot, Bias);
	Filter.moveDot(Dot, Bias);
	System.out.println("Filtering Caret...");
}
public void setDot(NavigationFilter.FilterBypass Filter, int dot, Position.Bias Bias)
{
 	super.setDot(Filter, 20, Bias);
	Filter.setDot(20, Bias);	
	System.out.println("Filtering Caret... " );
}
}

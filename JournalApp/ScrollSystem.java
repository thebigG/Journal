package JournalApp;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ScrollSystem extends JScrollPane {
 
	public ScrollSystem(Component view) 
	{
		super(view);
		setSize(view.getSize());
		this.setBorder(null);
		
		//this.setLayout(new FlowLayout());
		
	}
	public ScrollSystem(Component view, Color c)
	{

		super(view);
		setSize(view.getSize());
		setBackground(c);
		//this.setLayout(new FlowLayout());
	}
	public ScrollSystem(Component view, Color c, int VerticalPolicy, int HorizontalPolicy)
	{
		super(view);
		setSize(view.getSize());
		setBackground(c);
		//this.setLayout(new FlowLayout());
	}

}
package JournalApp;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
public class TagItem  extends JPanel implements Tag, ColorAdjustable, JournalComponent
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String TagContent;
private final int height = 60; 
private Font thisFont;
private int brightness;
private int darkness;
private Color myColor;
private Drawing xIcon;
static final int TagCharLimit = 20;
static final String TagContentLimit = "////////////////////";
	public TagItem(String TagContent, Font f, Color c) 
	{
		this.TagContent = TagContent;
		setThisFont(f);
		setThis();	
		addActionListener();
		myColor = c;
	}
{
//	this.addMouseListener(new MouseAdapter()
//	{
//		public void mouseClicked(MouseEvent e)
//		{
//			System.out.println("Hello, I am a tag item");
//		}
//		
//		
//	});	
}
public void setThis()
{
	this.setLayout(null);
	if(TagContent.toCharArray()[0] == (hash))
	TagContent =  (TagContent);
	else
	TagContent = (hash +TagContent);
//	this.setBackground(JournalKit.getTransParentColor());
//	this.setBackground(Color.BLUE);
	this.setSize(new Dimension(this.getFontMetrics(thisFont).stringWidth(TagContent),height));
	this.setBackground(JournalKit.getTransParentColor());
	//setXIcon();
	//this.setBorder(new LineBorder(Color.black, 1, false));
	//this.setBorder(new LineBorder(Color.black, 1, false));
}
private void setTagContent(String content)
{
	TagContent = content;
}
public String getTagContent()
{
	return TagContent;
}
public TagItem getThis()
{
	return this;
}
public void updateTagManager()
{}
public void setTag(String newTag)
{
	setTagContent(newTag);
	if(TagContent.toCharArray()[0] == hash)
	TagContent =  (TagContent);
	else
	TagContent = (hash +TagContent);	
	this.setSize(new Dimension(TagContent.length() * pxlsPerLetter,height));
	this.setBackground(JournalKit.getTransParentColor());
}
public TagItem getTag()
{
	return this;
}
public void updateTag(String newTag)
{
	setTagContent(newTag);
	if(TagContent.toCharArray()[0] == hash)
	TagContent =  (TagContent);
	else
	TagContent = (hash +TagContent);	
	this.setSize(new Dimension(TagContent.length() * pxlsPerLetter,height));
	
}

public void paint(Graphics g)
{
Graphics2D thisDrawing = ((Graphics2D)(g));
super.paint(thisDrawing);
thisDrawing.setColor(myColor);
thisDrawing.setFont(thisFont);
thisDrawing.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//thisDrawing.drawString(TagContent, 0, (int) ( this.getSize().height/1.5));
thisDrawing.drawString(TagContent, 0, (int) (13));
//System.out.println();
}
public void setThisFont(Font f)
{
	thisFont = f;
}
public Font getThisFont()
{
	return thisFont;
}
public void addActionListener()
{
	this.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("Clicked tag item: size " + ((JPanel)getThis()).getSize()
					+ "My name is: " + getThis().toString() + "\n" + "I am at: " + getThis().getLocation() + "And"
							+ "you clicked at: " + e.getPoint());

			System.out.println("Hello, The mouse has been entered!!");
			//setXIcon();
			if(xIcon!=null)
			{
			xIcon.setVisible(true);
			xIcon.repaint();
			}
		}
//		public void mouseEntered(MouseEvent e)
//		{
//			System.out.println("Hello, The mouse has been entered!!");
//			setXIcon();
//			if(xIcon!=null)
//			xIcon.setVisible(true);
//		}
//		public void mouseExited(MouseEvent e)
//		{
//			System.out.println("hello, you are exitting the mouuse !!!!???/");
//			//((JPanel) getThis()).setBorder(null);
//			if(xIcon!=null)
//			xIcon.setVisible(false);
//		}
	});
}
public void setBrightness(int n)
{
	brightness = n;
}
public void setColor(Color c) 
{
	myColor = c;
}
public Color getColor() 
{
	return myColor;
}
public int getBrightness() 
{
	return brightness;
}
public void setDarkness(int n) 
{
	darkness = n;
}
public void makeDarker()
{
	for(int i = 0;i<darkness;i++)
	{
		myColor.darker();
	}
	this.repaint();
}
public void makeBrighter()
{
	for(int i = 0;i<brightness;i++)
	{
		myColor.brighter();
	}
	this.repaint();
}
public void updateComponentColor() {
	// TODO Auto-generated method stub
	
}
public String toString()
{
	return getTagContent(); 
}
public void setXIcon()
{
	Drawing xIcon = new Drawing(50, 50, 0, 0,Drawing_Type.ImageScaled_Type, new ImageIcon(getClass().getResource("/Images/")).getImage());
	xIcon.setBounds(10,-7, 100, 100);
	this.add(xIcon);
	xIcon.setVisible(true);
	xIcon.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("<><><><><><><><<>><<><><><> Hello I am the X Icon");
		}
	});
}
}


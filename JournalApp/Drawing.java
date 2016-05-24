package JournalApp;

import ImageUtil.CroppedImage;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
public class Drawing extends JPanel implements Utility, Serializable
{
private Drawing_Type Shape;
private int Width;
private int Height;
private ImageIcon image;
private Image ScaledImage;
private CroppedImage croppedImage;
private int x1;
private int y1;
private int x2;
private int y2;
private Color color;
private JTextPane textManager;
private String drawText;
private transient Graphics2D thisDrawing;
private Font thisFont;
private int PicAreaWidth;
private int PicAreaHeight;
private JPanel ImageMenu;
private Drawing XIcon;

public Drawing(int w, int h, Drawing_Type s, Color color, JTextPane textManager)
{
	this.textManager = textManager;
	this.color = color;
	Width = w;
	Height = h;
	Shape  = s;
	setBackground(Color.WHITE);
	setSize( Width, Height);
	setLayout(null);
	
}
public Drawing(int w, int h, Drawing_Type s, Color color)
{
	this.color = color;
	Width = w;
	Height = h;
	Shape  = s;
	//setBackground(Color.WHITE);
	setSize( Width, Height);
	setLayout(null);
}
/**
 * This constructor is used for  images
 * @param w The desired width of this JPanel
 * @param h The desired height of this JPanel. 100 pixels are added to this height. This is done to make space for a menu to manage the image
 * @param x1image.The image object to be drawn
 * @param y1
 * @param s
 * @param i
 */
public Drawing(int w, int h, int x1, int y1, Drawing_Type s, ImageIcon i)
{	
        super();        
        this.setSize(new Dimension(w ,h + 100));        
	this.PicAreaWidth = i.getImage().getWidth(this);
	this.PicAreaHeight = i.getImage().getHeight(this);
	this.x1 = x1;
	this.y1 = y1;
	Width = w;
	Height = h;
	Shape  = s;
	setBackground(Color.WHITE);
	setLayout(null);
        
        image = i;
      //  ScaledImage= i.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        //setSize(image.getWidth(this),image.getHeight(this));
}

/**
 * This constructor is used for scaled images
 * @param w
 * @param h
 * @param x1
 * @param y1
 * @param s
 * @param i
 * @param PicAreawidth This is how much width form the picture should be painted
 *  @param PicAreaLength This is how much length from the picture should be painted 
 */
public Drawing(int w, int h, int x1, int y1, Drawing_Type s, ImageIcon i,int PicAreaWidth, int PicAreaLength )
{
	image= i;
	this.PicAreaWidth = PicAreaWidth;
	this.PicAreaHeight = PicAreaLength;
	this.x1 = x1;
	this.y1 = y1;
	Width = w;
	Height = h;
	Shape  = s;
	setBackground(Color.WHITE);
	setSize( Width, Height);
	setLayout(null);
}
/**
 * 
 * @param x1
 * @param y1 This value should always be zero. If this is value is not passed as zero then this constructor might 
 * not work properly
 * @param x2
 * @param y2
 * @param s
 * @param color
 */
public Drawing(int size, Drawing_Type s, Color color)
{
	setLayout(null);
	this.Shape = s;
	if(Shape == Drawing_Type.VLine_Type)
	{
	Width =  5;
	Height = size;
	this.y2 = size;
	}
	else if(Shape == Drawing_Type.HLine_Type)
	{

		Width =  size;
		Height = 5;
		this.x2 = size;
	}
	this.setSize(Width, Height);
	this.setThis();
	this.color = color;
}

public Drawing(String t,Drawing_Type s ,Color color, Font f, int width, int height)
{
	this.setThis(JournalKit.getPixelWidthFromText(f, t, this), height);
	thisFont = f;
	this.drawText = t;
	this.Shape = s;
	x1 = 0;
	y1  = this.getSize().height/2;
	this.color = color;
}

    Drawing(int i, int i0, int i1, int i2, Drawing_Type drawing_Type, Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public void paint(Graphics g)
{
	 thisDrawing = (Graphics2D)(g);
	super.paint(thisDrawing);
	if(Shape == Drawing_Type.Rect_Type)
	{
	thisDrawing.setColor(color);
	thisDrawing.draw3DRect(0,0, Width-1, Height-1, true);
	}
	else if(Shape == Drawing_Type.ImageScaled_Type)
	{
                thisDrawing.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(ScaledImage, x1, y1, Width, Height, 0, 0,PicAreaWidth, PicAreaHeight, this);
	}
	else if(Shape == Drawing_Type.VLine_Type)
	{
		thisDrawing.setColor(color);
		thisDrawing.drawLine(0,0,0, y2);
	}
	else if(Shape == Drawing_Type.HLine_Type)
	{
		thisDrawing.setColor(color);
		thisDrawing.drawLine(0, 0, x2, 0);
	}
	else if(Shape == Drawing_Type.Image_Type)
	{
            thisDrawing.drawImage(this.image.getImage(), x1, y1, PicAreaWidth, PicAreaHeight , this);
        }
	else if(Shape == Drawing_Type.StatusBar_Type)
	{
	}
	else if(Shape  == Drawing_Type.TagTool_Type)
	{

		thisDrawing.setColor(color);
		thisDrawing.draw3DRect(0,0, Width-28, Height-20, true);
		
	}
	else if(Shape == Drawing_Type.Text_Type)
	{
		thisDrawing.setColor(color);
		thisDrawing.setFont(thisFont);
		thisDrawing.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		thisDrawing.drawString(drawText, x1, y1);
	}
	else if(Shape == Drawing_Type.Tag_Type)
	{
		thisDrawing.setColor(color);
		thisDrawing.draw3DRect(0,0, Width-1, Height-1, false);
	}
}
public Drawing()
{
	
}

public void updateTag(String text)
{
	textManager.setText(text);
}
public void setThis()
{
	this.setLayout(null);
	this.setBackground(Color.WHITE);
}
public void setThis(int w, int h)
{
	this.setLayout(null);
	
        
	this.setSize(new Dimension(w, h));
	this.Height = h;
	this.Width = h;
	
}
public Drawing getThis()
{
	return this;
}
public  String getDrawText() {
	return drawText;
}
public  void setDrawText(String text) {
	drawText = text;
	
}
public void setImageFile(ImageIcon i)
{
	image = i;
}
public ImageIcon getImage()
{
    return image;
}
public Image getScaledImage()
{
    return ScaledImage;
}
public void cropImage()
{
    
}
/**
 * This sets the GRAPHICAL side of an image menu
 */
public void setImageMenu()
{
ImageMenu = new JPanel();
ImageMenu.setVisible(false);
ImageMenu.setBackground(Color.WHITE);
ImageMenu.setLayout(null);
ImageMenu.setSize(new Dimension(this.getSize().width, 75));
ImageMenu.setLocation(new Point(0, this.getSize().height-100));
ImageMenu.setBorder(new LineBorder(Color.BLACK, 1));
setDeleteIcon();
ImageMenu.add(getDeleteIcon());
}
public JPanel getImageMenu()
{
    return ImageMenu;
}
/**
 * This MUST be called after setImageMenu is called!
 */
public void setDrawingActions()
{
    this.addMouseListener(new MouseAdapter()
    {
        public void mouseEntered(MouseEvent e)
        {
            
            ImageMenu.setVisible(true);
            System.out.println("drawing actions mouse entered");
        }
        public void mouseExited(MouseEvent e)
        {
            ImageMenu.setVisible(false);
            System.out.println("drawing actions mouse exited");
        }
    }
    );
}
/**
 * Helper for ImageMenu
 */
private void setDeleteIcon()
{
     XIcon = new Drawing("x" , Drawing_Type.Text_Type, Color.RED, new Font(Font.SERIF, Font.BOLD, 50), 30, 60);
     XIcon.setBackground(JournalKit.getTransParentColor());
     XIcon.setLocation((this.getSize().width/2)-10,XIcon.getSize().height/2);
     
     XIcon.addMouseListener(new MouseAdapter()
     {
         public void mouseEntered(MouseEvent e)
         {
              ImageMenu.setVisible(true);
         }
         public void mouseExited(MouseEvent e)
        {
            ImageMenu.setVisible(false);
          
        }
     });
}
public Drawing getDeleteIcon()
{
    return XIcon;
}
}

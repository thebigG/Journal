package JournalApp;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.NavigationFilter;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.*;
public class InputTagSystem extends JPanel implements TagManager, LayoutManageable, ActionListener, JournalComponent
{
private InputTagSystem TagManager;
private JLabel AddTagInstruction;
private JTextField tagInputManager;
private int Width;
private int Height;
private ScrollSystem  ScrollView;
private TagItem CurrentTag;
public ArrayList<TagItem> Tags;
private  int NextTagIndex = 0;	// this keeps track of NEXT available position in Tags
private int currentTagIndex = 0;
private Point CurrentTagPos;
private int CurrentTagPosX = 0;
private int CurrentCaretPos = CurrentTagPosX;
private StringBuilder NumberOfCurrentSpaces;
private FlowLayout thisLayoutManager;
private final int thisLayoutRows = 1;
private int thisLayoutColumns = 1;
private Dimension tagManagerSize;
private boolean Switch;
private  Dimension tagInputManagerSize;
private final int tagYPosition = 10;
private Timer thisTimer;
private String chars25 = "LinuxLinuxLinuxLinuxLinux";
public static int TagWidth;
private boolean isScrollLeftLimit = false;
private boolean Active;
public InputTagSystem()
{
	super();
}
public InputTagSystem(int w, int h) 
	{
		Height = h;
		Width  = w;
		init();
	}
public InputTagSystem(int w, int h, Dimension tagManagerSize)
{

	Height = h;
	Width  = w;
	init();
	setTagManagerSize(tagManagerSize);
	//this.setBorder(new LineBorder(Color.BLACK, 2, false));
}


private void init()
{
	
	setTagManager();
	thisTimer= new Timer(1, this);
	initAddTagInstruction();
	getAddTagInstruction().setLocation(new Point(10,0));
	getTagManager().add(getAddTagInstruction());
	//thisThreadManager = new JournalThreadManager<Updatable>(this);
	//thisThreadManager.start();
	setNumberOfCurrentSpaces();
	initCurrentTagPos();
	setThis();
	//setScrollView();	
	setTags();
	TagItem fontMetricsTag = new TagItem(this.chars25,JournalKit.getInstance().PageFont, SystemColor.menuText);
	TagWidth = JournalKit.getPixelWidthFromText(JournalKit.getInstance().PageFont, this.chars25, fontMetricsTag);
	//activate();
	
}
public void setTagManager() 
{
	seThisLayoutManager();
	TagManager = new InputTagSystem();
	TagManager.setSize(Width-1,Height);
	TagManager.setLocation(0,0);
	//TagManager.setFont(getFont());
	TagManager.setLayout(null);
	//TagManager.setBorder(new LineBorder(JournalKit.DisabledColor, 1, false));
	this.add(TagManager);
	setTagInputManagerSize();
	setTagInputManager();
	TagManager.addMouseWheelListener(new MouseWheelListener()
	{

		public void mouseWheelMoved(MouseWheelEvent e) 
		{
		/**
		 * Use ARROWS to indicate which position is the user scrolling to.
		 */
			System.out.println(e.getWheelRotation());
			if(!Tags.isEmpty())
			if((!isScrollLeftLimit) )
			if(e.getWheelRotation()<0)
				moveTagManagerLeft();
			else
			{
				if(getTagManager().getLocation().x<=-1)
				moveTagManagerRight();
			}
		}
		
	});
	TagManager.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked( MouseEvent e)
		{
			activate();
		}
	});
	this.getTagInputManager().addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			//System.out.println(e.getPoint());
//			if(!Tags.isEmpty())
//			{
//			System.out.println(getTagManager().getComponents()[getComponents().length-1].getLocation().x);
//			int z,x,c;
//			int p;
//			z = getTagManager().getSize().width;
//			x = getLastTagAdded().getSize().width;
//			c = z -getWidthOfAllTags();
//			p = z-c-x;
//			System.out.println("value of scroll left point" + p);
//			System.out.println("position of this: " + getThis().getLocation());
//			System.out.println("position of tag Manager : " + getTagManager().getLocation().x);
//			}
			//thisTimer.stop();
			//NativeKit.update(TagManager);
			//getTagManager().selectAll();
			/*
			 * The commented code can be used to make text selections
			 */
			//getTagManager().getCaret().setDot(0);
			//getTagManager().getCaret().moveDot(40);
			if(getAddTagInstruction().getText().equals("Add Tags..."))
			{
				getAddTagInstruction().setVisible(false);				
			}
			else if(Tags.size() == 1)
			{
				//getThis().getTagManager().getCaret().moveDot(30);;
			}
		}
		public void mousePressed(MouseEvent e)
		{
			((InputTagSystem) getThis()).activate();
			//if(Tags.size() == 1)
			//getTagManager().setNavigationFilter(new CaretFilter(getThis()));
		}
	});
	this.getTagInputManager().addKeyListener(new KeyAdapter()
	{
		public void keyPressed(KeyEvent e)
		{
//			activate();
//			if(Character.isLetter(e.getKeyChar()))
//			CurrentTag.append(e.getKeyChar());
		}
		public void keyReleased(KeyEvent e)
		{
		}
		public void keyTyped(KeyEvent e)
		{
			if(getTagInputManager().getText().length()<25)
			{
			activate();
				System.out.println(e.getKeyChar());
			if(e.getKeyChar() == KeyEvent.VK_ENTER)
			{
				if(!getTagInputManager().getText().isEmpty())
				{
				addTag(getTagInputManager().getText());
				System.out.println("if block executing");
				JournalKit.update((JPanel)getThis());
				if(Tags.size() == 0)
					getAddTagInstruction().setVisible(true);
				}
				getTagInputManager().setText("");
				deactivate();
			}
		}
			else
			{
			}
		}
	});
}
public InputTagSystem getTagManager() 
{	
	return TagManager;
}

public void setThis()
{
	this.setBackground(Color.WHITE);
	this.setSize(Width,Height);
	this.setLayout(null);	
	
}
public InputTagSystem getThis()
{
	return this;
}
public void activate()
{
	this.getTagInputManager().setEnabled(true);
	this.getTagInputManager().setEditable(true);	
	//this.getTagInputManager().getCaret().setVisible(true);
	this.getTagInputManager().setVisible(true);
	Switch = true;
	//this.getTagManager().getCaret().moveDot(CurrentTagPosX);
}
public void deactivate()
{
	this.getTagInputManager().setEditable(false);
	this.getTagInputManager().setEnabled(false);
	this.getTagInputManager().getCaret().setVisible(false);
	this.getTagInputManager().setVisible(false);
	Switch = false;
}
public void setScrollView()
{
	ScrollView = new ScrollSystem(TagManager);
	ScrollView.setLocation(0,0);
	ScrollView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	//ScrollView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	this.add(ScrollView);
}
public ScrollSystem getScrollView()
{
	return ScrollView;
}

public void setTags()
{
	Tags = new ArrayList<TagItem>(10);
}
public ArrayList<TagItem> getTags()
{
	return Tags;
}
public TagItem getTag(int index)
{
	return Tags.get(index);
}
public void addTag(String tag) 
{
        JournalKit.updateCurrentEntryState();
	System.out.println("current index of tags: " + NextTagIndex);
	 CurrentTag = new TagItem(tag,JournalKit.getInstance().PageFont, SystemColor.menuText);
	CurrentTag.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			removeTag(Tags.indexOf(e.getSource()));
			System.out.println("Junior........." + Tags.indexOf(e.getSource()));
		}
	});;
	updateCurrentTagPos(CurrentTag);	
	//debugTags(NextTagIndex);
	System.out.println("current x pos of tag: " + CurrentTagPosX);
	//updateNumberOfSpaces();
	//Tags.get(NextTagIndex).setDarkness(20);
	//Tags.get(NextTagIndex).makeDarker();
	updateNextTagIndex();
	deactivate();
	System.out.println("last Tag method>>>>>: " + getLastTagAdded());
	Tags.add(NextTagIndex,CurrentTag);
	thisTimer.start();
	this.getTagManager().add(Tags.get(NextTagIndex));
	updateTagManager();
	System.out.println("position of last tag added: " + getLastTagAdded().getSize());
	if(!JournalKit.isThereEnoughSpace((JournalComponent)getTagManager(), TagWidth, JournalKit_Type.Width,  Tags))
	JournalKit.changeSizeOf(getTagManager(), new Dimension(getTagManager().getSize().width + TagWidth + JournalKit.pixlexAfterItem, getTagManager().getSize().height));
	System.out.println("position of last tag added: " + getLastTagAdded().getSize());
	System.out.println("25 characters in pixels with pagefont font : " +  TagWidth);
}
public void updateTagManager() 
{
	updateTagInputManager();
	debugTags(NextTagIndex);

}
public void initCurrentTagPos()
{
	CurrentTagPos = new Point(20,0);
}
public Point getCurrentTagPos()
{
	return CurrentTagPos;
}
public void updateCurrentTagPos(TagItem tag)
{
	System.out.println("size is :" + Tags.size());
	if(Tags.size()==0)
	{
	CurrentTagPos = new Point(0, tagYPosition);
	System.out.println(  "if condition executed ");
	}
	else if(Tags.size()!=0)
	{
		//CurrentTagPos = new Point((getLastTagAdded().getLocation().x + getLastTagAdded().getSize().width) , tagYPosition);
		CurrentTagPos = new Point((getLastTagAdded().getLocation().x + getLastTagAdded().getSize().width) + JournalKit.pixlexAfterItem,tagYPosition);
		System.out.println("else if condition executed ");

	}
	//System.out.println(getTagManager().getCaret().getDot());
	//System.out.println(CurrentTagPosX);
	tag.setLocation(CurrentTagPos);
	System.out.println("}}}}>>>> Last tag added: " +getLastTagAdded() );
	//System.out.println("updating tag position...."
		//	+ "\n---position of the last tag added (X) :" + getLastTagAdded().getLocation().x );
	//System.gc();
}
//DO NOT DELETE THE COMMENTED CODE BELOW UNTIL THE NEW APPROACH 
// OF INPUTTAGSYSTEM WORKS!
//public void updateCurrentCaretPos()
//{
//	CurrentCaretPos = CurrentTagPosX;
//	if(Tags.size()==0)
//	{
//		getTagManager().getCaret().setDot(CurrentTag.length());
//	}
//	else
//	{
//	getTagManager().getCaret().setDot(CurrentCaretPos);
//	}
//}
public int getCurrentCaretDot() 
{
	return CurrentTagPosX;
}
public Point getCurrentMagicCaretPos()
{
	return new Point(0, CurrentTagPosX);
}
public boolean isEmpty()
{
	return (Tags.size() == 0);
}
public void setNumberOfCurrentSpaces()
{
	NumberOfCurrentSpaces = new  StringBuilder();
}
public void updateNumberOfSpaces()
{
	NumberOfCurrentSpaces.delete(0, NumberOfCurrentSpaces.length());
	for(int i =0 ;i<Tags.size();i++)
	{
		for(int j = 0;j<Tags.get(i).getTagContent().length();j++)
		{
			NumberOfCurrentSpaces.append(" ");
		}
	}
	System.out.println("number of spaces added + " + NumberOfCurrentSpaces.length() );
Debug.Print("x pos " + CurrentTagPosX);
}
public StringBuilder getNumberOfCurrentSpaces()
{
	return NumberOfCurrentSpaces;
}
/**
 * TODO DONT ERASE the commented code. I will deal with this down the road. 
 * This method will create a SELECTOR for tags with the mouse. If this selector is accomplished it can be used for putting an X icon over each tag.
 * This way tags can be deleted INDIVIDUALLY.
 */
//public void addSelectionListener()
//{
//	this.addMouseMotionListener(((new MouseAdapter()
//	{
//		final int Index = NextTagIndex;
//		public void mouseMoved(MouseEvent e) 
//		{
//			if(NativeKit.isMouseHere((Tags.get(Index)), e))
//			(Tags.get(Index)).setBackground(SystemColor.textHighlight);
//			else
//				(Tags.get(Index)).setBackground(Color.WHITE);
//		}
//
//		public void mouseExited(MouseEvent e) 
//		{
//			(Tags.get(Index)).setBackground(Color.WHITE);
//		}
//		public void mouseClicked(MouseEvent e)
//		{
//			System.out.println("clicked");
//		}
//		
//	})));
//}
public void seThisLayoutManager() 
{
	thisLayoutManager = new FlowLayout();
	//thisLayoutManager.setHgap(1);
	//this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	//thisLayoutManager.setAlignment(FlowLayout.LEADING);
}
public LayoutManager getThisLayoutManager() 
{	
	return thisLayoutManager;
}
public void updateLayoutManager() 
{

}
public void setTagManagerSize(Dimension s)
{
	tagManagerSize = s;
}
public void setTagManagerSize(int w, int h)
{
	tagManagerSize = new Dimension(w,h);
}
public boolean isActivated() 
{
	return Switch;
}
public void setTagInputManager()
{
	tagInputManager = new JTextField();
	//AbstractDocument x = (AbstractDocument)((tagInputManager.getDocument()));
	//x.setDocumentFilter(new DocumentSizeFilter(5));
	//System.out.println(JournalKit.isNull(tagInputManager));
	tagInputManager.setDocument(new JTextFieldFilter(TagItem.TagCharLimit, this.getTagInputManager()));
	//PlainDocument x =     (PlainDocument) tagInputManager.getDocument();
	//x.setDocumentFilter(new DocumentSizeFilter(0));
	//tagInputManager.setDocument(x);
	tagInputManager.setBounds(0,0, tagInputManagerSize.width, tagInputManagerSize.height);
	tagInputManager.setBorder(null);
	tagInputManager.setBackground(JournalKit.getTransParentColor());
	tagInputManager.addCaretListener(new CaretListener()
	{

		public void caretUpdate(CaretEvent e) 
		{
			//System.out.println("Caret being updated!!!!!");
			//JournalKit.update((JPanel)getThis());
			
		}}
	);
	getTagManager().add(tagInputManager);
}
public JTextField getTagInputManager()
{
	return tagInputManager;
}
public  void setTagInputManagerSize()
{ 
	tagInputManagerSize  = new Dimension(150,30);
}
public void setTagInputManager(Dimension d)
{
	tagInputManagerSize = d;
}
public Dimension getTagInputManagerSize()
{
	return tagInputManagerSize;
}
public void updateTagInputManager()
{
//getTagInputManager().setLocation(new Point(getTagInputManager().getLocation().x + 20,getTagInputManager().getLocation().y));
System.out.println("input manager: " + getTagInputManager());
System.out.println("current Tag: " + getCurrentTag());
System.out.println("last tag added : " + getLastTagAdded());
getTagInputManager().setLocation((getCurrentTag().getLocation().x + getLastTagAdded().getSize().width) + JournalKit.pixlexAfterItem,0);
System.out.println(getTagInputManager().getCaret().getMagicCaretPosition());
System.out.println("updating location of input tag manager:" + getTagInputManager().getLocation());
}
public void updateNextTagIndex()
{
System.out.println("updating next tag index.... " );
NextTagIndex = Tags.size();	
System.out.println("value of next tag index: " + NextTagIndex);
if(Tags.size()>=1)
updateCurrentTagIndex();
}
public void updateCurrentTagIndex()
{
	currentTagIndex = (NextTagIndex - 1);
	Debug.Print("value of CURRENT tag index: " + currentTagIndex);
}
/**
 * 
 * @return the last tag added to the Tags arrayLIst. If this list is empty it returns null.
 */
public TagItem getLastTagAdded()
{
	//System.out.println("index of the last tag added>>>>>>>: " +currentTagIndex );
	TagItem tag = null;
	if(Tags.size()>=1)
	tag = Tags.get(Tags.size()-1);
	return tag;
}
public void debugTags(int index)
{
	System.out.println("<><>>>><><><><><. adding tag" + Tags.get(index)
			+ "\n>>>>>> " + Tags + "\n size: " + Tags.size());
}
public void updateTags()
{
	for(TagItem t : Tags)
	{
		JournalKit.update(t);
	}
}
public void moveTagManagerLeft()
{
	{
		TagManager.setLocation(new Point(TagManager.getLocation().x-1, TagManager.getLocation().y));
	}
//	System.out.println("moving TagManager to the left");
//	System.out.println("Position of tag manager: " + getTagManager().getLocation());
//	System.out.println("last Tag method>>>>>Location: " + getLastTagAdded().getLocation());
//	System.out.println("position of " + getLastTagAdded() + getLastTagAdded().getLocation());
	
}
/**
 * When moving(scrolling) the tag manager to the right one should not allow this method to function 
 * if the X position of the tag manager is LESS THAN 1
 */
public void moveTagManagerRight()
{
	getTagManager().setLocation(new Point(TagManager.getLocation().x+1, TagManager.getLocation().y));
//	System.out.println("moving TagManager to the right");
//	System.out.println("Position of tag manager: " + getTagManager().getLocation());

}
public void removeTag(int index)
{
	System.out.println();
        JournalKit.updateCurrentEntryState();
	JComponent  removedItem = Tags.get(index);
	getTagManager().remove(Tags.get(index));
	Tags.remove(index);
	JournalKit.arrangeTags(index,Tags, removedItem, tagInputManager, this );
	JournalKit.update(this);
	updateNextTagIndex();
}
public void update()
{
		System.out.println("Updating...");
}
public void actionPerformed(ActionEvent e) 
{
	if(!Tags.isEmpty())
	{
	//System.out.println("timer");
	ScrollLeftLimit();
	updateTagInputManager();
	}
	
}
private TagItem getCurrentTag()
{
return CurrentTag;	
}
public void setAddTagInstruction(String txt)
{
	AddTagInstruction.setText(txt);
}
public void initAddTagInstruction()
{
	AddTagInstruction = new JLabel("Add Tags...");
	AddTagInstruction.setForeground(Color.GRAY);
	AddTagInstruction.setSize(new Dimension(JournalKit.getPixelWidthFromText(AddTagInstruction.getFont(), AddTagInstruction.getText(), AddTagInstruction), 20));
}

public JLabel getAddTagInstruction()
{
	return AddTagInstruction;
}
private void  ScrollLeftLimit()
{
	if(!Tags.isEmpty())
	{
	int TotalWidth,LastTagAddedWidth,Chunk, p, BetweenTagWidth = 0;
	for(int i =0 ;i<getTags().size();i++)
	{
	BetweenTagWidth += JournalKit.pixlexAfterItem; 
	}
	TotalWidth = getTagManager().getSize().width;
	LastTagAddedWidth = getLastTagAdded().getSize().width;
	Chunk = TotalWidth -(getWidthOfAllTags() + BetweenTagWidth);
	p =  TotalWidth-Chunk-LastTagAddedWidth;
	System.out.println("value of p: " + p);
	System.out.println("value of tag manager pos :" + getTagManager().getLocation().x);
	if(p!=0)
isScrollLeftLimit = (Math.abs((getTagManager().getLocation().x)) >=(p));
	}
}
private int getWidthOfAllTags()
{
	int width = 0;
	for(int i = 0;i<Tags.size();i++)
	{
		width += Tags.get(i).getSize().width;
	}
	return width;
}
/**
 * 
 * @return an ArrayList of Strings that represent all of the tags that belong to this InputTagSystem
 */
public ArrayList<String> getAllTags()
{
    ArrayList<String> tags = new ArrayList<>();
    //System.out.println("tags size :" + getTags().size());
    for(int i = 0;i<getTags().size();i++)
    {
    tags.add(getTags().get(i).getTagContent());
    }    
    return tags;
}
public Timer getThisTimer()
{
    return thisTimer;
}
}
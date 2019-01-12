package JournalApp;
import ImageUtil.Format_Type;
import JournaLIO.JournaLIOLogic_Type;
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.ButtonPeer;
import java.awt.peer.CanvasPeer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DesktopPeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.LabelPeer;
import java.awt.peer.ListPeer;
import java.awt.peer.MenuBarPeer;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;
import java.awt.peer.PanelPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.TextFieldPeer;
import java.awt.peer.WindowPeer;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.UrlValidator;
/**
 * 
 * @author LorenzoGomez
 *This class is meant to be FUNCTIONAL and it is a collection of methods(mostly public and static)
 */

public  class JournalKit extends Toolkit 
{
static int TabUnicode = 9;
public static String EntriesPath = "Entries"; 
public static String EntriesExntension = ".entry";
final int Width =850; //Math.round((getDefaultToolkit().getScreenSize().width - (getDefaultToolkit().getScreenSize().width *299/384)));
final static String TabInSpaces = "         ";
final int Height = 700;
static int CharWidth = 13;
private int MinimumWidth = 475;
final int NumberofInputLines = 22;
public static int pxlsPerLetter = 7;
final String FontName = "Menlo";
final int FontSize = 14;
int FontType = Font.PLAIN;
final Font PageFont = new Font(FontName, FontType, FontSize);
final int InputLinePanelHeight = 24;
final int InputTextPaneHeight = 16;
final int LineSepratorHeight = 6;
final int ZeroCord = 0;
final int InputTextPaneYcord = 6;
final int InputSperatorYcord = 17;
int ExtraPages = 5;
final static int pixlexAfterItem = 5;	// This is intended for classes or GUI Components that will show on some kind of
//list like tag items. This is the distance that should kept between the components
final DefaultCaret caret = new DefaultCaret();
static  JournalKit Instance;
final Point MiddleScreen = new Point(getScreenSize().width/3, getScreenSize().height);
final Dimension MaxSize = new Dimension(Width, Height);
final Dimension MinimumSize = new Dimension(MinimumWidth,Height);
static int  words = 0;
static int NumberOfPages = 1;
static int TwoSeconds  = 2000;
static int Beginning  = -1; // used to know the position of an item in a list 
static int Middle = 0;	// used to know the position of an item in a list 
static int End  = 1;	// used to know the position of an item in a list 
static Color DisabledColor  = Color.GRAY;	//used to signal that a component is not enabled. This means it cannot receive input form the user. An example of this is a JLabel that may be inside a JTextField but may only be used for giving the user directions on what to type. 
private Dimension ScreenSize;
private Robot thisRobot;
private static final int RightClick = InputEvent.BUTTON1_MASK;
public static ImageIcon JournalLogo;
public static URL  JournalLogoURL;
public static URL WriteEntriesURL;
public static ImageIcon AttachmentIcon;
public static int WidthOfPhoto = 300; // This is used for the size of the pictures attached to a journal entry/inputpage 
static
{
    setJournalLogo();
    try {
            System.out.println("***Initializing URL before method call**");
        setJournalLogoURL();
    } catch (MalformedURLException ex) {
        Logger.getLogger(Journal.class.getName()).log(Level.SEVERE, null, ex);
    }
    setAttachmentIcon();
    setWriteEntriesURL();
}
private JournalKit() throws AWTException
{
ScreenSize =  getDefaultToolkit().getScreenSize();	
thisRobot = new Robot();
}

@Override
protected DesktopPeer createDesktopPeer(Desktop target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected ButtonPeer createButton(Button target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected TextFieldPeer createTextField(TextField target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected LabelPeer createLabel(Label target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected ListPeer createList(List target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected CheckboxPeer createCheckbox(Checkbox target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected ScrollbarPeer createScrollbar(Scrollbar target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected ScrollPanePeer createScrollPane(ScrollPane target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected TextAreaPeer createTextArea(TextArea target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected ChoicePeer createChoice(Choice target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected FramePeer createFrame(Frame target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected CanvasPeer createCanvas(Canvas target) {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected PanelPeer createPanel(Panel target) {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected WindowPeer createWindow(java.awt.Window target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected DialogPeer createDialog(Dialog target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected MenuBarPeer createMenuBar(MenuBar target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected MenuPeer createMenu(Menu target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected PopupMenuPeer createPopupMenu(PopupMenu target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected MenuItemPeer createMenuItem(MenuItem target) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected FileDialogPeer createFileDialog(FileDialog target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected CheckboxMenuItemPeer createCheckboxMenuItem(CheckboxMenuItem target)
		throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected FontPeer getFontPeer(String name, int style) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Dimension getScreenSize() throws HeadlessException 
{
	return super.getDefaultToolkit().getScreenSize();
}
@Override
public int getScreenResolution() throws HeadlessException {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public ColorModel getColorModel() throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String[] getFontList() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public FontMetrics getFontMetrics(Font font) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void sync() {
	// TODO Auto-generated method stub
	
}
@Override
public Image getImage(String filename) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Image getImage(URL url) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Image createImage(String filename) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Image createImage(URL url) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean prepareImage(Image image, int width, int height,
		ImageObserver observer) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public int checkImage(Image image, int width, int height, ImageObserver observer) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public Image createImage(ImageProducer producer) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Image createImage(byte[] imagedata, int imageoffset, int imagelength) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void beep() {
	// TODO Auto-generated method stub
	
}
@Override
public Clipboard getSystemClipboard() throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
@Override
protected EventQueue getSystemEventQueueImpl() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public DragSourceContextPeer createDragSourceContextPeer(DragGestureEvent dge)
		throws InvalidDnDOperationException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean isModalityTypeSupported(ModalityType modalityType) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean isModalExclusionTypeSupported(
		ModalExclusionType modalExclusionType) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Map<TextAttribute, ?> mapInputMethodHighlight(
		InputMethodHighlight highlight) throws HeadlessException {
	// TODO Auto-generated method stub
	return null;
}
public static void createInstance() throws AWTException
{
	   Instance = new JournalKit();
}
public static JournalKit getInstance()
{
	return Instance;
}
private static void setAttachmentIcon()
{
AttachmentIcon = getAttachmentIcon();
}
private static void setWriteEntriesURL()
{
    WriteEntriesURL  = InputPage.class.getClassLoader().getResource("Images/WriteEntries.jpg");
}
public static String getDate()
{
	String[] array = Calendar.getInstance().getTime().toString().split(" ");
	StringBuilder Date = new StringBuilder();
	for(int k = 0; k<=2;k++)
	{
		if(k == 0)
		{
			if(array[k].equalsIgnoreCase("Mon"))
				array[k] = "Monday";
			if(array[k].equalsIgnoreCase("Tue"))
			array[k] = "Tuesday";
			if(array[k].equalsIgnoreCase("Wed"))
				array[k] = "Wednesday";
			if(array[k].equalsIgnoreCase("Thu"))
				array[k] = "Thursday";
			if(array[k].equalsIgnoreCase("Fri"))
				array[k] = "Friday";
			if(array[k].equalsIgnoreCase("Sat"))
				array[k] = "Saturday";
			if(array[k].equalsIgnoreCase("Sun"))
				array[k] = "Sunday";
		}
		Date.append(" ");
		Date.append(array[k]);
	}
	Date.append(" ");
	Date.append(array[array.length-1]);
	return Date.toString();
	
}
public static boolean isMouseHere(Component c, MouseEvent e)
{
	return ((e.getPoint().x>=c.getLocation().x && e.getPoint().x<=c.getLocation().x+c.getSize().width) && (e.getPoint().y>=c.getLocation().y && e.getPoint().y<=c.getLocation().y+c.getSize().height));
}
public static boolean isMouseHere(int x1, int y1,int x2, int y2, MouseEvent e)
{
	return ((e.getPoint().x>=x1 && e.getPoint().x<=x1+x2) && (e.getPoint().y>=y1 && e.getPoint().y<=y1+y2));
}
public static boolean isNull(Object o)
{
	return o == null;
}
public  static int getNumberOfWords()
{
    words = 0;
	for(int k = 0;k<Journal.getInstance().getPages().size();k++)
	{
		words += Journal.getInstance().getPage(k).getNumberOfWords();
              // System.out.println("Number fo words function" + words + " " + Journal.getInstance().getPage(k) );
		//System.out.println("NativeKit "+words);
	}
                        
	return words;
}
public static void updateCurrentLine()
{
	for(int j = 0;j<JournalKit.getInstance().NumberofInputLines;j++)
	{
		if(Journal.getInstance().getPage(InputPage.CurrentPage).getInputLine(j).getStatus())
		{
			InputPage.CurrentLine  = j;
		}
	}
}
public static void updatePages()
{
NumberOfPages = Journal.getInstance().getPages().size();
}
public static void setJournalLogo()
{
JournalLogo  = JournalKit.getJournaLogoImage();
}
public static void setJournalLogoURL( ) throws MalformedURLException
{
    JournalLogoURL  = InputPage.class.getClassLoader().getResource("Images/JournalLogoV2.png");
    System.out.println("***Initializing URL**");
}
public static void nextPage() 
{
	InputPage.CurrentPage++;
	Debug.Print("current page:"  + InputPage.CurrentPage);
        	if(InputPage.CurrentPage == Journal.getInstance().getPages().size()-1)
	Journal.getInstance().getPages().add(new InputPage(false));
                
	Journal.getInstance().getMainJournalPanel().add(Journal.getInstance().getPages().get(InputPage.CurrentPage));
	Journal.getInstance().getPages().get(InputPage.CurrentPage-1).setVisible(false);
	Journal.getInstance().getMainJournalPanel().remove(Journal.getInstance().getPages().get(InputPage.CurrentPage-1));
	Journal.getInstance().getPages().get(InputPage.CurrentPage).setVisible(true);
	//if(InputPage.CurrentPage>=1)
	//	Journal.getInstance().getPages().get(InputPage.CurrentPage-1).setVisible(false);
	
}
public static void previousPage()
{
        if(InputPage.CurrentPage != 0 )
        {
	InputPage.CurrentPage--;
	Debug.Print("current page:"  + InputPage.CurrentPage);
	Journal.getInstance().getMainJournalPanel().add(Journal.getInstance().getPages().get(InputPage.CurrentPage));
	Journal.getInstance().getPages().get(InputPage.CurrentPage).setVisible(true);
	Journal.getInstance().getPages().get(InputPage.CurrentPage+1).setVisible(false);
        }
        
}
/**
 * deletes the InputPage at PageIndex from the Pages array in Journal.
 * @param PageIndex 
 */
public static void deletePage(int PageIndex)
{
Journal.getInstance().getPages().get(PageIndex).setVisible(false);
InputPage removedPage = Journal.getInstance().getPages().remove(PageIndex);
Journal.getInstance().getMainJournalPanel().remove(removedPage);
System.out.println("delet page function" + PageIndex);
if(PageIndex == 0 && (Journal.getInstance().getPages().size()<2))
{
String removedPageTitle  = removedPage.getTitle().getText();
Journal.getInstance().getPages().add(PageIndex, new InputPage(true));
Journal.getInstance().getPages().get(PageIndex).setTitlePane();       
Journal.getInstance().getPage(PageIndex).getTitle().setText(removedPageTitle);
removedPage.getThisTimer().stop();
Journal.getInstance().getPages().get(PageIndex).add(Journal.getInstance().getPages().get(PageIndex).getTitlePane());
Journal.getInstance().getMainJournalPanel().add(Journal.getInstance().getPages().get(PageIndex));
Journal.getInstance().getPages().get(PageIndex).setVisible(true);
}
else if(PageIndex == 0)
{
    
 String removedPageTitle  = removedPage.getTitle().getText();
//Journal.getInstance().getPages().add(PageIndex ,new InputPage(true));
Journal.getInstance().getPages().get(PageIndex).getInput().get(PageIndex).formatLine();
Journal.getInstance().getPages().get(PageIndex).getInput().get(PageIndex).getTextPane().setEditable(false);
Journal.getInstance().getPages().get(PageIndex).setTitlePane();
Journal.getInstance().getPage(PageIndex).getTitle().setText(removedPageTitle);
removedPage.getThisTimer().stop();
Journal.getInstance().getPages().get(PageIndex).add(Journal.getInstance().getPages().get(PageIndex).getTitlePane());
Journal.getInstance().getMainJournalPanel().add(Journal.getInstance().getPages().get(PageIndex));
Journal.getInstance().getPages().get(PageIndex).setVisible(true);
System.out.println("deleting condition");
}
else
{
if(PageIndex == Journal.getInstance().getPages().size()-1)    
{
  removedPage.getThisTimer().stop();
  previousPage();
}
else
{
removedPage.getThisTimer().stop();
Journal.getInstance().getMainJournalPanel().add(Journal.getInstance().getPages().get(PageIndex));
Journal.getInstance().getPages().get(PageIndex).setVisible(true);
}
}
if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved)
{
Journal.getInstance().getCurrentEntry().setEntryState(EntryState.Modified);
}
removedPage = null;
}
public static void update(Component c)
{
	if(c.isDisplayable())
	c.update(c.getGraphics());
}
/**
 * This is the equivalent of calling System.gc();
 */
public static void TrashObjects()
{
    System.out.println("Calling TrashObjects");
	System.gc();
}
public static Color getTransParentColor()
{
	return new Color(0,0,0,0);
}
public static void destroyObject(Object o)
{
	o = null;
}
public static void changeSizeOf(JComponent c , Dimension size)
{
	System.out.println("current size: " + c.getSize());
	c.setSize(size);
	System.out.println("size after :" + c.getSize());
}
public static Font getNormalSizeFont()
{
	return new Font(Font.SERIF,Font.PLAIN,15);
}

public static void makePanelImage(Component panel)
{
    Dimension size = panel.getSize();
    BufferedImage image = new BufferedImage(
                size.width, size.height 
                          , BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = image.createGraphics();
    panel.paint(g2);
    try
    {
        ImageIO.write(image, "jpeg", new File("snapshot.jpeg"));
        System.out.println("Panel saved as Image.");
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
public static int getMilliseconds(int Seconds)
{
	return(Seconds * 1000);
}
/**
 * This function checks if the character a keyboard event has received an input that should not be displayed
 * such as the command key on OS X or the windows key on windows 
 * @param e the KeyEvent that will be checked  
 * @return true if the KeyEvent has a special character. Otherwise, it returns false.
 */
public static boolean hasSpecialChars(KeyEvent e)
{
	boolean test = false;
	if(e.getKeyChar() == KeyEvent.CHAR_UNDEFINED)
	{
		System.out.println("true for: ");
		test  = true;
	}
	else  if(e.getKeyChar() ==  KeyEvent.VK_WINDOWS)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_ACCEPT)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_ALL_CANDIDATES)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_CONTROL)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_CAPS_LOCK)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_CONTEXT_MENU)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_CANCEL)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_DELETE)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_ENTER)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_COPY)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_CUT)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_ESCAPE)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_INSERT)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_SHIFT)
		test = true;
	else  if(e.getKeyChar() ==  KeyEvent.VK_TAB)
		test = true;
return 	test;
}
/**
 * This method should be used when a component has been eliminated from a list that is arranged from left to right.
 * @param index the subscript of the component that was removed 
 * @param list	the list of components
 * @param ItemRemoved the component that was removed form the list
 */
public static void arrangeTags(int index, ArrayList<TagItem> list, Component ItemRemoved, JTextComponent inputTagmanager, InputTagSystem instance)
{
if(!list.isEmpty())
{
int Position = whereInTheList(index, list.toArray());

int PixelsOfComponent = (ItemRemoved.getWidth() +pixlexAfterItem ); 

if(Position == Beginning)
{
for(int i = 0;i<list.size(); i++ )
{
	list.get(i).setLocation(list.get(i).getX() - PixelsOfComponent , list.get(i).getY());
}	
instance.updateTagInputManager();
}
else if(Position == Middle)
{
	for(int i = index;i<list.size();i++)
	{
	 list.get(i).setLocation(list.get(i).getX() - PixelsOfComponent , list.get(i).getY());
	}
	//inputTagmanager.setLocation();
	instance.updateTagInputManager();
}
}
}

//public static int distanceBetweenComponents(Component... Components)
{
	
}
/**
 * This method checks weather index is at the beginning, middle or end of the Object list.
 * @param index index used to check
 * @param list the array that will be checked 
 * @return the position(Beginning, Middle or End) where the index is in the Object array. The value returned by this method 
 * MUST be compared with the static public predefined fields that JournalKit provides. These are Beginning, Middle, and End.  
 */
public static int whereInTheList(int index, Object... list)
{
	int Position  = 20;
	if(index  == 0)
	{
	Position = Beginning;
	System.out.println("Middle position RETURNED");
	}
	else if(index>0 && index<list.length)
	{
		Position  = Middle;
		System.out.println("Middle position RETURNED");
	}
	else if(index  == (list.length-1))
	{
		Position  = End;
		System.out.println("End position RETURNED");
	}
	System.out.println(Position);
	return Position;
}
public static int  getPixelWidthFromText(Font font, String txt, Component component)
{
	return component.getFontMetrics(font).stringWidth(txt);
}
public static <E extends JournalComponent> boolean isThereEnoughSpace(JournalComponent container, int DesiredSpace, JournalKit_Type type,  ArrayList<E> components )
{
	boolean enough = false;
	if(type == JournalKit_Type.Width)
	{
	int width = 0;
	System.out.println("testing enough width......");
	for(int i =0;i<components.size();i++)
	{
		width += components.get(i).getSize().width;
	}
	System.out.println("width of all components :" + width);
	if((container.getSize().width - width ) >= (DesiredSpace))
	{
		enough = true;
		System.out.println("testing enough width...... true");
	}
	}
	else if(type == JournalKit_Type.Height)
	{

		int height = 0;
		for(int i =0;i<components.size();i++)
		{
		}
		if((container.getSize().height - height ) >= (DesiredSpace))
		{
			enough = true;
		}
		
		
	}
	return enough;
}
/**
 * This class updates fields of JournalKit that need to be updated at all times.
 */
// class DataManagement implements JournalThread
// { 
//	@Override
//	public void runJournalThread() 
//	{
//		 
//	}
//
//        @Override
//        public void stopJournalThread() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void run() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//	
// }
 /**
  * send the mouse pointer to the coordinates of p
  * @param p where mouse will go
  */
 public  void mouseGoTo(Point p)
 {
	 thisRobot.mouseMove(p.x,p.y);
 }
 /**
  * Makes the mouse click where ever it is 
  */
 public void mouseClick()
 {
	 thisRobot.mousePress(RightClick);
 }
 /**
  * This method makes the mouse go to point p and then click 
  * @param p where the mouse will go
  */
 public void mouseClick(Point p)
 {
	 mouseGoTo(p);
	 mouseClick();
 }
 public static String getNumberOfSpaces(int numberOfSpaces)
 {
	StringBuilder  text = new StringBuilder();
	for(int i = 0 ;i<numberOfSpaces;i++)
	{
		text.append(" ");
	}
	return text.toString();
 }
 /**
  * This method replaces any tabs found in a piece of text and replaces those tabs with JournalKit.TabSpaces, which is
  * a String containing 9 spaces
  * @param text The String to be scanned for Tabs
  * @return The string that has been modified.
  */
 public static String cleanTabs(String text)
 {
 	System.out.print("Tab method");	
 	System.out.println(text);
 	StringBuilder textBuilder  = new StringBuilder(text);
 	for(int i = 0;i<textBuilder.length();i++)
 	{
 		if(textBuilder.charAt(i) == 9)
 		{
 			textBuilder.replace(i, i+1, JournalKit.TabInSpaces);
 		}
 			
 	}
 	System.out.println("number of characters== " + textBuilder.length());
 	return textBuilder.toString();
 }
 /**
  * This method takes an IMAGE file and calculates the "Scaled" size of this image based on the desired size.
  * By "scaled image" it is the same as saying: "keep the same ratio and make it smaller" 
  * @param DesiredWidth The width that the user desires the scaled image to be
  * @param DesiredHeight The Height that the user desires the scaled image to be
  * @param imageFile The file that holds this image
  * @param WidthLimit 
  * @param HeightLimit
  * @return 
  */
public static Dimension getThisScale(int DesiredWidth, int DesiredHeight, File imageFile, int WidthLimit, int HeightLimit)
{
    new ImageIcon(imageFile.getAbsolutePath());
    return null;
}
public static String generateRandomName(int CharNum)
{
Random randomGenerator = new Random();
String generatedName = "";
char[] GeneratedCharacters = new char[CharNum];
{
    for(int i = 0;i<GeneratedCharacters.length;i++)
    {
        int decisionMaker = randomGenerator.nextInt(2);
        if(decisionMaker == 1){
        char GeneratedNumber = 0;
        do
        {
        GeneratedNumber = (char)randomGenerator.nextInt(58);
        GeneratedCharacters[i] = GeneratedNumber;
        }while(GeneratedNumber<48);
       generatedName = generatedName.concat(Character.toString(GeneratedCharacters[i]));
    }
    
    else{
        char GeneratedLetter = 0;
        do
        {
        GeneratedLetter = (char)randomGenerator.nextInt(91);
        GeneratedCharacters[i] = GeneratedLetter;
        }while(GeneratedLetter<65);
               generatedName = generatedName.concat(Character.toString(GeneratedCharacters[i]));
            }
}

return generatedName;
}
}
/**
 * Figures out in which side of the component the mouse is at.
 * @param shape the component
 * @return the position: Bottom,Top, Right, or Left
 */
//public static SidePosition_Type whichSideIsIt(Component shape, MouseEvent e)
//{
//   
//}
/**
 * This a
 * @param c
 * @param filling
 * @param size
 * @param p 
 */
public static void initComponent(Component c, Color filling, Dimension size, Point p)
{
    
}
public static ImageIcon getJournaLogoImage()
{
    return  new ImageIcon(InputPage.class.getClassLoader().getResource("Images/JournalLogoV2.png"));
}
public static Format_Type getFormat(String FilePath)
{
    Format_Type x = Format_Type.UNKNOWN;
    System.out.println(FilePath);
      String[] Slashes  = FilePath.split("\\.");       
          System.out.println(Slashes[Slashes.length-1]);
       String extension = Slashes[Slashes.length-1];
        if(extension.contains("gif"))
        {                 
            x =  Format_Type.GIF;
            System.out.println("gif");
        }
       else if(((extension.contains("jpg")) || extension.contains("jpeg")) || (extension.contains("JPG") || extension.contains("JPEG")))
        {
             x =  Format_Type.JPG;
             System.out.println("jpg");
        }
       else if(extension.contains("PNG") || extension.contains("png") )
       {
           x =  Format_Type.PNG;
           System.out.println("png");
       }
        return x;
}
public static boolean isThisURLValid(URL link)
{
boolean test = true;
UrlValidator validator  = new UrlValidator();
//System.out.println("URL toString: " + validator.toString());
if(validator.isValid(link.toString()) || link == null)
{
    test = false;
}
return test;
}
public static boolean isThisURLValid(String link)
{
boolean test = true;
UrlValidator validator  = new UrlValidator();
//System.out.println("URL toString: " + validator.toString());
if((link == null) || (!validator.isValid(link)))
{
    test = false;
}
return test;
}
public static ImageIcon getWriteNewentryImage()
{
    return  new ImageIcon(InputPage.class.getClassLoader().getResource("Images/WriteEntries.jpg"));
}
public static ImageIcon getAttachmentIcon()
{
    return new ImageIcon (InputPage.class.getClassLoader().getResource("Images/AddAttachement-V1.png"));
}
/**
 * This method checks whether a filename inside a path exists or not
 * @param path folder/directory to check
 * @param name Filename to check 
 * @return 
 */
public static JournaLIOLogic_Type DoesThisNameExist(String path, String name)
{
    File dir = new File(path);
    if(!dir.isDirectory())
    {
        System.out.println("generating a new title");
        return JournaLIOLogic_Type.NotDirectory;
    }    
        JournaLIOLogic_Type test = JournaLIOLogic_Type.False_ThisFileDoesNotExist;;
    File[] FileNames = dir.listFiles();
    for(int i = 0;i<FileNames.length;i++)
    {
        if(FileNames[i].getName().equalsIgnoreCase(name))
        {
            test = JournaLIOLogic_Type.True_TheFileExists;
        }
    }
    return test;
}

public static void updateCurrentEntryState()
{
    if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved)
        Journal.getInstance().getCurrentEntry().setEntryState(EntryState.Modified);
}
}
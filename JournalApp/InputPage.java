package JournalApp;
import ImageUtil.CroppedImage;
import ImageUtil.Format_Type;
import ImageUtil.ImageLoader;
import JournaLIO.JournaLIOLogic_Type;
import JournaLIO.Serializer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.WindowFocusListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.UrlValidator;

//import org.apache.commons.validator.routines.UrlValidator;

public  class InputPage extends JPanel implements Utility, Input, ActionListener
{

    /**
     * @return the DeleteThisEntrySideMenuItemMouseListener
     */
    public static MouseAdapter getDeleteThisEntrySideMenuItemMouseListener() {
        return DeleteThisEntrySideMenuItemMouseListener;
    }

    /**
     * @param aDeleteThisEntrySideMenuItemMouseListener the DeleteThisEntrySideMenuItemMouseListener to set
     */
    public static void setDeleteThisEntrySideMenuItemMouseListener(MouseAdapter aDeleteThisEntrySideMenuItemMouseListener) {
        DeleteThisEntrySideMenuItemMouseListener = aDeleteThisEntrySideMenuItemMouseListener;
    }

    /**
     * @return the DeleteThisPageSideMenuItemMouseListener
     */
    public static MouseAdapter getDeleteThisPageSideMenuItemMouseListener() {
        return DeleteThisPageSideMenuItemMouseListener;
    }

    /**
     * @param aDeleteThisPageSideMenuItemMouseListener the DeleteThisPageSideMenuItemMouseListener to set
     */
    public static void setDeleteThisPageSideMenuItemMouseListener(MouseAdapter aDeleteThisPageSideMenuItemMouseListener) {
        DeleteThisPageSideMenuItemMouseListener = aDeleteThisPageSideMenuItemMouseListener;
    }

    /**
     * @return the ViewEntriesSideMenuItemMouseListener
     */
    public static MouseAdapter getViewEntriesSideMenuItemMouseListener() {
        return ViewEntriesSideMenuItemMouseListener;
    }

    /**
     * @param aViewEntriesSideMenuItemMouseListener the ViewEntriesSideMenuItemMouseListener to set
     */
    public static void setViewEntriesSideMenuItemMouseListener(MouseAdapter aViewEntriesSideMenuItemMouseListener) {
        ViewEntriesSideMenuItemMouseListener = aViewEntriesSideMenuItemMouseListener;
    }

    /**
     * @return the PreviousPageLabelMouseListener
     */
    public static MouseAdapter getPreviousPageLabelMouseListener() {
        return PreviousPageLabelMouseListener;
    }

    /**
     * @param aPreviousPageLabelMouseListener the PreviousPageLabelMouseListener to set
     */
    public static void setPreviousPageLabelMouseListener(MouseAdapter aPreviousPageLabelMouseListener) {
        PreviousPageLabelMouseListener = aPreviousPageLabelMouseListener;
    }

    /**
     * @return the NextPageLabelMouseListener
     */
    public static MouseAdapter getNextPageLabelMouseListener() {
        return NextPageLabelMouseListener;
    }

    /**
     * @param aNextPageLabelMouseListener the NextPageLabelMouseListener to set
     */
    public static void setNextPageLabelMouseListener(MouseAdapter aNextPageLabelMouseListener) {
        NextPageLabelMouseListener = aNextPageLabelMouseListener;
    }

/**
 * Create the panel.
 */
 //private static Stage thisStage;
private transient KeyAdapter NextLineKeyListener;    
private transient MouseAdapter SaveThisEntryMenuItemMouseListner; 
private transient MouseAdapter InputTitleMouseListener;
private static  MouseAdapter DeleteThisEntrySideMenuItemMouseListener;
private static  MouseAdapter DeleteThisPageSideMenuItemMouseListener;
private static   MouseAdapter ViewEntriesSideMenuItemMouseListener;
private transient  MouseAdapter MenuIconMouseListener;
private static  MouseAdapter PreviousPageLabelMouseListener;
private static MouseAdapter NextPageLabelMouseListener;
private transient MouseAdapter PhotoAttachmentDeleteIconMouseListener;
private transient ActionListener LocalFileSystemActionListener;
private transient ActionListener URLOptionActionListener;
private transient WindowFocusListener AttachmentMenuWindowLIstener;
private transient CaretListener InputCaretLIstener;
 private  static JPanel SideMenu;
 private static JLabel ViewEntriesSideMenuItem;
 private static JLabel DeleteThisEntrySideMenuItem;
 private static JLabel DeleteThisPageSideMenuItem;
 private static JLabel SaveThisEntryMenuItem;
 private JPanel MenuIcon;
 private Drawing[] MenuDashes;
 private ArrayList<String> Tags;
 private String Untitled = "Untitled";
private static String EntryName = "";
private ArrayList<InputLine> Input;
private int StartingPxlYPoint = 50;
private int Index; 
private static Drawing NextPageArrow;
private static Drawing PreviousPageArrow;
private static JPanel NextPageLabel;
private static JPanel PreviousPageLabel;
private boolean EndOfPage = false;
public static int CurrentPage = 0;
public static int CurrentLine = 0;
private StringBuilder[] Content;
private JLabel NextButton;
private JLabel PreviousButton;
private JPanel TopUtility;
private JLabel DoneButton;
private JPanel AttachmentPanel;
private JLabel AttachmentIcon;
private JLabel AttachmentInstructionDescription;
public static FileBrowser NativeBrowser;
private Drawing RedLine;
private JSeparator[] BlueLine;
private static JPanel BottomUtilityPanel;
private static JLabel CurrentPageDescription ;
private static JLabel CurrentLineDescription;
private static JLabel NumberOfWordsDescription;
private static JLabel NumberOfPhotosDescription;
private static ArrayList<Drawing> BottomUtilityLine;
private static Drawing BottomHLine;
static int NumberOfPhotos = 0;
int words = 0;
private Drawing PhotoView;
private ArrayList<Drawing> Photos;
private static InputTagSystem TagPanel;
private static  JLabel TagPic;
private static Drawing HashTag;
private static JButton testButton;
private Timer thisTimer;
//private volatile JFXPanel FXpane;
private transient volatile Runnable FileBrowserThread;
private transient volatile Runnable MediaPlayerThread;
private  transient volatile  Thread FXThread;
private transient JournalMediaPlayer thisMediaPlayer;
private JPopupMenu AttachmentMenu;
private ArrayList<JMenuItem> AttachmentOptions;
private JMenuItem AudioAttachmentOption;
private JMenuItem PhotoAttachmentOption;
private JMenuItem VideoAttachmentOption;
private JMenuItem LocalFileSystem; 
private JMenuItem URLOption;
private int WidthOfPhoto = 300;
private transient static volatile Runnable StageInit;
private ArrayList<PhotoAttachment> PhotoAttachments;
private static File ReturnedFile;
private static FileNameExtensionFilter PhotoFilter;
private static FileNameExtensionFilter VideoFilter;
private static FileNameExtensionFilter AudioFilter;
private Drawing XDrawing;
private JPanel TransparentPanel;
private JTextPane Title;
private JScrollPane TitlePane;
 private ImageIcon AttachmentPic;
 private final static long serialVersionUID = 0; 
 private EntryPage thisEntryPage;   //this holds the data to be serialized about this InputPage
 private boolean Date; // Decides whether or not this InputPage needs a date.
 public static int AddedCurrentLine = 0; // represents the calculated current line of all of the pages added together 
 public static int CurrentNumberOfPhotos  = 0;
 

static
{
   setPhotoFilter();
   //setStageInit();
   setNativeBrowser();
   
}
{
    setPhotos();
    setPhotoAttachments();
    //this.setFocusable(true);
    this.getActionMap().put("Escape", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {
        if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified)
                 {
                     saveModifiedEntryWarning();
                 }
        else if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.New)
        {
           saveNewEntryWarning();
        }
                 else
                 {
                    Journal.getInstance().showMyGreet();
                 }    
    
    }
    });
     this.getActionMap().put("Save", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
          try {
              saveThisEntry();
          } catch (IOException ex) {
              Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    });
      this.getActionMap().put("Delete", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
        deleteThisEntry();
    }
    });
      this.getActionMap().put("HidePhotoView", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
       hidePhotoView();
    }
    });
    this.getActionMap().put("ShowPhotooView", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
       showPhotoView();
    }
    });
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),"Escape");
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"Save");
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"Delete");
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"HidePhotoView");
    this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"ShowPhotooView");        
}
/**
 * This is a copy constructor. It is meant to copy the data from entry and instantiate a new  InputPage with it.
 * @param entry The Entry object that contains the data.
 */
public InputPage(EntryPage entry, boolean date) throws IOException
{
        this.Date = date;
        this.thisEntryPage = entry;
         setTransparentJPanel();
        setXDrawing();
//        setFXThread();
//        System.out.println("I bet the exeception happens right after this ;)");
	setBlueLine();
        setContent();
        setBorder(null);
	setBackground(Color.WHITE);
	setBounds(0, 0,JournalKit.Instance.Width, JournalKit.getInstance().Height-100);
	//add(panel);
	setLayout(null);
        setStartingPxlYPoint();
	setCaretListener();
	setLines(entry);
        setStartingPxlYPoint();
	setCaretListener();
	//setTopUtility();
	//setSaveButton();
	setNextPageButton();
	setRedLine();
	setPreviousPageButton();
	setCurrentLine();
        if(entry.getAttachment() == null)
        {
            setAttachmentPanel();
            setMenu();
        }
        else
        {
            setAttachmentPanel();
            setMenu();
            AttachmentPanel.remove(AttachmentIcon);
            AttachmentPanel.remove(AttachmentInstructionDescription);
            PhotoAttachments.add(0 ,new PhotoAttachment(entry.getAttachment(), Path_Type.Entry));            
            PhotoAttachments.get(0).getPhoto().setLocation(40, 100);            
            PhotoAttachments.get(0).getPhoto().getDeleteIcon().addMouseListener(getPhotoAttachmentDeleteIconMouseListener());
                        getAttachmentPanel().add(getPhotoAttachments().get(0).getPhoto());
                        getAttachmentPanel().updateUI();
        }
        setPhotoView();
        setMenuIcon();
	startTimer();}
public InputPage(boolean date)
	{
	//Thread x  = new Thread();
        this.Date = date;
	init();
	}

public void init()
{    
   
    setTransparentJPanel();
        setXDrawing();
//        setFXThread();
//        System.out.println("I bet the exeception happens right after this ;)");
	setBlueLine();
	setContent();
//        Platform.runLater(InputPage.getStageInit());
        setFileBrowserThread();
  //      setMediaPlayerThread();
EntryName = "Junior";
//	setForeground(Color.WHITE);
//	setBackground(Color.WHITE);
//	setBounds(0, 0, JournalKit.getInstance().Width, JournalKit.getInstance().Height);
//	setLayout(null);
//	setNativeBrowser();
//	panel = new JPanel();
	setBorder(null);
	setBackground(Color.WHITE);
	setBounds(0, 0,JournalKit.Instance.Width, JournalKit.getInstance().Height-100);
	//add(panel);
	setLayout(null);
//	 NextButton = new JLabel("");
//	 NextButton.addMouseListener(new MouseAdapter() {
//	 	@Override
//	 	public void mousePressed(MouseEvent e) 
//	 	{
//	 		NextButton.setEnabled(false);
//	 	}
//	 	public void mouseReleased(MouseEvent e)
//	 	{
//	 		NextButton.setEnabled(true);
//	 	}
//	 });
//	NextButton.setBounds(365, 555, 56, 44);
//	//panel.add(NextButton);
//	//NextButton.setIcon(new ImageIcon(InputPage.class.getResource("/Images/NextPageButton-3.jpg")));
//	NextButton.addMouseMotionListener(new MouseMotionAdapter() {
//		@Override
//		public void mouseMoved(MouseEvent e) 
//		{}
//	});
	setStartingPxlYPoint();
	setCaretListener();
	setLines();
	//setTopUtility();
	//setSaveButton();
	setNextPageButton();
	setRedLine();
	setPreviousPageButton();
	setCurrentLine();
	setAttachmentPanel();
        setMenu();
	setPageNavagation();
	setPhotoView();
        setMenuIcon();
	//Journal.getInstance().setSize(JournalKit.getInstance().MaxSize);
	startTimer();
}
/**
 * This is a copy constructor. The purpose of this is to smooth the process of serialization. This method accomplishes that copying the data that the entry contains to this inputPage. 
 * @param entry 
 */
public void setLines() 
{
	setIndex();
	Input = new ArrayList<InputLine>(JournalKit.getInstance().NumberofInputLines);
	while(Index<JournalKit.getInstance().NumberofInputLines)
	{
		Input.add(Index, new InputLine());                 
		Input.get(Index).getContent();
		//Input.get(Index).startInputLineThreadManager();
		// the first line will ALWAYS do this for the current date formatting 
		if(Index == 0 && Date)
		{                        
			Input.get(Index).formatLine();
			Input.get(Index).getTextPane().setEditable(false);
		                
                }                    
                
		Input.get(Index).setLocation(50,StartingPxlYPoint);
		add(Input.get(Index));
		Input.get(Index).setVisible(true);		
		BlueLine[Index].setLocation(0, StartingPxlYPoint+17);
		add(BlueLine[Index]);
		if(Index ==0)
                {
                    if(!Date)
                    {
		setNextLineAction();
                    }
                    
                }
                else
                {
                    setNextLineAction();
                }
		StartingPxlYPoint+=25;
		Debug.Print(Index);
		if(Index == JournalKit.getInstance().NumberofInputLines-1)
		{
			Debug.Print("adding caret to the end of page");
			final int k = Index;
                     setInputCaretLIstener(new CaretListener()
                     {
                         public void caretUpdate(CaretEvent e)
                         {
                             if(Input.get(k).getText().length() == InputLine.MaxCharacters)
                             {
                                 EndOfPage = true;
                                 Debug.Print(" end of page:"+EndOfPage);
                             }
                             
                         }
                         
                     });
			Input.get(k).getTextPane().addCaretListener(getInputCaretLIstener());
		}
             //   Input.get(Index).addMouseListener(InputTitleMouseListener);
		Input.get(Index).setLineNum(Index+1);
		Index++;
	}
}
/**
 * This version of setLines prepares the lines of this entry with the content of EntryPage e
 * @param e The entry page that had the content to be copied to this InputPage
 */
public void setLines(EntryPage e)
{
	setIndex();
	Input = new ArrayList<InputLine>(JournalKit.getInstance().NumberofInputLines);
	while(Index<JournalKit.getInstance().NumberofInputLines)
	{
		Input.add(Index, new InputLine());
		//Input.get(Index).startInputLineThreadManager();
		// the first line will ALWAYS do this for the current date formatting 
		if(Index == 0 && this.Date)
		{                        
			Input.get(Index).formatLine(Journal.getInstance().getCurrentEntry().getDate());
			Input.get(Index).getTextPane().setEditable(false);		                
                } 
                if(e.getContent() != null)
                {
               if(Index<e.getContent().size())
               {
               	Input.get(Index).getTextPane().setText(e.getContent().get(Index));
               }
                }
		Input.get(Index).setLocation(50,StartingPxlYPoint);
		add(Input.get(Index));
		Input.get(Index).setVisible(true);		
		BlueLine[Index].setLocation(0, StartingPxlYPoint+17);
		add(BlueLine[Index]);
		if(Index !=0)
		setNextLineAction();
		StartingPxlYPoint+=25;
		Debug.Print(Index);
		if(Index == JournalKit.getInstance().NumberofInputLines-1)
		{
			Debug.Print("adding caret to the end of page");
			final int k = Index;
                     setInputCaretLIstener(new CaretListener()
                     {
                         public void caretUpdate(CaretEvent e)
                         {
                             if(Input.get(k).getText().length() == InputLine.MaxCharacters)
                             {
                                 EndOfPage = true;
                                 Debug.Print(" end of page:"+EndOfPage);
                             }
                             
                         }
                         
                     });
			Input.get(k).getTextPane().addCaretListener(getInputCaretLIstener());
		}                
                Input.get(Index).getTextPane().addMouseListener(InputTitleMouseListener);
		Input.get(Index).setLineNum(Index+1);
		Index++;
	}
}
/**
 * @return The JTextPane that belongs to InputLine index 
 */
public JTextPane getLine(int index)
{
	return Input.get(index).getTextPane();
}
public InputLine getInputLine(int index)
{
	return Input.get(index);
}
public void calcNumberOfInputLines()
{
	
}
public void setInputPage()
{
	
}
/**
 * This method sets the behavior of the text and the lines when moving to the next line.
 * This method SHOULD have keyReleased and keyPressed Overridden! 
 */
public void setNextLineAction()
{
	final int i = Index;
             setNextLineKeyListener(new KeyAdapter()
             {
                 public void keyReleased(KeyEvent e)
                 {
                     System.out.println("Number of Characters :" + Input.get(CurrentLine).getText().length());
                     System.out.println("good");
                     System.out.println(Input.get(i).getTextPane().getCaret().getMagicCaretPosition());
                     if((Input.get(i).getContent().length()>= InputLine.MaxCharacters)  && (Input.get(i).getLineNum() != 22))
                     {
                         nextLine(i);
                     }
                     else if((Input.get(i).getContent().length() + Input.get(i).getTabSize()>= InputLine.MaxCharacters)  && (Input.get(i).getLineNum() == 22))
                     {
                         JournalKit.nextPage();
                     }
                     System.out.println("line :" + CurrentLine);
                     System.out.println("Number of Characters :" + Input.get(CurrentLine).getText().length());
//	 if(e.getKeyCode() == KeyEvent.VK_TAB)
//	 {
//			System.out.println("this line has " + getThis().getInput().get(CurrentLine).getTextPane().getText().length());
//			final int Constant = Input.get(CurrentLine).getContent().length()-Input.get(CurrentLine).getLastWord().length();
//		Input.get(CurrentLine).delete(Constant,Input.get(CurrentLine).getContent().length() );
//		Input.get(CurrentLine).getTextPane().setText(JournalKit.TabInSpaces);
//	 }
updatePageContent();
                 };
                 public void keyPressed(KeyEvent e)
                 {
                     //updatePageContent();
                     System.out.println(getThis().getInput().size());
                     //Input.get(CurrentLine).getTextPane().setText(Input.get(CurrentLine).cleanTabs(Input.get(CurrentLine).getText(), InputLine.MaxCharacters));
                     System.out.println("the size of Input: " + Input.size());
                  //   System.out.println(Input.get(InputPage.CurrentLine).getText());
                     //Input.get(CurrentLine).getTextPane().setText(Input.get(CurrentLine).cleanTabs(Input.get(CurrentLine).getText(), InputLine.MaxCharacters));
                     if((e.getKeyCode() == KeyEvent.VK_ENTER) && Input.get(i).getLineNum() != Input.size())
                     {
                         nextLine(i);
                         updatePageContent();
                     }
                     else if((e.getKeyCode() == KeyEvent.VK_ENTER) && Input.get(i).getLineNum() == Input.size())
                     {
                         //nextPage();
                         updatePageContent();
                     }
                     else if((e.getKeyCode() == KeyEvent.VK_DOWN) && (Input.get(i).getLineNum() != Input.size()))
                     {
                         nextLine(i);
                         updatePageContent();
                     }
                     else if((e.getKeyCode() == KeyEvent.VK_UP) && (Input.get(i).getLineNum() != 0))
                     {
                         previousLine(CurrentLine);
                         updatePageContent();
                     }
                     else if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (Input.get(i).getLineNum() == Input.size()))
                     {
                         JournalKit.nextPage();
                         updatePageContent();
                     }
                     
                     //	else if((e.getKeyCode() == KeyEvent.VK_TAB) && (Input.get(i).getLineNum() != Input.size()) && (Input.get(i).isItFull()))
//	{
//		System.out.println("Tab Pressed : " + Input.get(0).getContent().length());
//		nextLine(i);
//		updatePageContent();
//	}


                 }
                 
             });
Input.get(i).getTextPane().addKeyListener(getNextLineKeyListener());
}
public void setStartingPxlYPoint() 
{
	StartingPxlYPoint = 50;
}
public void setIndex()
{	
	Index = 0;
}
//public void setSaveButton() 
//{
//	SaveButton.setIcon(SavePic);
//	SaveButton.addMouseListener(new MouseAdapter() {
//		@Override
//		public void mouseClicked(MouseEvent e) 
//		{
//			for(int i = 0;i<NumberofEntryPages;i++){
//			if(Input.get(i).hasText())
//			{
//				Input.get(i).getText();
//			}
//			}
//		}
//	});
//	SaveButton.setBounds(3, 692, 86, 51);
//	add(SaveButton);
//}

public JLabel getNextButton() {
	return NextButton;
}
public JLabel getPreviousButton() {
	return PreviousButton;
}
public void setClassId()
{
	
}

public void writeData(String data)
{
	
}

public void setCaretListener()
{
//	Input.get(Input.size()-1).getTextPane().addKeyListener(new KeyAdapter() {
//		public void keyPressed(KeyEvent e) 
//		{
//			if(Input.get(Input.size()-1).isEndofLine())
//			{
//				Debug.Print("last line");
//				InputPage.nextPage();
//				
//			}
//		}
//	});;
}



public InputPage getPage(int index) 
{
	// TODO Auto-generated method stub
	return null;
}
public void setStatus(Object o) {
	// TODO Auto-generated method stub
	
}
public void setContent()
{
	Content = new StringBuilder[JournalKit.getInstance().NumberofInputLines];
	for(int j = 0; j<Content.length;j++)
	{
		Content[j] = new StringBuilder();
	}
}
public void setContent(ArrayList<String> content)
{
    Content = new StringBuilder[JournalKit.getInstance().NumberofInputLines];
	for(int j = 0; j<Content.length;j++)
	{
		Content[j] = new StringBuilder();
	}
        if(!content.isEmpty())
        {
        for(int i =0 ;i<content.size();i++)
        {
        Content[i].append(content.get(i));
        }
        }
}
public StringBuilder getContentofLine(int Line)
{
	return Content[Line];
}
public void updateContent()
{
	
}
public StringBuilder getContent()
{
StringBuilder data = new StringBuilder();
for(int k = 0;k<Content.length;k++)
{
	if(Input.get(k).hasText())
	{
		data.append(Input.get(k).getContent());
		data.append("\n");
		
	}
}
return data;
}
public ArrayList<String> getLinesContent()
{
    ArrayList<String> data = new ArrayList<>();
    for(int k = 0;k<Content.length;k++)
{
	if(Input.get(k).hasText())
	{
		data.add(Input.get(k).getContent().toString());
		
	}
}
    return data;
}
public static void updateCurrentLine() 
{
    CurrentLine = 0;
    AddedCurrentLine = 0;
	for(int j = 0;j<JournalKit.getInstance().NumberofInputLines;j++)
	{
		if(Journal.getInstance().getPage(InputPage.CurrentPage).getInputLine(j).getStatus())
		{
			InputPage.CurrentLine  = j;
                        AddedCurrentLine = j;
		}
	}	
        if(CurrentPage>0)
        {
            int AllLines = 0;
            System.out.println("_____________________________________");
            for(int i =0;i<CurrentPage;i++)
            {
                AllLines += 22;
                System.out.println("All lines: " + AllLines);
            }
            AddedCurrentLine = AddedCurrentLine + AllLines;
        }
        	//System.out.println("Current Line " + CurrentLine );
}
/**
 * This method brings the caret to the next line. This also handles any text on the next line and the current one. 
 * @param LineNum The current line where the caret is at
 */
public void nextLine(int LineNum)
{
	Input.get(LineNum+1).setStatus(true);
	InputPage.updateCurrentLine();
	final int index  = LineNum;
	if(Input.get(index).passToNextLine())
	{
		if(Input.get(index+1).hasText())
		{
			//cleanUpNextLine(LineNum);
			String LineText = Input.get(index+1).getText();
			Input.get(index+1).getTextPane().setText( LineText + Input.get(index).getLastWord());
			Input.get(index).deleteWord(Input.get(index).getLastWord());
		

		}
		else{
		Input.get(index+1).getTextPane().setText(Input.get(index).getLastWord());
		Input.get(index).deleteWord(Input.get(index).getLastWord());
		}
		goToLine(index, index+1);
	}
	else
	{
		goToLine(index ,index+1);
	}
}
/**
 * This method will take the caret to the previous line
 * @param LineNum the current where the caret is at
 * @note: This method assumes that LineNum is an Line Number and NOT and INDEX 
 */
public void previousLine(int LineNum)
{
	if(LineNum>=1)
	{
	Point point = this.getInputLine(LineNum).getTextPane().getLocationOnScreen();
	point.setLocation(point.x + (this.getInputLine(LineNum).getSize().width-50), point.y-10);
	JournalKit.getInstance().mouseClick(point);
        this.getInput().get(LineNum).setStatus(false);
        this.getInput().get(LineNum-1).setStatus(true);
	}
}

/**
 * This method will make sure that there is enough space to move text to the next line.
 * This method will also handle any text moving between the lines. 
 * @param CurrentLineNum
 */
private void cleanUpNextLine(int CurrentLineNum)
{
	int NextLine = CurrentLineNum;
	String NextLineText = "";
	if(Input.get(NextLine).isItFull())
	{
		System.out.println("Text obtained from line number:" + NextLine);
		NextLineText  = Input.get(NextLine).getContent().toString();
		Input.get(NextLine).clearAll();
		cleanUpNextLine(CurrentLineNum + 1);
	}
	System.out.println("setting the text to line number: " +(NextLine + 1 ));
	Input.get(NextLine + 1).getTextPane().setText(NextLineText);;
}
/**
 * This method will calculate weather there is enough space on the next Line.
 * @param CurrentLineNum The current Line NUMBER where the caret is at.
 * @return True, if there is enough space on the next line. Otherwise, it will return false
 */
//private boolean isThereEnoughSpace(int CurrentLineNum)
{
	
}
public void goToLine(int CurrentLine, int Destination)
{
	if(CurrentLine<Destination){
	for(int i = CurrentLine;i<Destination;i++)
	{
		Input.get(i).getTextPane().setEnabled(false);
		Input.get(i).getTextPane().setEnabled(true);
		if(Input.get(i).hasText())
		{
			Input.get(i).getTextPane().getCaret().setMagicCaretPosition(new Point(Input.get(i).getText().length(), 0));
		}
		else
		{
			Input.get(i).getTextPane().getCaret().setMagicCaretPosition(new Point());
		}
	}
	}
	else
	{
//		for(int k = CurrentLine;k<JournalKit.getInstance().NumberofInputLines;k++)
//		{
//			Debug.Print("loops is happenning +++++++++");
//			Input.get(k).getTextPane().setEnabled(false);
//			Input.get(k).getTextPane().setEnabled(true);
//			if(k == (JournalKit.getInstance().NumberofInputLines - 1))
//			{
//				for(int j =0;j<Destination;j++)
//				{
//					Input.get(j).getTextPane().setEnabled(false);
//					Input.get(j).getTextPane().setEnabled(true);
//				}
//			}
//		}
	}
	if(Input.get(Destination).hasText())
	{
		Input.get(Destination).getTextPane().getCaret().setDot(Input.get(Destination).getText().length());
	}
	else
	{
		Input.get(Destination).getTextPane().getCaret().setMagicCaretPosition(new Point());
		Input.get(Destination).getTextPane().getCaret().setDot(0);
	}
}
public void setTopUtility()
{
	TopUtility =new JPanel();
	TopUtility.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println("hello ");
		}
	});
	JSeparator line = new JSeparator(); 
	line.setBounds(0, 20, JournalKit.getInstance().Width, 8);
	line.setVisible(true);
	TopUtility.add(line);
	add(TopUtility);
	//add(line);
	TopUtility.setBackground(Color.WHITE);
	TopUtility.setBounds(0, 0,JournalKit.getInstance().Width, 25);
	TopUtility.setVisible(true);
}

@Override
public  String toString()
{
	return EntryName;
}
public  void setEntryName(String name)
{
	EntryName = name;
}
public  String getEntryName()
{
	return EntryName;
}

public void setAttachmentPanel()
{
	AttachmentPanel = new JPanel();
        AttachmentPic = JournalKit.getAttachmentIcon();
	AttachmentPanel.setBackground(Color.white);
	AttachmentPanel.setBounds(JournalKit.Instance.MinimumSize.width+2, 0, JournalKit.getInstance().MaxSize.width -JournalKit.Instance.MinimumSize.width, JournalKit.getInstance().Height);
	AttachmentIcon = new JLabel();
	AttachmentInstructionDescription = new JLabel("Click on The Add Button to Attach items");
	Font DescriptionFont = new Font(Font.DIALOG, Font.PLAIN, 15);
	AttachmentInstructionDescription.setFont(DescriptionFont);
	AttachmentInstructionDescription.setBounds(40,AttachmentPanel.getSize().height/2-75,AttachmentPanel.getSize().width,20);
	AttachmentPanel.setLayout(null);
	AttachmentIcon.setIcon(AttachmentPic);
	AttachmentPanel.add(AttachmentIcon);
	AttachmentPanel.add(AttachmentInstructionDescription);
	AttachmentIcon.setBounds(AttachmentPanel.getSize().width/2-50, AttachmentPanel.getSize().height/2-50 , AttachmentPic.getIconWidth(), AttachmentPic.getIconHeight());	
	AttachmentIcon.addMouseListener(new MouseAdapter()
	{
		public   void  mouseClicked(MouseEvent e)
		{
                   AttachmentMenu.show(AttachmentIcon, 0, 0);
               } 
                      
                    
                        
		
		
	});
        AttachmentPanel.setVisible(true);
	getThis().add(AttachmentPanel);
        System.out.println(AttachmentPanel + "added");
}
	
	
/**
 * TO-DO adding listeners to NativeBrowser will help for easier file handling
 */
public static void setNativeBrowser()
{
	NativeBrowser = new FileBrowser();	
	//NativeBrowser.setMode(FileDialog.LOAD);
	//NativeBrowser.setMultipleMode(true);
}
public static void setNativeBrowser(FileBrowser o)
{
    NativeBrowser = o;
}
public static FileBrowser getNativeBrowser()
{	
	return NativeBrowser;
}
public JPanel getAttachmentPanel()
{
	return AttachmentPanel;
}
public InputPage getThis()
{
	return this;
}
public void setPreviousPageButton() 
{
	
	PreviousPageButton.setIcon(PreviousIcon);
	System.out.println(PreviousPageButton.getSize());
	PreviousPageButton.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e )
		{
			System.out.println("clicked on pre vious pages!!!!");
		}
	});
	//BottomUtility.add(PreviousPageButton);
	PreviousPageButton.setBounds(400,30, PreviousIcon.getIconWidth(),PreviousIcon.getIconHeight());
}
public JLabel getPreviouspageButton() 
{
	return PreviousPageButton;
}
public void setNextPageButton() 
{
	NextPageButton.setIcon(NextIcon);
}
public JLabel getNextPageButton() 
{
	return NextPageButton;
}

public void setRedLine()
{
	RedLine = new Drawing( getHeight(), Drawing_Type.VLine_Type, Color.RED);
	RedLine.setLocation(49,0);
	add(RedLine);
}
public void setBlueLine()
{
	BlueLine = new JSeparator[JournalKit.Instance.NumberofInputLines];
	for(int i = 0;i<JournalKit.Instance.NumberofInputLines;i++)
	{
	BlueLine[i] = new JSeparator();
	BlueLine[i].setForeground(SystemColor.textHighlight);
	BlueLine[i].setSize(48 , JournalKit.getInstance().LineSepratorHeight);
	} 
	
}
public static void setBottomUtility() throws InterruptedException, BadLocationException 
{
BottomUtilityPanel = new JPanel();
BottomUtilityPanel.setLayout(null);
BottomUtilityPanel.setBounds(0,Journal.getInstance().getHeight()-100 , JournalKit.Instance.MinimumSize.width, 100);
//System.out.println(test);
setBottomUtilityLine();
setCurrentPageDescription();
setCurrentLineDecsription();
setNumberOfWordsDescription();
setNumberOfPhotosDescription();
setBottomHLine();
setHashTag();
setTagPanel();
 setNextPageLabel();
 setPreviousPageLabel();
 getBottomUtility().add(getNextPageLabel());
  getBottomUtility().add(getPreviousPageLabel());
//setTagPic();
//while(!InputPage.getTagPic().isVisible())
//{
//	Thread.sleep(100);
//}
//BottomUtilityPanel.addMouseListener(new MouseAdapter()
//{
//	public void mouseClicked(MouseEvent e)
//	{
//		System.out.println("bottom utility" +e.getPoint());
//	}
//});
//TagPic.setVisible(true);
setTestButton();
//test();
}
public static  JPanel getBottomUtility()
{
	return BottomUtilityPanel;
}
//public void setFirstStatLine()
//{
//	Status = new Drawing(JournalKit.getInstance().MinimumSize.width, 40, Drawing.StatusBar);
//	Status.setFirstStatus();
//	//firstStatLine.setSize(1000, 100);
//	//firstStatLine.setLocation(200,0);
//	Status.setLocation(BottomUtilityPanel.getWidth()/2, BottomUtilityPanel.getHeight()/2);
//	Status.addMouseListener(new MouseAdapter()
//	{
//		public void mouseClicked(MouseEvent e)
//		{
//			System.out.println("status bar");
//		}
//	});
//}
public static void setCurrentPageDescription() 
{
CurrentPageDescription = new JLabel("Current Page: " + (InputPage.CurrentPage + 1) + " of " + (JournalKit.NumberOfPages)) ;
CurrentPageDescription.setBounds(1, 62,143,15);
BottomUtilityPanel.add(CurrentPageDescription);
getBottomUtilityLine(0).setLocation(150, 52);
BottomUtilityPanel.add(getBottomUtilityLine(0));
}
public static JLabel getCurrentPageDescription()
{	
	return CurrentPageDescription;
}

public static void setCurrentLineDecsription() 
{
CurrentLineDescription = new JLabel("Current Line: " + InputPage.CurrentLine);
CurrentLineDescription.setBounds(160, 62, 145, 15);
BottomUtilityPanel.add(CurrentLineDescription);
getBottomUtilityLine(1).setLocation(290, 52);
BottomUtilityPanel.add(getBottomUtilityLine(1));
}
public static JLabel getCurrentLineDescription() 
{	
	return CurrentLineDescription;
}
public  static void setNumberOfWordsDescription() 
{
	NumberOfWordsDescription = new JLabel( JournalKit.getNumberOfWords() + " Words");
	NumberOfWordsDescription.setBounds(300,62, 88,15);
	BottomUtilityPanel.add(NumberOfWordsDescription);
	getBottomUtilityLine(2).setLocation(390, 52);
	BottomUtilityPanel.add(getBottomUtilityLine(2));
}
public static JLabel getNumberOfWordsDescription() 
{
	return NumberOfWordsDescription;
}
public static void setNumberOfPhotosDescription() 
{
	NumberOfPhotosDescription = new JLabel(InputPage.NumberOfPhotos + " Photos");
	NumberOfPhotosDescription.setBounds(400, 62, 78, 15);
	BottomUtilityPanel.add(NumberOfPhotosDescription);
	getBottomUtilityLine(3).setLocation(375,52);
	//BottomUtilityPanel.add(getBottomUtilityLine(3));
}

public static JLabel getNumberOfPhotosDescrption() 
{
	return NumberOfPhotosDescription;
}
public static void setBottomHLine()
{
	BottomHLine = new Drawing(JournalKit.Instance.MinimumSize.width, Drawing_Type.HLine_Type, Color.LIGHT_GRAY );
	BottomHLine.setLocation(0, 50);
	BottomUtilityPanel.add(BottomHLine);
}
public static void setBottomUtilityLine()
{
	BottomUtilityLine = new ArrayList<Drawing>();
	for(int i = 0; i<=3;i++)
	{
		BottomUtilityLine.add(i, new Drawing( 25, Drawing_Type.VLine_Type, Color.LIGHT_GRAY ));
	}
}
public static Drawing getBottomUtilityLine(int i)
{
	return BottomUtilityLine.get(i);
}
public  int getNumberOfWords()
{
    		words = 0;
	if(Input != null)
	{
	for(int i = 0; i<JournalKit.getInstance().NumberofInputLines;i++)
	{
		//System.out.println("line count: " + Input.get(i).getWordCount());
		words+=Input.get(i).getWordCount();
		//System.out.println( "page count" +words);	
		//System.out.println(Input.get(i).getTextPane().getCaret().isVisible() + "line number:" + i);
	}
	}
      //  System.out.println("number of words function: " + words );
	return words;
}
public  void setCurrentLine()
{
	for(int k = 0;k<JournalKit.getInstance().NumberofInputLines;k++)
	{
		final int x = k;
		Input.get(k).getTextPane().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				for(int i = 0;i<JournalKit.getInstance().NumberofInputLines;i++)
				{
					if(i == x)
					{
						Input.get(i).setStatus(true);
					}
					else
					{
						Input.get(i).setStatus(false);
					}
				}
				System.out.println( "Status" +Input.get(x).getStatus());
                                
                                if(Title != null)
                                {
                                  if(Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().getText().isEmpty())
                {
                    System.out.println("title is empty");
                    Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().setText(Untitled);
                }
			}
                        }
		});
	}

}
public void updatePageContent()
{
	for(int j = 0;j<Input.size();j++)
	{
		Input.get(j).updateLineContent();
	}
}
public void actionPerformed(ActionEvent e) 
{
        JournalKit.TrashObjects();
        updateCurrentNumberOfPhotos();
	updatePageContent();
        updateCurrentLine();
        getThis().updateUI();        
        AttachmentPanel.updateUI();
        if(Journal.getInstance().getCurrentEntry() != null)
        {
           // System.out.println(Journal.getInstance().getCurrentEntry().getEntryName());
        }
        if(getTitle() != null)
        {
            setEntryName(getTitle().getText());
            //System.out.println( "Entry name:  " +Journal.getInstance().getCurrentEntry());
        }
	//System.out.println("updating page...");
        if(CurrentPage == 0)
        {
            InputPage.getPreviousPageLabel().setVisible(false);
        }
        else
        {
                        InputPage.getPreviousPageLabel().setVisible(true);
        }
        if(null != Journal.getInstance().getCurrentEntry().getEntryState())
        switch (Journal.getInstance().getCurrentEntry().getEntryState()) 
        {
            case Saved:
                Journal.getInstance().setStatus(getThis() + "(saved)");
                break;
            case Modified:
                Journal.getInstance().setStatus(getThis() + "(Edited)");
                break;
            default:
            {
                System.out.println("I bet is this " + ((Object)(getThis())) +"Current Page: " + InputPage.CurrentPage);
                Journal.getInstance().setStatus(getThis());
                 break;
            }
        }
        if(InputPage.getTagPanel().getAllTags().isEmpty() && !InputPage.getTagPanel().isActivated())
        {
         InputPage.getTagPanel().getAddTagInstruction().setVisible(true);
        }
        else if(!InputPage.getTagPanel().getAllTags().isEmpty())
        {
                     InputPage.getTagPanel().getAddTagInstruction().setVisible(false);
        }
        if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MinimumSize))
        {
        PhotoView.setDrawText("+");
        PhotoView.repaint();
        }
        else if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MaxSize))
        {
          PhotoView.setDrawText("-");
          PhotoView.repaint();
        }
        
}
public void setPageNavagation()
{
	this.addMouseWheelListener(new MouseWheelListener() {
		public void mouseWheelMoved(MouseWheelEvent e) 
		{
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
			{
				//System.out.println(e.getUnitsToScroll());
				String x = Integer.toString(e.getScrollType());
				System.out.println("scrolling.........." + e.getUnitsToScroll());
				if(e.getUnitsToScroll() > 20)
				{
					//System.out.println("Current Page After Scroll: " + InputPage.CurrentPage);
				//	JournalKit.nextPage();
				}
				else
				{
					
				}
			}
		}
	});
	addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			System.out.println("dragged "+e.getClickCount());
		}
	});
}

public void setEntry() {
	// TODO Auto-generated method stub
	
}
public void setAuthorName(String name) {
	// TODO Auto-generated method stub
	
}
public String getAuthorName() {
	// TODO Auto-generated method stub
	return null;
}
public void setTags(ArrayList<String> Tags) {
	// TODO Auto-generated method stub
	
}
public ArrayList<String> getTags() {
	// TODO Auto-generated method stub
	return null;
}
public void addTag(String Tag) {
	// TODO Auto-generated method stub
	
}
public void setPhotoView()
{
	String bullet = "\n\u2022";
	final String dash = "U+0304";
	PhotoView  = new Drawing("+", Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 50, 50);
	PhotoView.setLocation(435, 20);
	this.add(PhotoView);
	PhotoView.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MaxSize))
			{
			hidePhotoView();

			}
			else
			{
                            showPhotoView();
			}
			PhotoView.repaint();
		}
		
	});
}
public static void setTagPanel()  
{
	TagPanel = new InputTagSystem(275,35);
	TagPanel.setLocation(40, 10);
	TagPanel.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("input page works");
			System.out.println(TagPanel.getTagManager().getLocation());
		}
	});
	BottomUtilityPanel.add(TagPanel);
	//TagPanel.getTagInputManager().setText("Add Tags...");
	//TagPanel.invalidate();
	//TagPanel.deactivate();
	//TagPanel.wait();
	//Journal.getInstance().setSize(JournalKit.getInstance().MinimumSize.width, JournalKit.getInstance().MinimumSize.height);
	
}
public static InputTagSystem getTagPanel()
{
	return TagPanel;
}
public void setSaveButton()
{
	
}
public JLabel getSaveButton()
{
	return null;
}
//public static void setTagPic();
//{
//	TagPic = new JLabel(TagIcon);
//	TagPic.setLocation(0,-25);
//	TagPic.setSize(TagIcon.getIconWidth(), TagIcon.getIconHeight());
//	TagPic.addMouseListener(new MouseAdapter()
//	{
//		public void mouseClicked(MouseEvent e)
//		{
//			System.out.println("tag clicked");
//		}
//		
//	});
//	BottomUtilityPanel.add(TagPic);
//}
public  static JLabel getTagPic()
{
	return TagPic;
}
public static void setHashTag()
{
	HashTag = new Drawing("#" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 30, 60);
	HashTag.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
                    
			System.out.println("drawing clicked");
		}
	});
	HashTag.setLocation(new Point(10,12));
	BottomUtilityPanel.add(HashTag);
}
public static Drawing getHashTag()
{
	return HashTag;
	
}
public static void setTestButton()  
{
	testButton = new JButton("Click");
	testButton.setSize(new Dimension(100,20));
	testButton.setLocation(new Point(370,10));
	testButton.addMouseListener(new MouseAdapter() 
	{
                @Override
		public void mouseClicked(MouseEvent e)
		{   
                    Journal.getInstance().getPage(0).setVisible(false);
		}
	});
	//getBottomUtility().add(testButton);
}
//public   void test()
//{
//	Drawing xIcon = new Drawing(100, 100, 0, 0,Drawing_Type.ImageScaled_Type, new ImageIcon(InputPage.class.getResource("/Images/Trash - Icon-.jpg")).getImage());
//	xIcon.setBounds(300, 5, 100, 100);
//	getBottomUtility().add(xIcon);
//
//}
public ArrayList<InputLine> getInput()
{
return Input;	
}
/**
 * This method sets up an scrolling system for the input page.
 * 
 */
private void setPaging()
{
	this.addMouseWheelListener(new MouseWheelListener()
	{
		//public void mouseWheelMoved(MousWheeleEvent e)
		{
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
}
public int getCurrentLine()
{
	return CurrentLine;
}
private void startTimer()
{
	thisTimer = new Timer(20, this);
	thisTimer.start();
}
public Timer getThisTimer()
{
	return thisTimer;
}
//public void setJavaFX()
//{
//	FXpane = new JFXPanel();
//        
//}
//public JFXPanel getJavaFX()
//{
//    return FXpane;
//}
public void setFileBrowserThread()
{
    FileBrowserThread = new Runnable()
	{
                @Override
		public  void run()
		{
                   {
                       
		
                        
                    }
		}
		
	};
}
public void setFileBrowserThread(Runnable o)
{
    FileBrowserThread = o;
}
public Runnable getFileBrowserThread()
{
	return FileBrowserThread;
}
//public void setMediaPlayerThread()
//{
//    MediaPlayerThread = new Runnable()
//    {
//        public void run()
//        {
//           // thisMediaPlayer = new JournalMediaPlayer(200,200, files.get(0).getAbsolutePath());
//           // FXpane.setScene(thisMediaPlayer.getThisScene());
//            FXpane.setLocation(new Point(AttachmentPanel.getSize().width/2-50, AttachmentPanel.getSize().height/2-50));
//            FXpane.setSize(new Dimension(200,200));
//            getAttachmentPanel().add(FXpane);   
//        }
//    };
//}
public void innerClass() throws Exception
{
}
public void setMenu()
{
    AttachmentMenu = new JPopupMenu();
    AttachmentMenu.setBorderPainted(true);
    
       // Menu.setPopupSize(new Dimension(100,30));
   // Menu.addSeparator();
        setAttachmentMenuWindowLIstener(new WindowFocusListener() {
            @Override public void windowGainedFocus(WindowEvent arg0) {}
            @Override public void windowLostFocus(WindowEvent arg0) {
                AttachmentMenu.setVisible(false);
                Journal.getInstance().removeWindowFocusListener(this);
            }
        });
    setAttachmentOptions();
}
public void setAttachmentOptions()
{
    LocalFileSystem = new JMenuItem("Local File System");
    URLOption = new JMenuItem("URL(Internet) :)");
     AudioAttachmentOption = new JMenuItem("Audio");     
     VideoAttachmentOption = new JMenuItem("Video");    
     PhotoAttachmentOption  = new JMenu("Photo");
     PhotoAttachmentOption.add(LocalFileSystem);
     PhotoAttachmentOption.add(URLOption);
     setPhotoAttachmentDeleteIconMouseListener(new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                System.out.println("file name method:" + PhotoAttachments.get(0).getFile().getName());
                                int answer = JOptionPane.showConfirmDialog(AttachmentPanel, "Are you sure you want to delete this photo?\nThis CANNOT be undone", "Delete Attachment", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,JournalKit.getJournaLogoImage());
                                if(answer == JOptionPane.YES_OPTION)
                                {
                                    PhotoAttachments.get(0).getPhoto().setVisible(false);
                                    System.out.println(AttachmentIcon);
                                    AttachmentPanel.add(AttachmentIcon);
                                    AttachmentPanel.add(AttachmentInstructionDescription);
                                    JournalKit.destroyObject(PhotoAttachments.get(0).getPhoto());
                                    PhotoAttachments.get(0).delete();
                                }
                            }
                            
                        }); 
        setURLOptionActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(AttachmentPanel, "Make sure that you are connected to the INTERNET to load a photo from a link", "Info", JOptionPane.INFORMATION_MESSAGE , JournalKit.getJournaLogoImage());
                    String URL =   (String) JOptionPane.showInputDialog(AttachmentPanel, "Type the URL:", "URL", JOptionPane.QUESTION_MESSAGE, JournalKit.getJournaLogoImage(), null, null);
                    UrlValidator validator = new UrlValidator();
                    while((URL != null) && (!validator.isValid(URL)))
                    {
                        
                        URL =   (String) JOptionPane.showInputDialog(AttachmentPanel, "Invalid URL!\nType the URL:", "URL", JOptionPane.QUESTION_MESSAGE, JournalKit.getJournaLogoImage(), null, null);
                    }           if (URL != null) {
                        CroppedImage test = ImageLoader.fromUrl(URL);
                        HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
                        System.out.println("connection object" + connection);
                        System.out.println( "user interaction" + connection.getAllowUserInteraction());
                        System.out.println("bufferd image: " + test.getBufferedImage());
                        while(test.getBufferedImage()== null)
                        {
                            URL =   (String) JOptionPane.showInputDialog(AttachmentPanel, "The link is valid, but it doesn't conatin any photo\nType a link that DIRECTLY linked to a photo", "URL", JOptionPane.QUESTION_MESSAGE, JournalKit.getJournaLogoImage(), null, null);
                            test = ImageLoader.fromUrl(URL);
                        }        while(connection.getResponseCode() != 200 && JournalKit.isThisURLValid(URL) )
                        {
                            URL =   (String)JOptionPane.showInputDialog(AttachmentPanel, "The link seems valid, but is not authorizing me to load the photo\nType a link that you have authorization of", "URL", JOptionPane.QUESTION_MESSAGE, JournalKit.getJournaLogoImage(), null, null);
                        }        PhotoAttachments.add(0 ,new PhotoAttachment(URL, Path_Type.URL));
                        if(PhotoAttachments.get(0).getFile().length()==0)
                        {
                            JOptionPane.showMessageDialog(AttachmentPanel, "An Error Occur\nMake sure that the link you are prividing is not being proetcted", "Info", JOptionPane.INFORMATION_MESSAGE , JournalKit.getJournaLogoImage());
                            return;
                        }  
                        if(JournalKit.getFormat(URL) == Format_Type.UNKNOWN)
                    {
                        JOptionPane.showMessageDialog(AttachmentPanel, "This file does not have an explicit extension on its name.\n This can cause errors. To avoid errors, include the extension of the file on the file name.", "Warning", JOptionPane.INFORMATION_MESSAGE , JournalKit.getJournaLogoImage());
                    }
                        AttachmentPanel.remove(AttachmentIcon);
                        AttachmentPanel.remove(AttachmentInstructionDescription);
                        System.out.println("size of the attachment Panel: " + getAttachmentPanel().getSize());
                        PhotoAttachments.get(0).getPhoto().setLocation(40, 100);
                        PhotoAttachments.get(0).getPhoto().getDeleteIcon().addMouseListener(getPhotoAttachmentDeleteIconMouseListener());
                        getAttachmentPanel().add(getPhotoAttachments().get(0).getPhoto());
                        JournalKit.updateCurrentEntryState();
                        getAttachmentPanel().updateUI();
                    }
                    try {
                        //URL url = new URL(URL);
                    }
                    catch(Exception ex)
                    {
                        
                        
                        
                        
                    }       }catch(IOException ex)
                    {
                        Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }           }
        });   
        setLocalFileSystemActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Photo");
                getNativeBrowser().setFileFilter(PhotoFilter);
                int response =  getNativeBrowser().showDialog(getThis(), "Load");
                if (response  == JFileChooser.APPROVE_OPTION && getNativeBrowser().getSelectedFile() != null) {
                    System.out.println("approve running");
                    System.out.println("size of attachment panel: " + getAttachmentPanel().getSize());
                    File f = getNativeBrowser().getSelectedFile();                   
                    if(JournalKit.getFormat(f.getAbsolutePath()) == Format_Type.UNKNOWN)
                    {
                        JOptionPane.showMessageDialog(AttachmentPanel, "This file does not have an explicit extension on its name.\n This can cause errors. To avoid errors, include the extension of the file on the file name.", "Warning", JOptionPane.INFORMATION_MESSAGE , JournalKit.getJournaLogoImage());
                    }
                    PhotoAttachments.add(0 ,new PhotoAttachment(f.getAbsolutePath(), Path_Type.Local));
                    
                    if(PhotoAttachments.get(0).getFile().length()==0)
                    {
                        JOptionPane.showMessageDialog(AttachmentPanel, "An Error Occur\nMake sure that the link you are prividing is not corrupted", "Info", JOptionPane.INFORMATION_MESSAGE , JournalKit.getJournaLogoImage());
                        PhotoAttachments.get(0).delete();
                        return;
                        
                        
                    }     if (PhotoAttachments.get(0).getFile().length()!=0) {
                        AttachmentPanel.remove(AttachmentIcon);
                        AttachmentPanel.remove(AttachmentInstructionDescription);
                        System.out.println("size of the attachment Panel: " + getAttachmentPanel().getSize());
                        PhotoAttachments.get(0).getPhoto().setLocation(40, 100);
                        setPhotoAttachmentDeleteIconMouseListener(new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                System.out.println("file name method:" + PhotoAttachments.get(0).getFile().getName());
                                int answer = JOptionPane.showConfirmDialog(AttachmentPanel, "Are you sure you want to delete this photo?\nThis CANNOT be undone", "Delete Attachment", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,JournalKit.getJournaLogoImage());
                                if(answer == JOptionPane.YES_OPTION)
                                {
                                    PhotoAttachments.get(0).getPhoto().setVisible(false);
                                    System.out.println(AttachmentIcon);
                                    AttachmentPanel.add(AttachmentIcon);
                                    AttachmentPanel.add(AttachmentInstructionDescription);
                                    JournalKit.destroyObject(PhotoAttachments.get(0).getPhoto());
                                    PhotoAttachments.get(0).delete();
                                }
                            }
                            
                        });         
                        PhotoAttachments.get(0).getPhoto().getDeleteIcon().addMouseListener(getPhotoAttachmentDeleteIconMouseListener());
                       JournalKit.updateCurrentEntryState();
                  }
                    System.out.println("location of photo: " + PhotoAttachments.get(0).getPhoto().getLocation());
                  getAttachmentPanel().add(getPhotoAttachments().get(0).getPhoto());
                }
                getAttachmentPanel().updateUI();
            }
        });
     LocalFileSystem.addActionListener(getLocalFileSystemActionListener());
     URLOption.addActionListener(getURLOptionActionListener());
   //  AttachmentMenu.add(AudioAttachmentOption);
    // AttachmentMenu.add(VideoAttachmentOption);
     AttachmentMenu.add(PhotoAttachmentOption);
 }
//public void trashObjects() throws InterruptedException
//{
//     
//    FXpane.getScene();
//    synchronized(FXpane)
//        {
//            try {
//                FXpane.wait();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    
//}
//public void setFXThread()
//{
//        FXpane = new JFXPanel();
//}
//public static Stage getThisStage()
//{
//    return thisStage;
//}
/**
 * This method is simple to make sure that the stage is instantiated ONCE.
 */
//public static void setStageInit()
//{
//       StageInit =  new Runnable()
//       {
//           public void run(){
//           thisStage = new Stage();
//           
//       }};
//}

    /**
     *
     * @return
     */
    //public static  Runnable getStageInit()
//{
//    return StageInit;
//}
public static void setPhotoFilter()
{
    PhotoFilter = new FileNameExtensionFilter("Photo","jpg", "jpeg", "gif","png");
}
public static FileNameExtensionFilter getPhotoFilter()
{
    return PhotoFilter;
}
/**
 This method instantiates 
 the photos that will be used as attachments for this journal entry.
 */
public void setPhotos()
{
Photos = new ArrayList<Drawing>();
}

public ArrayList<Drawing> getPhotos()
{
    return Photos;
}
public JLabel getAttachmentIcon()
{
    return AttachmentIcon;
}
public void deleteFileExtension(String f)
{
        
}
private void setPhotoAttachments()
{
    PhotoAttachments = new ArrayList<PhotoAttachment>();
}
public ArrayList<PhotoAttachment> getPhotoAttachments()
{
    return PhotoAttachments;
}
public void setXDrawing()
{
    XDrawing = new Drawing("X" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.DIALOG, Font.PLAIN, 50), 30, 60);
}
public Drawing getXDrawing()
{
    return XDrawing;
}
public void setTransparentJPanel()
{
    TransparentPanel = new JPanel();
    TransparentPanel.setLayout(null);
    TransparentPanel.setBackground(JournalKit.getTransParentColor());
   // TransparentPanel.setBorder(new LineBorder(Color.BLACK, 1));
}
public JPanel getTransparentPanel()
{
   return  TransparentPanel;
}
public static void restartApplication() throws URISyntaxException, IOException
{
  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
  final File currentJar = new File(Journal.class.getProtectionDomain().getCodeSource().getLocation().toURI());

  /* is it a jar file? */
  if(!currentJar.getName().endsWith(".jar"))
    return;

  /* Build command: java -jar application.jar */
  final ArrayList<String> command = new ArrayList<String>();
  command.add(javaBin);
  command.add("-jar");
  command.add(currentJar.getPath());

  final ProcessBuilder builder = new ProcessBuilder(command);
  builder.start();
  System.exit(0);
}
public void delete()
{

}
public void setTitlePane()
{
Title = new JTextPane();
Title.addKeyListener(new KeyAdapter()
{
    public void keyTyped(KeyEvent e)
    {
        if(!JournalKit.hasSpecialChars(e))
        {
            JournalKit.updateCurrentEntryState();
            System.out.println("typing title");
        }
    }
});
TitlePane = new JScrollPane(Title);
TitlePane.setOpaque(false);
TitlePane.setBorder(null);
Title.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
TitlePane.setSize(new Dimension(370,50));
TitlePane.setLocation(new Point(60, 5));
    int UntitledIndex =1 ;
while(JournalKit.DoesThisNameExist(JournalKit.EntriesPath, Untitled + ".entry") == JournaLIOLogic_Type.True_TheFileExists)
{
     Untitled = ("Untitled"+ UntitledIndex);
     //System.out.println("generating a new title");
    UntitledIndex++;
}
Title.setText(Untitled);
Title.addMouseListener(new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
if(Title.getText().equalsIgnoreCase(Untitled))
{
    Title.setText("");
}
}
 });
}
public void setTitlePane(JScrollPane p)
{
TitlePane = p;
}
public void setTitlePane(String defaultMessage)
{
Title = new JTextPane();
Title.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
Title.setSize(new Dimension(370,50));
Title.setLocation(new Point(50, 0));
Title.setText(defaultMessage);
}
public JTextPane getTitle()
{
    return Title;
}
public JScrollPane getTitlePane()
{
    return TitlePane;
}
public static void setNextPageLabel()
{
	NextPageLabel = new JPanel();
        NextPageLabel.setLayout(null);
        NextPageLabel.setBackground(JournalKit.getTransParentColor());
        NextPageLabel.setLocation(new Point(Journal.getInstance().getPage(CurrentPage).getPhotoView().getLocation().x-10,0));
        NextPageLabel.setSize(new Dimension(50,30));
        NextPageArrow = new Drawing("->" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 30, 60);
       //NextPageLabel.setOpaque(false);
      // NextPageArrow.setOpaque(false);
        NextPageArrow.setVisible(true);
        NextPageLabel.add(NextPageArrow);        
        setNextPageLabelMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JournalKit.nextPage();                  
            }
            public void mouseEntered(MouseEvent e)
            {
               // NextPageArrow.setVisible(true);
                
            }
            public void mouseExited(MouseEvent e)
            {
                //NextPageArrow.setVisible(false);
                
            }
        });
        NextPageLabel.addMouseListener(getNextPageLabelMouseListener());
}
public static JPanel getNextPageLabel()
{
    return NextPageLabel;
}
public Drawing getPhotoView()
{
    return PhotoView;
}
public static void setPreviousPageLabel()
{
        PreviousPageLabel = new JPanel();
        PreviousPageLabel.setLayout(null);
        PreviousPageLabel.setBackground(JournalKit.getTransParentColor());
        PreviousPageLabel.setLocation(new Point((Journal.getInstance().getPage(CurrentPage).getPhotoView().getLocation().x-15)-NextPageLabel.getWidth(),0));
        PreviousPageLabel.setSize(new Dimension(50,30));
        PreviousPageArrow = new Drawing("<-" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 30, 60);
    //   PreviousPageLabel.setOpaque(false);
    //   PreviousPageArrow.setOpaque(false);
    PreviousPageArrow.setVisible(true);
        PreviousPageLabel.add(PreviousPageArrow);        
        setPreviousPageLabelMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JournalKit.previousPage();                
            }
            public void mouseEntered(MouseEvent e)
            {
                //PreviousPageArrow.setVisible(true);
                
            }
            public void mouseExited(MouseEvent e)
            {
                //PreviousPageArrow.setVisible(false);                
            }
        });
        PreviousPageLabel.addMouseListener(getPreviousPageLabelMouseListener());
}
public static JPanel getPreviousPageLabel()
{
    return PreviousPageLabel;
}
public void setMenuIcon()
{
    MenuIcon = new JPanel();
    MenuIcon.setLocation(new Point(2,10));
    MenuIcon.setLayout(null);
    MenuIcon.setSize(new Dimension(40,40));
    //MenuIcon.setBorder(new LineBorder(Color.BLACK,1));
    MenuDashes = new Drawing[3];
    for(int i = 0;i<MenuDashes.length;i++)
    {
        MenuDashes[i] = new Drawing("\u2014" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 40), 30, 30);
        MenuDashes[i].setSize(new Dimension(MenuDashes[i].getWidth(), MenuDashes[i].getHeight()/2));
        if(i == 0)
        {
            MenuDashes[i].setLocation(new Point(0,5));
        }
        else
        {
         MenuDashes[i].setLocation(0 ,(MenuDashes[i-1].getY() + MenuDashes[i-1].getHeight())-5);
        }
      //  MenuDashes[i].setBorder(new LineBorder(Color.BLACK,1));
        MenuDashes[i].setOpaque(false);
            MenuIcon.add(MenuDashes[i]);
            
    }
   
        setMenuIconMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                MenuIcon.setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
                MenuIcon.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseClicked(MouseEvent e)
            {
                
                if(getThis().getX() == 0)
                {
                   showSideMenu();
                }
                else
                {
                    hideSideMenu();
                    
                }
                 if(Journal.getInstance().getPage(0).getTitle().getText().isEmpty())
                {
                    Journal.getInstance().getPage(0).getTitle().setText(Untitled);
                }
            }
            public void mouseEntered(MouseEvent e)
            {
                MenuIcon.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
                MenuIcon.setBorder(null);
            }
        });
        MenuIcon.addMouseListener(MenuIconMouseListener);
        this.add(MenuIcon);
}
public JPanel getMenuIcon()
{
    return MenuIcon;
}
public static void setSideMenu()
{
    SideMenu = new JPanel();
    SideMenu.setLocation(-100,0);
    SideMenu.setSize(100,Journal.getInstance().getHeight());
    SideMenu.setOpaque(false);
    SideMenu.setLayout(null);
    ViewEntriesSideMenuItem = new JLabel("   View Entries");
    ViewEntriesSideMenuItem.setFont(JournalKit.getNormalSizeFont());
    ViewEntriesSideMenuItem.setSize(110, 40);
    ViewEntriesSideMenuItem.setLocation(0,0);
        setViewEntriesSideMenuItemMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                ViewEntriesSideMenuItem.setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
                ViewEntriesSideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseEntered(MouseEvent e)
            {
                ViewEntriesSideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
                ViewEntriesSideMenuItem.setBorder(null);
            }
            public void mouseClicked(MouseEvent e)
            {
                 hideSideMenu();
                 if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified)
                 {
                 saveModifiedEntryWarning();
                 }
                 else if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.New)
                 {
                 saveNewEntryWarning();
                 }
                 else
                 {
                    Journal.getInstance().showMyGreet();
                 }
            }
        });
    ViewEntriesSideMenuItem.addMouseListener(getViewEntriesSideMenuItemMouseListener());
    DeleteThisPageSideMenuItem = new JLabel("   Delete Page");
    DeleteThisPageSideMenuItem.setFont(JournalKit.getNormalSizeFont());
    DeleteThisPageSideMenuItem.setSize(110, 40);
    DeleteThisPageSideMenuItem.setLocation(0,ViewEntriesSideMenuItem.getHeight());
        setDeleteThisPageSideMenuItemMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                DeleteThisPageSideMenuItem.setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
                DeleteThisPageSideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseEntered(MouseEvent e)
            {
                DeleteThisPageSideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
                DeleteThisPageSideMenuItem.setBorder(null);
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                hideSideMenu();
                JournalKit.deletePage(InputPage.CurrentPage);
            }
        });
     DeleteThisPageSideMenuItem.addMouseListener(getDeleteThisPageSideMenuItemMouseListener());
    DeleteThisEntrySideMenuItem = new JLabel("   Delete Entry");
    DeleteThisEntrySideMenuItem.setFont(JournalKit.getNormalSizeFont());
    DeleteThisEntrySideMenuItem.setSize(110, 40);
    DeleteThisEntrySideMenuItem.setLocation(0,ViewEntriesSideMenuItem.getHeight() + DeleteThisPageSideMenuItem.getHeight());
        setDeleteThisEntrySideMenuItemMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                DeleteThisEntrySideMenuItem.setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
                DeleteThisEntrySideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseEntered(MouseEvent e)
            {
                DeleteThisEntrySideMenuItem.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
                DeleteThisEntrySideMenuItem.setBorder(null);
            }
            public void mouseClicked(MouseEvent e)
            {
                hideSideMenu();
               deleteThisEntry();
            }
        });
    DeleteThisEntrySideMenuItem.addMouseListener(getDeleteThisEntrySideMenuItemMouseListener());
    SaveThisEntryMenuItem = new JLabel("   Save Entry");
    SaveThisEntryMenuItem.setFont(JournalKit.getNormalSizeFont());
    SaveThisEntryMenuItem.setSize(110, 40);
    SaveThisEntryMenuItem.setLocation(0,ViewEntriesSideMenuItem.getHeight()*3);
    MouseAdapter SaveThisEntryMenuItemMouseListner = new MouseAdapter() 
   {
         public void mousePressed(MouseEvent e)
        {
                SaveThisEntryMenuItem.setBorder(null);
        }
        public void mouseReleased(MouseEvent e)
        {
                SaveThisEntryMenuItem.setBorder(new LineBorder(Color.BLACK,1));
        }
           public void mouseEntered(MouseEvent e)
        {
                SaveThisEntryMenuItem.setBorder(new LineBorder(Color.BLACK,1));
        }
        public void mouseExited(MouseEvent e)
        {
              SaveThisEntryMenuItem.setBorder(null);
         } 
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("clicked");
                if(Journal.getInstance().getPage(InputPage.CurrentPage).getTitle() != null)
                {
                if(Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().getText().isEmpty())
                {
                    System.out.println("title is empty");
                    Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().setText(Journal.getInstance().getPage(InputPage.CurrentPage).getUntitled());
                }
                }
            hideSideMenu();
             try {
                 InputPage.saveThisEntry();
             } catch (IOException ex) {
                 Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (Exception ex) {
                 Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
             }
        }    
   };
    SaveThisEntryMenuItem.addMouseListener( SaveThisEntryMenuItemMouseListner);
       
    SideMenu.add(DeleteThisPageSideMenuItem);
    SideMenu.add(ViewEntriesSideMenuItem);
    SideMenu.add(DeleteThisEntrySideMenuItem);
    SideMenu.add(SaveThisEntryMenuItem);
}
public static  JPanel getSideMenu()
{
    return SideMenu;
}
public String getUntitled()
{
    return Untitled;
}
public static void saveThisEntry( Object e, String FileName) throws Exception
{
if(Journal.getInstance().getPage(InputPage.CurrentPage).getX() == 0)
          {
        SideMenu.setLocation(0,0);
        Journal.getInstance().getPage(InputPage.CurrentPage).setLocation(Journal.getInstance().getPage(InputPage.CurrentPage).getX()+100,Journal.getInstance().getPage(InputPage.CurrentPage).getY());                         
        InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()+100,InputPage.getBottomUtility().getY());
         }
          else
          {
          SideMenu.setLocation(-100,0);
          Journal.getInstance().getPage(InputPage.CurrentPage).setLocation(Journal.getInstance().getPage(InputPage.CurrentPage).getX()-100, Journal.getInstance().getPage(InputPage.CurrentPage).getY());                         
        InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()-100,InputPage.getBottomUtility().getY());
       
          }
Serializer.serialize(e, FileName + JournalKit.EntriesExntension);
}
public static JPanel getBottomUtilityPanel()
{
    return BottomUtilityPanel;
}
public void setMenuIconMouseListener()
{
        setMenuIconMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                MenuIcon.setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
                MenuIcon.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseClicked(MouseEvent e)
            {
                if(getThis().getX() == 0)
                {
                    SideMenu.setLocation(0,0);
                    getThis().setLocation(getThis().getX()+100, getThis().getY());
                    InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()+100,InputPage.getBottomUtility().getY());
                }
                else
                {
                    SideMenu.setLocation(-100,0);
                    getThis().setLocation(getThis().getX()-100, getThis().getY());
                    InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()-100,InputPage.getBottomUtility().getY());
                    
                }
            }
            public void mouseEntered(MouseEvent e)
            {
                MenuIcon.setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
                MenuIcon.setBorder(null);
            }
        });
}
public MouseAdapter getMenuIconMouseListener()
{
    return MenuIconMouseListener;
}
public void setInpuTitletMouseListener()
{
      InputTitleMouseListener = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("clicked");
                if(Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().getText().isEmpty())
                {
                    System.out.println("title is empty");
                    Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().setText(Untitled);
                }
            }
        };
}
public MouseAdapter getInputTitleMouseListener()
{
    return InputTitleMouseListener;
}

    /**
     * @return the NextLineKeyListener
     */
    public KeyAdapter getNextLineKeyListener() {
        return NextLineKeyListener;
    }

    /**
     * @param NextLineKeyListener the NextLineKeyListener to set
     */
    public void setNextLineKeyListener(KeyAdapter NextLineKeyListener) {
        this.NextLineKeyListener = NextLineKeyListener;
    }

    /**
     * @return the SaveThisEntryMenuItemMouseListner
     */
    public MouseAdapter getSaveThisEntryMenuItemMouseListner() {
        return SaveThisEntryMenuItemMouseListner;
    }

    /**
     * @param SaveThisEntryMenuItemMouseListner the SaveThisEntryMenuItemMouseListner to set
     */
    public void setSaveThisEntryMenuItemMouseListner(MouseAdapter SaveThisEntryMenuItemMouseListner) {
        this.SaveThisEntryMenuItemMouseListner = SaveThisEntryMenuItemMouseListner;
    }

    /**
     * @param InputTitleMouseListener the InputTitleMouseListener to set
     */
 

    /**
     * @param MenuIconMouseListener the MenuIconMouseListener to set
     */
    public void setMenuIconMouseListener(MouseAdapter MenuIconMouseListener) {
        this.MenuIconMouseListener = MenuIconMouseListener;
    }

    /**
     * @return the PhotoAttachmentDeleteIconMouseListener
     */
    public MouseAdapter getPhotoAttachmentDeleteIconMouseListener() {
        return PhotoAttachmentDeleteIconMouseListener;
    }

    /**
     * @param PhotoAttachmentDeleteIconMouseListener the PhotoAttachmentDeleteIconMouseListener to set
     */
    public void setPhotoAttachmentDeleteIconMouseListener(MouseAdapter PhotoAttachmentDeleteIconMouseListener) {
        this.PhotoAttachmentDeleteIconMouseListener = PhotoAttachmentDeleteIconMouseListener;
    }

    /**
     * @return the LocalFileSystemActionListener
     */
    public ActionListener getLocalFileSystemActionListener() {
        return LocalFileSystemActionListener;
    }

    /**
     * @param LocalFileSystemActionListener the LocalFileSystemActionListener to set
     */
    public void setLocalFileSystemActionListener(ActionListener LocalFileSystemActionListener) {
        this.LocalFileSystemActionListener = LocalFileSystemActionListener;
    }

    /**
     * @return the URLOptionActionListener
     */
    public ActionListener getURLOptionActionListener() {
        return URLOptionActionListener;
    }

    /**
     * @param URLOptionActionListener the URLOptionActionListener to set
     */
    public void setURLOptionActionListener(ActionListener URLOptionActionListener) {
        this.URLOptionActionListener = URLOptionActionListener;
    }

    /**
     * @return the AttachmentMenuWindowLIstener
     */
    public WindowFocusListener getAttachmentMenuWindowLIstener() {
        return AttachmentMenuWindowLIstener;
    }

    /**
     * @param AttachmentMenuWindowLIstener the AttachmentMenuWindowLIstener to set
     */
    public void setAttachmentMenuWindowLIstener(WindowFocusListener AttachmentMenuWindowLIstener) {
        this.AttachmentMenuWindowLIstener = AttachmentMenuWindowLIstener;
    }

    /**
     * @return the InputCaretLIstener
     */
    public CaretListener getInputCaretLIstener() {
        return InputCaretLIstener;
    }

    /**
     * @param InputCaretLIstener the InputCaretLIstener to set
     */
    public void setInputCaretLIstener(CaretListener InputCaretLIstener) {
        this.InputCaretLIstener = InputCaretLIstener;
    }
    
    /**
     * This copies data from entry e and inserts into this input page.
     * @param e 
     */
 public void copyThisEntry(Entry e)
 {
     
 } 
 
public static void saveThisEntry() throws IOException
{
//Journal.getInstance().setEntryPages();
//Entry thisEntry = new Entry(Journal.getInstance().getPage(0).getTitle().getText(), "Junior", JournalKit.getDate() ,InputPage.getTagPanel().getAllTags(), Journal.getInstance().getEntryPages());
Journal.getInstance().updateCurrentEntry();
if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.New)
{
while(JournalKit.DoesThisNameExist(JournalKit.EntriesPath, Journal.getInstance().getCurrentEntry().getEntryName() + ".entry") == JournaLIOLogic_Type.True_TheFileExists)
{
int answer = JOptionPane.showConfirmDialog(Journal.getInstance().getPage(CurrentPage), "An entry with this name already exists.\nWould you like to overite the contents of that Entry with the content from this one?", "This Entry Exists", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,JournalKit.getJournaLogoImage());    
if(answer == JOptionPane.YES_OPTION)
{
break;
}
else if(answer == JOptionPane.NO_OPTION)
{
return;
}
else if(answer == JOptionPane.CANCEL_OPTION )
{
return;
}
else
{
return;
}
}
}
else if((Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved ||Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified) && (Journal.getInstance().getCurrentEntry() != null) )
{
Journal.getInstance().getCurrentEntrySavedFile().delete();
Journal.getInstance().updateCurrentEntry();
}

File file =  Serializer.serialize(Journal.getInstance().getCurrentEntry(), Journal.getInstance().getCurrentEntry().getEntryName() + ".entry");
Journal.getInstance().getCurrentEntry().setEntryState(EntryState.Saved);
Journal.getInstance().setCurrentSavedtEntryFile(file);
//Journal.getInstance().getPage(0).setEntryName(EntryName + "(saved)");
}
public EntryPage getThisEntryPage()
{
    return thisEntryPage;
}
public static void addTags(ArrayList<String> tags)
{
for(int i =0 ;i<tags.size();i++)
{
InputPage.getTagPanel().addTag(tags.get(i));
}
}
public static void showSideMenu()
{
 SideMenu.setLocation(0,0);                 
Journal.getInstance().getPage(InputPage.CurrentPage).setLocation(Journal.getInstance().getPage(InputPage.CurrentPage).getX()+100, Journal.getInstance().getPage(InputPage.CurrentPage).getY());
InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()+100,InputPage.getBottomUtility().getY());
}
public static void hideSideMenu()
{
       SideMenu.setLocation(-100,0);
       Journal.getInstance().getPage(InputPage.CurrentPage).setLocation(Journal.getInstance().getPage(InputPage.CurrentPage).getX()-100, Journal.getInstance().getPage(InputPage.CurrentPage).getY());
       InputPage.getBottomUtility().setLocation(InputPage.getBottomUtility().getX()-100,InputPage.getBottomUtility().getY());
                 
}
public static void updateCurrentNumberOfPhotos()
{
    CurrentNumberOfPhotos = 0;
    for(int i = 0;i<Journal.getInstance().getPages().size();i++)
    {
        if(!Journal.getInstance().getPage(i).getPhotoAttachments().isEmpty())
        {
            CurrentNumberOfPhotos++;
        }        
    }
    NumberOfPhotosDescription.setText(CurrentNumberOfPhotos + " Photos");
}
public String getThisDate()
{
return Input.get(0).getText();
}
public static void resetCurrentPage()
{
    CurrentPage = 0;
}
    @Override
    public void finalize() throws Throwable
{
        try {
            JOptionPane.showMessageDialog(this, "finalizing object");
            System.out.println("trashing objects");
        } finally {
            super.finalize();
        }
}
    @Override
    public void setVisible(boolean t)
    {
        if(t)
           this.setFocusable(true);
    this.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                System.out.println("escaping");
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                if(Journal.getInstance().getCurrentEntry() != null && Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified)
                {
                    if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified)
                 {
                 int answer = 0;
                 answer = JOptionPane.showConfirmDialog(Journal.getInstance().getPage(InputPage.CurrentPage), "This entry has been edited, would you like to save the changes made to this entry?", "Edited Entry", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, JournalKit.getJournaLogoImage());
                if(answer == JOptionPane.YES_OPTION)
                {
                     try {
                         saveThisEntry();
                     } catch (IOException ex) {
                         Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 Journal.getInstance().showMyGreet();                 
                }
                else if(answer == JOptionPane.NO_OPTION)
                {
                  Journal.getInstance().showMyGreet();
                }
                else if(answer == JOptionPane.CANCEL_OPTION)
                 {                    
                 }
                 }
                 else
                 {
                    Journal.getInstance().showMyGreet();
                 }
                }              
                }
            
                System.out.println("key pressed");
            }
        });
    super.setVisible(t);
        }

public static void saveNewEntryWarning()
{
 int answer = 0;
            answer = JOptionPane.showConfirmDialog(Journal.getInstance().getPage(InputPage.CurrentPage), "would you like to save the changes made to this entry?", "New Entry", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, JournalKit.getJournaLogoImage());
            if(answer == JOptionPane.YES_OPTION)
                {
                     try {
                         saveThisEntry();
                     } catch (IOException ex) {
                         Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 Journal.getInstance().showMyGreet();                 
                }
                else if(answer == JOptionPane.NO_OPTION)
                {
                  Journal.getInstance().showMyGreet();
                }
                else if(answer == JOptionPane.CANCEL_OPTION)
                 {
                     
                 }
}
public static void saveModifiedEntryWarning()
{
int answer = 0;
                 answer = JOptionPane.showConfirmDialog(Journal.getInstance().getPage(InputPage.CurrentPage), "This entry has been edited, would you like to save the changes made to this entry?", "Edited Entry", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, JournalKit.getJournaLogoImage());
                if(answer == JOptionPane.YES_OPTION)
                {
                     try {
                         saveThisEntry();
                     } catch (IOException ex) {
                         Logger.getLogger(InputPage.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 Journal.getInstance().showMyGreet();                 
                }
                else if(answer == JOptionPane.NO_OPTION)
                {
                  Journal.getInstance().showMyGreet();
                }
                else if(answer == JOptionPane.CANCEL_OPTION)
                 {                    
                 }
}
public static void deleteThisEntry()
{

                if(Journal.getInstance().getCurrentEntry() != null)
                {
                    if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved || Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Modified )
                    {
                int answer = JOptionPane.showConfirmDialog(DeleteThisEntrySideMenuItem.getParent(), "Are you sure you want to delete " +   "\""+ Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().getText()+"\"" + " ?" + "\nThis action CANNOT be undone.", "Delete " + "\""+ Journal.getInstance().getPage(InputPage.CurrentPage).getTitle().getText()+"\"", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,JournalKit.getJournaLogoImage());
                                if(answer == JOptionPane.YES_OPTION)
                                {
                                    Entry.delete(Journal.getInstance().getCurrentEntry());
                                    if(Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved)
                                    Journal.getInstance().getCurrentEntrySavedFile().delete();                                
                                    Journal.getInstance().showMyGreet();
                                    //CurrentEntry = null;
                                }
                                
                    }
                    }
}
public static void saveThisEntry(Entry e) throws IOException
{
Serializer.serialize(e);
}
public void hidePhotoView()
{
    Journal.getInstance().setSize(JournalKit.getInstance().MinimumSize);
                        Journal.getInstance().getMainJournalPanel().setSize(JournalKit.getInstance().MinimumSize);
			PhotoView.setDrawText("+");
                        JournalKit.update(getThis());
}
public void showPhotoView()
{
                                JournalKit.update(getThis());
				Journal.getInstance().setSize(JournalKit.getInstance().MaxSize);
                                Journal.getInstance().getMainJournalPanel().setSize(JournalKit.getInstance().MaxSize);
				PhotoView.setDrawText("-");
}
}

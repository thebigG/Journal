package JournalApp;
//import java.awt.AWTException;
import com.sun.glass.ui.Application;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Platform;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Component;

import javax.swing.*;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author LorenzoGomez
 *
 */

public class Journal extends JFrame implements Input, WindowStatus
{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private ArrayList<InputPage> Pages;
private static Journal Window;
private JournalRootPanel MainJournalPanel;
private static int status ;
private  EntriesView myGreet;
private ArrayList<EntryPage> EntryPages; // this is the holder of serialized data of Pages. This is meant to be used for serialization process ONLY.
private Entry CurrentEntry;   // This is meant to be used for deserialization ONLY.
private Timer JournalTimer;
private File CurrentSavedEntryFile; // This is the current saved/serialized FILE of the current ssaved entry 
private Entry CurrentSavedEntry;
	/**
	 * Launch the application.
     * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
     * @throws java.awt.AWTException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException, AWTException 
	{ 
		
            System.setProperty("apple.laf.useScreenMenuBar", "true");
	    System.setProperty("apple.awt.fileDialogForDirectories", "true");
         //   System.setProperty("java.awt.headless", "true");
            System.setProperty("apple.awt.UIElement", "true");
	    System.setProperty("apple.awt.UIElement", "true");
            System.setProperty("Quaqua.Button.style", "push");
	    System.setProperty("JButton.buttonType", "toolbar");
	    UIManager.put("Quaqua.Button.style", "push");
	    UIManager.put("OptionPane.background", Color.white);
	    UIManager.put("Panel.background", Color.WHITE);
		try {
		    UIManager.setLookAndFeel(
		        UIManager.getSystemLookAndFeelClassName());
	} catch (UnsupportedLookAndFeelException ex) {
	  System.out.println("Unable to load native look and feel");
		}
		SwingUtilities.invokeAndWait(new Runnable(){

			public void run() {
			    try 
			    {
					Window = new Journal();
					fxiJava8RenderIssue();
					setWindowsCloseProcess();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  //  InputPage.getTagPanel().notifyAll();
			  //  setTest();

			    // ...

//			    if(Desktop.isDesktopSupported())
//			    {
//			      try {
//					Desktop.getDesktop().browse(new URI("http://www.google.com"));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (URISyntaxException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			    }
			}}); 
		    System.setProperty("apple.awt.UIElement", "true");

        }
    private ArrayList<EntryPage> CurrentEntryPages;

	/**
	 * Create the frame.
	 * @throws AWTException 
	 */
	private Journal() throws AWTException, IOException, FileNotFoundException, ClassNotFoundException 
		{
                  JournalKit.createInstance();
                  setCurrentaEntryPages();
		//this.getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE );
		this.getRootPane().putClientProperty("window.shadow", Boolean.TRUE);
		
	    //setBrowser();
		
		//System.out.println("Current Time" + JournalKit.getDate());
		//Debug.Print((JournalKit.getDefaultToolkit().getScreenSize().width)*299/384);
		//System.out.println(JournalKit.getDefaultToolkit().getScreenSize().height);
//		Debug.Print(JournalKit.getDefaultToolkit().getScreenResolution());
                
//		Debug.Print(Math.round(3.5));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(JournalKit.getInstance().MinimumSize);                
		setMaximumSize(JournalKit.getInstance().MaxSize);
                setSize(JournalKit.getInstance().MaxSize);
                setLocation(JournalKit.getInstance().MiddleScreen);
		getContentPane().setLayout(null);                
		//getContentPane().setLayout(cards);		     
                setMainJournalPanel();
                 setMyGreet();
                //getMainJournalPanel().setDoubleBuffered(true);
                getMainJournalPanel().addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) 
			{
		 	System.out.println("Componenet Added:" + e.getChild());
                        if((e.getChild() instanceof InputPage) || (e.getChild() instanceof EntriesView))
		 	setStatus(e.getChild()); 
		 	//JournalKit.update(Window);			
			}
                        public void componentRemoved(ContainerEvent e) 
			{
                         if(e.getChild() instanceof InputPage)
                         {
		 	System.out.println("Componenet removed:" + e.getChild());
                         }
		 	//JournalKit.update(Window);			
			}
		});
		//myGreet.getWriteEntryButton().setBounds(x, y, width, height);
		//myGreet.setLocation(new Point(0, 0));
//                System.out.println("size of my greet :" + myGreet.getSize());
		getMainJournalPanel().add(myGreet);
                ;
		
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent e) {
//				if(InputPage.getNativeBrowser().isVisible())
//				{
//					System.out.println("Window activated");
//				}
//			}
//			public void windowDeactivated(WindowEvent e)
//			{
//				Debug.Print("Window deactivated");
//			}
//		});
//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentShown(ComponentEvent e) 
//			{
//				if(InputPage.getNativeBrowser().isVisible())
//				{
//					System.out.println("File explorer is here!!!!");
//				}
//			}
//		});
		this.setBackground(Color.WHITE);
            this.getContentPane().add(getMainJournalPanel());
            this.getContentPane().setVisible(true);
            this.setVisible(true);
	//setJournalTimer();
        
		}
public void setMainJournalPanel()
{
    MainJournalPanel = new JournalRootPanel();    
    MainJournalPanel.setLayout(null);
    MainJournalPanel.setSize(new Dimension(getSize()));
    MainJournalPanel.setLocation(new Point(0,0));
    MainJournalPanel.setBackground(Color.WHITE);
    MainJournalPanel.setVisible(true);
    MainJournalPanel.addMouseListener(new MouseAdapter()
    {
    
        public void mouseClicked(MouseEvent e)
        {
          //  System.out.println("It's alive!");
        }
    });
}
public JPanel getMainJournalPanel()
{
    return MainJournalPanel;
}
public  void setPages() throws InvocationTargetException, InterruptedException, BadLocationException
{
	Pages = new ArrayList<InputPage>(1);
	for(int k = 0;k<2; k++)
	{
            
                if(k == 0)
                {
                        Pages.add(k, new InputPage(true));
			InputPage.setBottomUtility();  
                        InputPage.setSideMenu();
                        Pages.get(k).setTitlePane();                                                
                        Pages.get(k).add(Pages.get(k).getTitlePane());
                        /**
                         * set up the title listener for every line of the first page of this entry
                         */
                        
                }   
                else
                {
                    Pages.add(k, new InputPage(false));
                }
                
        }
}
public void setPages(ArrayList<InputPage> pages) throws InterruptedException, BadLocationException
{
        this.Pages = pages;
        //System.out.println(pages.size());
	for(int k = 0;k<pages.size(); k++)
	{
                if(k == 0)
                {                    
			InputPage.setBottomUtility();  
                        InputPage.setSideMenu();
                        Pages.get(k).setTitlePane();
                        Pages.get(k).getTitle().setText(CurrentEntry.getEntryName());
                        Pages.get(k).add(Pages.get(k).getTitlePane()); 
                        InputPage.addTags(CurrentEntry.getTags());
                        Pages.get(k).setInpuTitletMouseListener();
                        for(int i = 1;i<Pages.get(k).getInput().size();i++)
                        {
                            Pages.get(k).getInput().get(i).getTextPane().addMouseListener(Pages.get(k).getInputTitleMouseListener());
                        }
                }   
               // Pages.get(k).setMenuIconMouseListener();
              //  Pages.get(k).getMenuIcon().addMouseListener(Pages.get(k).getMenuIconMouseListener());
                 /**
                         * set up the title listener for every line of the first page of this entry
                         */
//                        Pages.get(k).setInpuTitletMouseListener();
//                        for(int i = 1;i<Pages.get(k).getInput().size();i++)
//                        {
//                            Pages.get(k).getInput().get(i).getTextPane().addMouseListener(Pages.get(k).getInputTitleMouseListener());
//                        }
                //Pages.get(k).get
	}

}
public void setStartingPxlYPoint() {}

public  static Journal getInstance()
{
	return Window;
}

public InputPage getPage(int Index) 
{	
	return Pages.get(Index);
}


public JTextPane getLine(int LineNum) {
	// TODO Auto-generated method stub
	return null;
}


public ArrayList<InputPage> getPages()
{
	return Pages;
}

@Override
public void setStatus(Component o) 
{
	this.setTitle("Journal - "+ o);
}
public void setStatus(String s)
{
        this.setTitle("Journal - "+ s);
}
public int getCurrentLine() 
{	
	return 0;
}

public void updateCurrentLine() 
{	
}
public  InputPage getCurrentPage()
{
	return Pages.get(InputPage.CurrentPage) ;
}
public String toString()
{
	return "Welcome";
}

public void setPreviousPageButton() {
	// TODO Auto-generated method stub
	
}

public JLabel getPreviouspageButton() {
	// TODO Auto-generated method stub
	return null;
}

public void setNextPageButton() {
	// TODO Auto-generated method stub
	
}

public JLabel getNextPageButton() {
	// TODO Auto-generated method stub
	return null;
}

public void setCurrentPageDescription() {
	// TODO Auto-generated method stub
	
}

public JLabel getCurrentPageDescription() {
	// TODO Auto-generated method stub
	return null;
}

public void setCurrentLineDecsription() {
	// TODO Auto-generated method stub
	
}

public JLabel getCurrentLineDescription() {
	// TODO Auto-generated method stub
	return null;
}

public void setNumberOfWordsDescription() {
	// TODO Auto-generated method stub
	
}

public JLabel getNumberOfWordsDescription() {
	// TODO Auto-generated method stub
	return null;
}

public void setNumberOfPhotosDescription() {
	// TODO Auto-generated method stub
	
}

public JLabel getNumberOfPhotosDescrption() {
	// TODO Auto-generated method stub
	return null;
}
public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
{
	super.imageUpdate(getIconImage(), ImageObserver.SOMEBITS, this.getX(), this.getY(), ImageObserver.WIDTH, ImageObserver.HEIGHT );
//	System.out.println("updating image.....");
	return false;
}
/**
 * This function fixes an issue that may happen while running JDK 8 by 
 * minimizing and restoring the window.
 * @throws InterruptedException
 */
public static void fxiJava8RenderIssue() throws InterruptedException
{
	Window.setState(JFrame.ICONIFIED);
	Thread.sleep(600);
	Window.setExtendedState(JFrame.NORMAL);
}
public static void KillAllProcesses() throws InterruptedException
{
      
     //   Application.GetApplication().terminate();
   
    JournalKit.TrashObjects();           
    Platform.exit();
    System.exit(0);
}
public static void setWindowsCloseProcess()
{
	Journal.getInstance().addWindowListener(new WindowAdapter()
	{
                @Override
		public void windowClosing(WindowEvent e)
		{
                    System.out.println("closing");
                    try {
                        KillAllProcesses();
                        //Journal.getInstance().KillAllProcesses();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Journal.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
                @Override
                public void windowClosed(WindowEvent e)
                {
                     System.out.println("closing");
			//Journal.getInstance().KillAllProcesses();
                }
	});
}
public Journal getThis()
{
    return this;
}
public EntriesView getMyGreet()
{
 return myGreet;   
}
public void showPages()
{
    getMainJournalPanel().add(Pages.get(InputPage.CurrentPage));
    getMainJournalPanel().add(InputPage.getBottomUtility());                                
    getMainJournalPanel().add(InputPage.getSideMenu());                                
    //cards.next(getContentPane());
    Pages.get(InputPage.CurrentPage).setVisible(true);
    //input.setVisible(false);
    myGreet.setVisible(false);
    getMainJournalPanel().remove(myGreet);
    getMainJournalPanel().remove(myGreet.getToolBar());
}
public void showMyGreet()
{
   Pages.get(InputPage.CurrentPage).setVisible(false); 
 Journal.getInstance().getPage(InputPage.CurrentPage).getParent().remove(InputPage.getSideMenu()); 
    getMainJournalPanel().remove(Pages.get(InputPage.CurrentPage));
   for(int i = 0 ; i<Pages.size();i++)
   {
       
       Pages.get(i).getThisTimer().stop();       
       Pages.set(i, null);
   }
   
Pages.clear();
   System.out.println(Pages.isEmpty());
   //InputPage.getTagPanel().getThisTimer().stop();
    Journal.getInstance().getMainJournalPanel().remove(InputPage.getBottomUtilityPanel());
    //input.setVisible(false);
    myGreet.setVisible(true);   
    getMainJournalPanel().add(myGreet);  
    getMainJournalPanel().add(myGreet.getToolBar());
}
public void setJournalTimer()
{
//JournalTimer = new Timer(20,this);
JournalTimer.start();
}
public void setCurrentaEntryPages()
{
  CurrentEntryPages = new ArrayList<EntryPage>(5);
}
public void actionPerformed(ActionEvent e) 
    {
        if(MainJournalPanel != null)
        {                        
        // MainJournalPanel.updateUI();
        }
        {
         // if(Pages.size()>=2  )
            //updateCurrentEntry();
//      //  updateCurrentEntry();
            
       }
        
       // JournalKit.TrashObjects();
    }
public void updateCurrentEntry()
{
    
     CurrentEntryPages.clear();
    // second condition buggy
for(int i = 0;i<Pages.size();i++)
{    
    //CurrentEntryPages.clear();
//    if(!CurrentEntryPages.isEmpty())
//    {
//    if(!Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
//    CurrentEntryPages.set(i,new EntryPage(Pages.get(i).getLinesContent(), Pages.get(i).getPhotoAttachments().get(0).getFile()));
//    else if(!Pages.get(i).getLinesContent().isEmpty() && Pages.get(i).getPhotoAttachments().isEmpty())
//    CurrentEntryPages.set(i,new EntryPage(Pages.get(i).getLinesContent()));
//    else if(Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
//    CurrentEntryPages.set(i,new EntryPage(Pages.get(i).getPhotoAttachments().get(0).getFile()));
//    
////else    
////    {
////    CurrentEntryPages.set(i,new EntryPage());
////    }
//    }    
//    else
    {
    if(!Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
    CurrentEntryPages.add(i,new EntryPage(Pages.get(i).getLinesContent(), Pages.get(i).getPhotoAttachments().get(0).getFile()));
    else if(!Pages.get(i).getLinesContent().isEmpty() && Pages.get(i).getPhotoAttachments().isEmpty())
    CurrentEntryPages.add(i,new EntryPage(Pages.get(i).getLinesContent()));
    else if(Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
    CurrentEntryPages.add(i,new EntryPage(Pages.get(i).getPhotoAttachments().get(0).getFile()));  
    
    else{
        System.out.println("adding extra page");
        CurrentEntryPages.add(new EntryPage());
    }
//    else
//    {
//    System.out.println("extra page added");
//    CurrentEntryPages.add(i ,new EntryPage());
//    }
    }
}
       getCurrentEntry().setPages(CurrentEntryPages);
       getCurrentEntry().setAuthorName("");
       getCurrentEntry().setDate(Pages.get(0).getThisDate());
       getCurrentEntry().setEntryName(Pages.get(0).getTitle().getText());
       getCurrentEntry().setTags(InputPage.getTagPanel().getAllTags());
}
            
    /**
     *
     */
    public void setEntryPages()
{
    for(int i = 0;i<Pages.size();i++)
{    
    if(!Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
    EntryPages.add(i ,new EntryPage(Pages.get(i).getLinesContent(), Pages.get(i).getPhotoAttachments().get(0).getFile()));
    else if(!Pages.get(i).getLinesContent().isEmpty() && Pages.get(i).getPhotoAttachments().isEmpty())
    EntryPages.add(i ,new EntryPage(Pages.get(i).getLinesContent()));
    else if(Pages.get(i).getLinesContent().isEmpty() && !Pages.get(i).getPhotoAttachments().isEmpty())
    EntryPages.add(i ,new EntryPage(Pages.get(i).getPhotoAttachments().get(0).getFile()));
}
       EntryPages.set(CurrentEntryPages.size(), new EntryPage());

}
public ArrayList<EntryPage> getEntryPages()
{    
    System.out.println("entry pages: " + EntryPages.isEmpty());
    return EntryPages;
}
public void setCurrentEntry(Entry e)
{
    this.CurrentEntry = e;
//   
}
public Entry getCurrentEntry()
{
    return CurrentEntry;
}
//public toString()
//{
//    String  x ="";
//    if()
//}
public void setMyGreet() throws IOException, FileNotFoundException, ClassNotFoundException
{
    myGreet = new EntriesView();
    MainJournalPanel.add(myGreet.getToolBar());
}
public void setCurrentSavedtEntryFile(File e)
{
CurrentSavedEntryFile = e;
}
public void deleteCurrentEntry()
{
   CurrentEntry = null; 
}
public File getCurrentEntrySavedFile()
{
    
 return CurrentSavedEntryFile;   
}
public void setCurrentSavedEntry(Entry e)
{
e = CurrentSavedEntry;
}
public Entry getCurrentSavedEntry()
{
    return CurrentSavedEntry;
}
}
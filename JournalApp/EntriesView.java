package JournalApp;
import ImageUtil.ImageLoader;
import JournaLIO.Serializer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
/**
 * This class is used to to view all of the saved Journal entries.
 * @author lorenzogomez
 *TO-DO Maybe just add a smalll JPanel to "this". Having that top utility being part of the instance will make it easier to handel. 
 */
public class EntriesView extends JPanel implements ActionListener
{
private ArrayList<Entry> Entries;
private ArrayList<File> EntryFiles;
private ArrayList<JLabel> EntryIcons;
private ArrayList<InputPage> InputPages;
private  Drawing InfoView;
private JLabel WriteEntry;
private JPanel MainPanel;
private ArrayList<ImageIcon> Thumbnails;
private Timer thisTimer;
public static int EntryIconsMaxWidth = 110;
public static int MaxEntryIcons = 21;
public JLabel NoEntries;
public JLabel NoEntrySelected;
public JPanel InfoViewPanel;
public static int MainPanelInitiaY = 50;
private JPanel ToolBar;
private JLabel JournalIconView;
private JLabel EntryAuthor;
private JLabel DateCreated;
private JLabel Heart;
private JPanel EntryInfoBox;
public static int Initial_Size = 3;
private JScrollPane EntryInfoScroller;
private JLabel EntryTags;
public final static String TransparentHeartCode = "<html> <span style=\"font-size:200%;fontcolor:&#red;\">&#9825;<html>";
public final static String RedHeartCode = "<html> <span style=\"font-size:200%;color:red;\">&hearts;<html>";
private Drawing thisHashTag;
private StringBuilder TagsContent;
final static String NoTags = "The selected entry has not tags";
public final static int CharactersPerTagLine  = 22;
private Drawing HelpQuestionMark;
private final String CommandSymbolHTML  = "&#8984";
private final String CommandSymbolUnicode  = "⌘";
{
    this.getActionMap().put("New Entry", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
        				try {
                                    unselectEntries();
					try {
						Journal.getInstance().setPages();
                                                Journal.getInstance().setCurrentEntry(new Entry());
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//InputPage input = new InputPage();
				Journal.getInstance().showPages();
    }
    });  
    this.getActionMap().put("HideInfoView", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
       hideInfoView();
    }
    });
    this.getActionMap().put("ShowInfoView", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
    {           
       showInfoView();
    }
    });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"New Entry");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"HideInfoView");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),"ShowInfoView");        
setTimer();
MainPanel = new JPanel();
MainPanel.setLayout(null);
//this.setLayout(null);
MainPanel.setBackground(Color.WHITE);
MainPanel.setSize(new Dimension(JournalKit.getInstance().MinimumSize));
MainPanel.setLocation(new Point(0,0));
//MainPanel.setDoubleBuffered(true);
MainPanel.addMouseWheelListener(new MouseWheelListener()
	{

		public void mouseWheelMoved(MouseWheelEvent e) 
		{
		/**
		 * Use ARROWS to indicate which position is the user scrolling to.
		 */
			if(!EntryIcons.isEmpty())
                        {
                            System.out.println(e.getPreciseWheelRotation());
                            if(e.getPreciseWheelRotation()<0)
                            {                
                              if(MainPanel.getY()<MainPanelInitiaY)
                            moveMainPanelDown(e.getPreciseWheelRotation());                          
                            }
                            else
                            {
                                //System.out.println(getThis().getComponent(0).getComponentAt(EntryIcons.get(EntryIcons.size()-1).getX(), EntryIcons.get(EntryIcons.size()-1).getY()));
                                //System.out.println(Math.abs(MainPanel.getLocation().getY()));  
                               // System.out.println("height of main panel" + MainPanel.getHeight());
                              //  System.out.println(" number of entries  " + EntryIcons.size());
                                if(Math.abs(MainPanel.getY())<getYScrollingLimit())
                                moveMainPanelUp( e.getPreciseWheelRotation());
                            }
                        }
		}
		
	});
MainPanel.addMouseListener(new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
unselectEntries();   
}
});
this.setBackground(Color.WHITE);
this.setLocation(new Point(0,MainPanelInitiaY));
this.setSize(new Dimension(JournalKit.getInstance().MaxSize));
this.addMouseListener(new MouseAdapter()
{
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("mouse Listener");
    }
});
setWriteEntry();
setNoEntries();
}
public EntriesView() throws IOException, FileNotFoundException, ClassNotFoundException
{
super();
super.setBackground(Color.WHITE);
super.setLayout(null);
setTagsContent();
setEntryFiles();
setThumbnails();
updateThumbnails();
setEntryIcons();
setInfoViewPanel();
getInfoViewPanel().add(getJournalIconView());
getInfoViewPanel().add(getNoEntrySelected());
setEntryInfoBox();
setDateCreated();
setHeart();
getEntryInfoBox().add(Heart);
setThisHashTag();
setEntryTags();
setEntryInfoScrller();
setInfoView();
setToolBar();
 
super.add(MainPanel);
getThisTimer().start();
}
public void setWriteEntry() throws IOException
{
WriteEntry = new JLabel(new ImageIcon( ImageLoader.fromUrl(InputPage.class.getClassLoader().getResource("Images/WriteEntries.jpg")).getResizedToWidth(40).getBufferedImage()));
WriteEntry.setSize(new Dimension(WriteEntry.getIcon().getIconWidth(),WriteEntry.getIcon().getIconHeight()));
WriteEntry.setLocation(0,0);
WriteEntry.setVisible(true);
//super.add(MainPanel);
getWriteEntry().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				try {
                                    unselectEntries();
					try {
						Journal.getInstance().setPages();
                                                Journal.getInstance().setCurrentEntry(new Entry());
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//InputPage input = new InputPage();
				Journal.getInstance().showPages();
			}
		});

}
public void setEntryFiles()
{
    File x = new File(JournalKit.EntriesPath);
        if(!x.isDirectory())
            x.mkdir();
    File[] files = x.listFiles();
    EntryFiles = new ArrayList<>(files.length + Initial_Size );
        
    if(files.length != 0)
    {
    for(int i = 0;i<files.length;i++)
    {       
        if(files[i].getName().contains(JournalKit.EntriesExntension))
        {
        EntryFiles.add(files[i]);
        }
    }    
    }
    for(int i = 0; i<EntryFiles.size();i++)
    {
    System.out.println(EntryFiles.get(i));
    }
}
public void updateEntryFiles()
{
    EntryFiles.clear();
    File[] x = new File(JournalKit.EntriesPath).listFiles();        
    for(int i = 0;i<x.length;i++)
    {       
        if(x[i].getName().contains(JournalKit.EntriesExntension))
        {
        EntryFiles.add(x[i]);
        }
    }   
}
public void setEntryIcons() throws IOException, FileNotFoundException, ClassNotFoundException
{
    EntryIcons = new ArrayList<>();
    InputPages = new ArrayList<>();    
    Entries = new ArrayList<>();
    int x = 30;
    int y = 0;
    if(!EntryFiles.isEmpty())
    {
    for (int k = 0;k<EntryFiles.size(); k++)
    {
        System.out.println("page index" + k);
      EntryIcons.add(new JLabel(EntryFiles.get(k).getName().split("\\.")[0])); 
      
      if(k!=0 ) 
      {
      x = x +( EntryIcons.get(k-1).getWidth() + 20);
      if((k)%3 == 0)
        {
            x = 30;
            System.out.println(EntryIcons.get(k).getText() + "true");
            y = y + EntryIcons.get(k-1).getHeight() + 20;
            if(k%MaxEntryIcons == 0)
            {
                System.out.println("resizing");
            MainPanel.setPreferredSize(new Dimension(MainPanel.getWidth(),  (JournalKit.getInstance().MinimumSize.height + MainPanel.getSize().height)));
            MainPanel.setSize(new Dimension(MainPanel.getWidth(),  (JournalKit.getInstance().MinimumSize.height + MainPanel.getSize().height)));
            MainPanel.revalidate();
            //MainPanel.repaint();
            }
        }
      }         
        EntryIcons.get(k).setIcon(getThumbnails().get(k));
        EntryIcons.get(k).setHorizontalTextPosition(JLabel.CENTER);
        EntryIcons.get(k).setVerticalTextPosition(JLabel.BOTTOM);
        EntryIcons.get(k).setHorizontalAlignment(JLabel.CENTER);
        EntryIcons.get(k).setSize(new Dimension(EntryIconsMaxWidth,70));
      //  EntryIcons.get(i).setBorder(new LineBorder(Color.BLACK, 1));
        EntryIcons.get(k).setLocation(new Point(x,y));        
        Entries.add(Serializer.DeSerialize(getThis().getEntryFiles().get(k).getAbsolutePath()));
        setEntryIconsListeners(k);
              System.out.println(x);
        MainPanel.add(EntryIcons.get(k));
    }
    }
}
public void updateEntryIcons() throws IOException, FileNotFoundException, ClassNotFoundException
{
   int x = 30;
    int y = 0;
    MainPanel.removeAll();
   // setInfoView();
    //setInfoViewPanel();
    EntryIcons.clear();
    Entries.clear();
    MainPanel.setSize(new Dimension(JournalKit.getInstance().MinimumSize));
    if(!EntryFiles.isEmpty())
    {
    for(int k = 0; k<EntryFiles.size();k++)
    {
        System.out.println("page index" + k);
      EntryIcons.add(new JLabel(EntryFiles.get(k).getName().split("\\.")[0])); 
      
      if(k!=0 ) 
      {
      x = x +( EntryIcons.get(k-1).getWidth() + 20);
      if((k)%3 == 0)
        {
            x = 30;
            System.out.println(EntryIcons.get(k).getText() + "true");
            y = y + EntryIcons.get(k-1).getHeight() + 20;
            if(k%MaxEntryIcons == 0)
            {
            System.out.println("resizing");
            MainPanel.setPreferredSize(new Dimension(MainPanel.getWidth(), (JournalKit.getInstance().MinimumSize.height + MainPanel.getSize().height)));
            MainPanel.setSize(new Dimension(MainPanel.getWidth(),  (JournalKit.getInstance().MinimumSize.height + MainPanel.getSize().height)));
            MainPanel.revalidate();
         //   MainPanel.repaint();
           
            }
        }
      }
         
        EntryIcons.get(k).setIcon(getThumbnails().get(k));
        EntryIcons.get(k).setHorizontalTextPosition(JLabel.CENTER);
        EntryIcons.get(k).setVerticalTextPosition(JLabel.BOTTOM);
        EntryIcons.get(k).setHorizontalAlignment(JLabel.CENTER);
      //  int width = JournalKit.getPixelWidthFromText(EntryIcons.get(k).getFont(), EntryIcons.get(k).getText(), EntryIcons.get(k));       
        EntryIcons.get(k).setSize(new Dimension(EntryIconsMaxWidth,70));
      //  EntryIcons.get(i).setBorder(new LineBorder(Color.BLACK, 1));
        EntryIcons.get(k).setLocation(new Point(x,y));        
        Entries.add(Serializer.DeSerialize(getThis().getEntryFiles().get(k).getAbsolutePath()));
        setEntryIconsListeners(k);           
        System.out.println(x);
        MainPanel.add(EntryIcons.get(k));
        
    }
    }
    //setWriteEntry();
}
public  void setInfoView()
{
InfoView  = new Drawing("+", Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 50, 50);
	InfoView.setLocation(435, 20);
        InfoView.setOpaque(false);
	InfoView.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MaxSize))
			{
			hideInfoView();
			}
			else
			{
                           showInfoView();
			}
		}
		
	});

}
public EntriesView getThis()
{
    return this;
}
public JLabel getWriteEntry()
{
    return WriteEntry;
}
public String toString()
{
    return "Journal";
}
public ArrayList<File> getEntryFiles()
{
    return EntryFiles;
}
public void setInputPages()
{
}
public void setThumbnails()
{
Thumbnails = new ArrayList<ImageIcon>();
}
public ArrayList<ImageIcon> getThumbnails()
{
    return Thumbnails;
}
public void updateThumbnails() throws IOException
{
    Thumbnails.clear();
 for(int i = 0;i<EntryFiles.size();i++)
 {
     Thumbnails.add(new ImageIcon(ImageLoader.fromUrl((InputPage.class.getClassLoader().getResource("Images/JournalLogoV2.png"))).getResizedToSquare(50, .1).getBufferedImage()));
 }   
}
public void setTimer()
{
    thisTimer = new Timer(20, this);

}
public Timer getThisTimer()
{
    return thisTimer;
}
    @Override
    public  synchronized void actionPerformed(ActionEvent e) 
    {
        ArrayList<File> files = new ArrayList<>();
        //this.repaint();
    try {
       this.wait(100);
    } catch (InterruptedException ex) {
        Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
    }
        File[] x = new File(JournalKit.EntriesPath).listFiles();            
            
    for(int i = 0;i<x.length;i++)
    {       
        if(x[i].getName().contains(JournalKit.EntriesExntension))
        {
            
        files.add(x[i]);
        }
    }  
        if(files.size()!=EntryFiles.size())
        {
            System.out.println("----------------------------------------------------------updating list of entries!");
            updateEntryFiles();
            try {
                updateThumbnails();
            } catch (IOException ex) {
                Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                updateEntryIcons();
            } catch (IOException ex) {
                Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MinimumSize) && InfoView != null)
        {
        hideInfoView();
        }
        else if(Journal.getInstance().getSize().equals(JournalKit.getInstance().MaxSize)  && InfoView != null)
        {
         showInfoView();
        }
        if(files.isEmpty())
        {
        MainPanel.add(NoEntries);
        }        
//        else
//        {
//            if(JournalIconView.getText() != null)
//            {
//            JLabel entry  = getSelectedEntryIcon();
//            if(!JournalIconView.getText().isEmpty() && entry != null)
//            {
//                entry.setBorder(null);
//            }
//            }
//        }
        JournalKit.TrashObjects();
        
    }

private void setPagesOf(Entry e) throws IOException
{
for(int j = 0;j<e.getPages().size();j++)
         {
             if(j == 0)
           InputPages.add(new InputPage(e.getPage(j), true));
             else
           InputPages.add(new InputPage(e.getPage(j), false));
         }

}
private void setEntryIconsListeners(int index)
{
    final int i = index;
    EntryIcons.get(i).addMouseListener(new MouseAdapter()  
                
                {
                     public void mousePressed(MouseEvent e)
            {
                //EntryIcons.get(i).setBorder(null);
            }
            public void mouseReleased(MouseEvent e)
            {
               //EntryIcons.get(i).setBorder(null);
            }
             public void mouseEntered(MouseEvent e)
            {
               // EntryIcons.get(i).setBorder(new LineBorder(Color.BLACK,1));
            }
            public void mouseExited(MouseEvent e)
            {
             //   EntryIcons.get(i).setBorder(null);
            }
                    public void mouseClicked(MouseEvent e)
                    {
                        for(Entry x: Entries)
                        {
                        System.out.println(x.getEntryName());
                        }
                        if(EntryIcons.get(i).getBorder() == null)
                       {
                       selectEntry(i);
                       }
                        
                        else if(EntryIcons.get(i).getBorder() != null)
                        {
                        try 
                        {
                            //System.out.println("testing " + new InputPage(Entries.get(k).getPage(k)));
                            //setPagesOf(Entries.get(i));            
                            unselectEntries();
                      Journal.getInstance().setCurrentEntry(Entries.get(i));                         
                      Journal.getInstance().getCurrentEntry().setEntryState(EntryState.Saved);
                      Journal.getInstance().setCurrentSavedtEntryFile(EntryFiles.get(i));
                      System.out.println("current entry: " + Journal.getInstance().getCurrentEntry());
                      System.out.println("mumber of entries: " + Entries.size());
                      InputPage.resetCurrentPage();
       for(int k = 0;k<Entries.get(i).getPages().size();k++)
        {
        if(k == 0)
         InputPages.add(new InputPage(Entries.get(i).getPage(k), true));
        else
          InputPages.add(new InputPage(Entries.get(i).getPage(k), false));
        
        }        
                             Journal.getInstance().setPages(InputPages);
                            Journal.getInstance().showPages();
                        } catch (InterruptedException | BadLocationException | IOException ex) {
                            Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    }
                });
}
public  void hideInfoView()
{
    Journal.getInstance().setSize(JournalKit.getInstance().MinimumSize);
    Journal.getInstance().setPreferredSize(JournalKit.getInstance().MinimumSize);
    InfoView.setDrawText("+");
}
public  void showInfoView()
{
    Journal.getInstance().setSize(JournalKit.getInstance().MaxSize);
    Journal.getInstance().setPreferredSize(JournalKit.getInstance().MaxSize);
    InfoView.setDrawText("-");
}
public void setNoEntries()
{
NoEntries = new JLabel("<html>No Entries.<br>Press \""+ CommandSymbolHTML +"+N\" to start a new entry.<html>");
//NoEntries.setHorizontalTextPosition(JLabel.CENTER);
//NoEntries.setVerticalTextPosition(JLabel.BOTTOM);
NoEntries.setSize(new Dimension(500, 100));
NoEntries.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
NoEntries.setForeground(Color.GRAY);
NoEntries.setBackground(Color.GRAY);
NoEntries.setLocation(new Point(MainPanel.getWidth()/6, JournalKit.getInstance().MinimumSize.height/3));
}
public JLabel getEntries()
{
return NoEntries;
}
public void setNoEntrySelected()
{
NoEntrySelected = new JLabel("No Entry Selected");   
NoEntrySelected.setSize(new Dimension(500, 100));
NoEntrySelected.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
NoEntrySelected.setForeground(Color.GRAY);
NoEntrySelected.setBackground(Color.GRAY);
NoEntrySelected.setLocation(new Point(JournalIconView.getLocation()));
NoEntrySelected.setVisible(true);
}
public JLabel getNoEntrySelected()
{
    return NoEntrySelected;
}
public void setInfoViewPanel() throws IOException
{
    InfoViewPanel = new JPanel();
    InfoViewPanel.setLayout(null);   
    System.out.println("size of this: " + this.getSize());
    InfoViewPanel.setBounds(JournalKit.Instance.MinimumSize.width+2, 0, JournalKit.getInstance().MaxSize.width -JournalKit.Instance.MinimumSize.width, JournalKit.getInstance().Height- MainPanelInitiaY);
    //InfoViewPanel.setPreferredSize(new Dimension(JournalKit.getInstance().MaxSize.width -JournalKit.Instance.MinimumSize.width, JournalKit.getInstance().Height));
    InfoViewPanel.setBackground(Color.WHITE);
    InfoViewPanel.setLayout(null);
  // InfoViewPanel.setBorder(new LineBorder(Color.BLACK,1));
    InfoViewPanel.setVisible(true);
    InfoViewPanel.addMouseListener(new MouseAdapter()
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
        System.out.println("Here!");
        }
    });    
        setJournalIconView();
     setNoEntrySelected();
    this.add(InfoViewPanel);
}

public JPanel getInfoViewPanel()
{
    return InfoViewPanel;
}
public void moveMainPanelUp(double amount)
{
double x =  Math.abs(amount);
MainPanel.setLocation(new Point(MainPanel.getX(), (int) (MainPanel.getY()-x)));

if(Math.abs(MainPanel.getY())>getYScrollingLimit())
{
    MainPanel.setLocation(MainPanel.getX(), -getYScrollingLimit());
}
}
public void moveMainPanelDown(double amount)
{
double x = Math.abs(amount);
MainPanel.setLocation(new Point(MainPanel.getX(), (int) (MainPanel.getY()+x)));
 System.out.println("down limit");
    if(MainPanel.getY()>0)
{
    MainPanel.setLocation(MainPanel.getX(), 0);
}
}
private int getYScrollingLimit()
{
    return MainPanel.getHeight()-getThis().getHeight();
}
public  void setToolBar()
{
    ToolBar = new JPanel();
    ToolBar.setOpaque(false);
    ToolBar.setSize(new Dimension(JournalKit.getInstance().MinimumSize.width, MainPanelInitiaY));
    ToolBar.setLayout(null);
    ToolBar.setLocation(0,0);  
    ToolBar.setVisible(true);    
    setHelpQuestionMark();
  //  ToolBar.setBorder(new LineBorder(Color.black,1));
    ToolBar.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Tool Bar");
        }
    });
    ToolBar.add(InfoView);   
    ToolBar.add(WriteEntry);
    ToolBar.add(HelpQuestionMark);
}
public  JPanel getToolBar()
{
    return ToolBar;
}
public void setHeart() throws IOException
{
Heart = new JLabel();
Heart.setLocation( -10 + EntryInfoBox.getWidth()/2 ,0);
Heart.setSize(26,29);

//Heart.setBackground(Color.red);
//Heart.setForeground(Color.red);
Heart.addMouseListener(new MouseAdapter()
{
public void mouseClicked(MouseEvent e)
{
    if(Heart.getText().equals(TransparentHeartCode))
    {
    Heart.setText(RedHeartCode);
     Entry SelectedEntry =  getSelectedEntry();
     if(SelectedEntry != null)
     {
     SelectedEntry.setLIkeThisEntry(true);
        try 
        {
          Serializer.serialize(SelectedEntry, SelectedEntry.getEntryName() + ".entry");
        } catch (IOException ex) 
        {
            Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    }
    else
    {
    Heart.setText(TransparentHeartCode);
     Entry SelectedEntry =  getSelectedEntry();
     if(SelectedEntry != null)
     {
     SelectedEntry.setLIkeThisEntry(false);    
      try 
        {
          Serializer.serialize(SelectedEntry, SelectedEntry.getEntryName() + ".entry");
        } catch (IOException ex) 
        {
            Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntriesView.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    }
}
});
}
public JLabel getHeart()
{
    return Heart;
}

public void setJournalIconView() throws IOException
{
    JournalIconView = new JLabel(new ImageIcon(ImageLoader.fromUrl((InputPage.class.getClassLoader().getResource("Images/JournalLogoV2.png"))).getResizedToSquare(80, .1).getBufferedImage()));
   // JournalIconView.setText(getSelectedEntry().getEntryName());
    JournalIconView.setHorizontalTextPosition(JLabel.CENTER);
    JournalIconView.setVerticalTextPosition(JLabel.BOTTOM);
    JournalIconView.setHorizontalAlignment(JLabel.CENTER);
    JournalIconView.setSize(JournalIconView.getIcon().getIconWidth()*2, (int) (JournalIconView.getIcon().getIconHeight()*1.5));
    JournalIconView.setLocation((InfoViewPanel.getWidth()/2)-60, (InfoViewPanel.getHeight()/2) -(200));
    JournalIconView.setVisible(false);
    //JournalIconView.setBorder(new LineBorder(Color.BLACK));
}
public JLabel getJournalIconView()
{
    return JournalIconView;
}
public Entry getSelectedEntry()
{
    Entry e = null;    
    for(int i =0 ;i<EntryIcons.size();i++)
    {
        if(EntryIcons.get(i).getBorder() != null)
        {
        e = Entries.get(i);
        }
    }
    return e;
}
public JLabel getSelectedEntryIcon()
{
    JLabel e = null;
    for(int i =0 ;i<EntryIcons.size();i++)
    {
        if(EntryIcons.get(i).getBorder() != null)
        {
        e = EntryIcons.get(i);
        }
    }
    return e;
}
public void setEntryAuthor()
{
    EntryAuthor = new JLabel();
}
public void setDateCreated()
{
    DateCreated = new JLabel();
    DateCreated.setLocation(EntryInfoBox.getWidth()/5, 5);
}
public void updateDateCreated()
{
    DateCreated.setText( "Written on " +getSelectedEntry().getDate().trim());
    DateCreated.setSize(new Dimension(JournalKit.getPixelWidthFromText(DateCreated.getFont(), getSelectedEntry().getDate().trim(), DateCreated), 40));
}
public void updateDateCreated(int EntryIndex)
{
    DateCreated.setText("Written on " + Entries.get(EntryIndex).getDate().trim());
    System.out.println(  "length :" +Entries.get(EntryIndex).getDate().length());
    DateCreated.setSize(new Dimension(JournalKit.getPixelWidthFromText(DateCreated.getFont(), getSelectedEntry().getDate().trim() , DateCreated) * 2, 60));
   // DateCreated.setBorder(new LineBorder(Color.BLACK));
}
public JLabel getDateCreated()
{
    return DateCreated;
}
public void setEntryInfoBox()
{
    EntryInfoBox = new JPanel();
    EntryInfoBox.setLocation(0,0);
    EntryInfoBox.setLayout(null);
    EntryInfoBox.setSize(new Dimension(JournalIconView.getWidth()*2, 500));
    EntryInfoBox.setVisible(false);
    //EntryInfoBox.setBorder(new LineBorder(Color.BLACK,1));
    //InfoViewPanel.add(EntryInfoBox);
}
public JPanel getEntryInfoBox()
{
    return EntryInfoBox;
}
public void setEntryTags()
{
    EntryTags = new JLabel();
    EntryTags.setLocation(thisHashTag.getX() + thisHashTag.getWidth(), thisHashTag.getY());
    EntryInfoBox.add(EntryTags);
}
public JLabel getEntryTags()
{
    return EntryTags;
}
public void unselectEntries()
{
    JLabel EntryIcon = getSelectedEntryIcon();
    if(EntryIcon != null)
    {
        EntryIcon.setBorder(null);  
        JournalIconView.setText("");
        JournalIconView.setVisible(false);
        //DateCreated.setVisible(false);
        NoEntrySelected.setVisible(true);
        //Heart.setVisible(false);
        EntryInfoBox.setVisible(false);
    }
}
public void setEntryInfoScrller()
{
    EntryInfoScroller = new JScrollPane();
    EntryInfoScroller.setSize(new Dimension(JournalIconView.getWidth()*2, 500));
    EntryInfoScroller.setLocation(JournalIconView.getX() - (JournalIconView.getWidth()/2), JournalIconView.getY() + JournalIconView.getHeight());
    EntryInfoScroller.setViewportView(EntryInfoBox);
    EntryInfoScroller.setBorder(null);
    //EntryInfoScroller.setVisible(false);
    InfoViewPanel.add(EntryInfoScroller);
}
public void selectEntry(int EntryIndex)
{
EntryIcons.get(EntryIndex).setBorder(new LineBorder(Color.BLACK,1)); 
                       for(int j = 0;j<EntryIcons.size();j++)
                       {
                           if(j != EntryIndex)
                           {
                               EntryIcons.get(j).setBorder(null);                                                          
                           }
                       }
                        updateDateCreated(EntryIndex);
                       getJournalIconView().setText(getSelectedEntry().getEntryName());
//                       DateCreated.setVisible(true);
//                       Heart.setVisible(true);
                        updateTagsContent();
                            EntryInfoBox.add(EntryTags);
                            System.out.println(EntryTags);
                       EntryInfoBox.setVisible(true);
                       JournalIconView.setVisible(true);                       
                       NoEntrySelected.setVisible(false);
                       EntryInfoBox.add(getDateCreated());
                       getThis().showInfoView();
                       if(getSelectedEntry().getLikeThisEntry() == true)
                       {
                            Heart.setText(RedHeartCode);  
                            System.out.println("if heart condition");
                       }
                       else
                       {
                             Heart.setText(TransparentHeartCode);  
                             System.out.println("else heart condition");
                             
                       }
}
public void setThisHashTag()
{
    thisHashTag = new Drawing("#:" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font(Font.SERIF, Font.BOLD, 50), 30, 60);
	thisHashTag.addMouseListener(new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("drawing clicked");
		}
	});
        thisHashTag.setLocation(DateCreated.getX(), DateCreated.getY() + DateCreated.getHeight() + thisHashTag.getHeight());
        EntryInfoBox.add(thisHashTag);
}
public Drawing getThisHashTag()
{
    return thisHashTag;
}
public void setTagsContent()
{
    TagsContent = new StringBuilder();    
}
public void updateTagsContent()
{
    TagsContent.delete(0, TagsContent.length());
    int tagLines = 0;
    Entry selectedEntry = getSelectedEntry();
     ArrayList<String> tags = selectedEntry.getTags();     
     String tagText = "";
     if(!tags.isEmpty())
     {
     for(int i = 0;i<tags.size();i++)
     {
        tagText = tagText + tags.get(i);
      }
     if(tagText.length()<= CharactersPerTagLine)
     {
         TagsContent.append(tagText);
     }
     else
     {
      tagLines = (tagText.length()/CharactersPerTagLine) + 1;          
     for(int i =0; i<tagText.length();i++)
     {
     if((i%CharactersPerTagLine) == 0)
     {
     TagsContent.append("<br>");
     
     }
     TagsContent.append(tagText.charAt(i));
     }
     }
     }
     else
     {
         TagsContent.append(NoTags);
     }
       
     EntryTags.setText( "<html>" +TagsContent.toString() + "<html>");
     EntryTags.setSize(JournalKit.getPixelWidthFromText(EntryTags.getFont(), EntryTags.getText(), EntryTags), 50);
     EntryTags.setVisible(true);
    // EntryTags.setBorder(new LineBorder(Color.BLACK, 1));
     System.out.println("first loop " + JournalKit.getPixelWidthFromText(EntryTags.getFont(), EntryTags.getText(), EntryTags) ) ;
     System.out.println("second :" + 30 * tagLines);
}
public StringBuilder getTagsContent()
{
    return TagsContent;
}
public  void setHelpQuestionMark()
{
    HelpQuestionMark = new Drawing("?" , Drawing_Type.Text_Type, SystemColor.textHighlight, new Font("Times New Roman", Font.PLAIN, 50), 30, 75);
    HelpQuestionMark.setLocation(new Point(ToolBar.getWidth()/2, 8));
    HelpQuestionMark.addMouseListener(new MouseAdapter()
    {
        public void mouseClicked(MouseEvent e)
        {
        showCommands();
        }
    });
}
public Drawing getHelpQuestionMark()
{
    return HelpQuestionMark;
}
public void showCommands()
{
    JOptionPane.showMessageDialog(this, "To create a new Entry:" + "\"" + CommandSymbolUnicode + "+N\"\n"
            + "To Exapand Journal View:"+ "\"" +  CommandSymbolUnicode + "+B\"\n"
            + "To Hide Journal View:"+ "\"" +  CommandSymbolUnicode + "+L\"\n" 
            + "To delete Entry(while editing entry):" + "\"" +CommandSymbolUnicode + "+DELETE\"\n"
            + "To exit an Entry:"+"Press " + "\"ESC\"\n"
            + "To save an Entry:" + "\"" + CommandSymbolUnicode + "+S\"\n","Shortcuts", JOptionPane.INFORMATION_MESSAGE, JournalKit.getJournaLogoImage());
                    
}
}
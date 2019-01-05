package JournalApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.*;
import javax.swing.*;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class JournalGreet extends JPanel implements Utility
{

private JPanel contentPane;
private JLabel ViewEntry;
private  JLabel WriteEntry;
private Font Buttonfont;
private Drawing WriteButton;
private Drawing ViewButton;
	public JPanel getContentPane() {
	return contentPane;
}

public JLabel getWriteEntryButton() {
	return WriteEntry;
}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JournalGreet frame = new JournalGreet();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public JournalGreet() {
		showGreetWindow();
		setJournalLogo();
		setGreeter();
		setWriteEntryButton();
		setViewEntryButton();
	}

	public void setWriteEntryButton() {
		WriteButton = new Drawing(140,65, Drawing_Type.Rect_Type, Color.BLACK);
		WriteButton.setLocation(342,250);
//		drawing.setVisible(false);

		add(WriteButton);
		 Buttonfont = new Font(Font.SERIF, Font.PLAIN, 24);
		 WriteEntry = new JLabel("Write Entry");
		 WriteEntry.setFont(Buttonfont);
			WriteButton.add(WriteEntry);
		 addMouseMotionListener(new MouseMotionAdapter() {
		 	@Override
		 	public void mouseMoved(MouseEvent e) 
		 	{
	 			System.out.println("mouse moved!!" + e.getPoint());
	 			System.out.println("location of draing:" + WriteButton.getLocation());
		 		if(JournalKit.isMouseHere(WriteButton, e))
	 			{}
		 		else
		 		{
		 		}
		 	}
		 });
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			}
		});
		//WriteEntryButton.setIcon(WriteEntryPic);
		WriteEntry.setBounds(2, -21, 139, 99);
		add(WriteButton);
		//add(WriteEntryButton);
	}

	public void setViewEntryButton() {
		ViewButton = new Drawing(140,65, Drawing_Type.Rect_Type, Color.BLACK);
		 ViewEntry = new JLabel("View Entry");
		 ViewEntry.setFont(Buttonfont);
		ViewEntry.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setVisible(false);
				System.out.println("View Entry Clicked");
			}
		});
		//ViewEntryButton.setIcon(ViewEntryPic);
		ViewEntry.setBounds(2, -21, 139, 99);
		ViewButton.setLocation(2, 252);
		ViewButton.add(ViewEntry);
	    add(ViewButton);
	}

	public void setGreeter() {
		JLabel Greeter = new JLabel("Welcome to Journal");
		Greeter.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Greeter.setBounds(140, 6, 156, 16);
		add(Greeter);
	}

	public void setJournalLogo() {
		JLabel JournalLogo = new JLabel("");
		JournalLogo.setIcon(Logo);
		JournalLogo.setBounds(175, 34, 100, 113);
		add(JournalLogo);
	}

	public void showGreetWindow() {
		//setTitle("Journal");
		//setResizable(false);
		setBackground(Color.WHITE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, JournalKit.getInstance().Width, JournalKit.getInstance().Height);
		
		//contentPane = new JPanel();
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setLayout(null);
	}
public int getPanelWidth()
{
	return (int)contentPane.getBounds().getHeight();
}
public int getPanelHeight()
{
	return (int)contentPane.getBounds().getHeight();
}
public JLabel getViewEntryButton() {
	return ViewEntry;
}
public String toString()
{
	return "Welcome";
}

public void setSaveButton() {
	// TODO Auto-generated method stub
	
}

public JLabel getSaveButton() {
	// TODO Auto-generated method stub
	return null;
}
//public void paint(Graphics g)
//{
//	super.paint(g);
//	g.setColor(Color.white);
//	g.draw(351,250, 122, 50, true);
//	
//
//}

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

public void setCurrentPage() {
	// TODO Auto-generated method stub
	
}

public JLabel getCurrentPage() {
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

public void setCurrentLIne() {
	// TODO Auto-generated method stub
	
}

public JLabel getCurrentLine() {
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

public void setNumberOfWords() {
	// TODO Auto-generated method stub
	
}

public JLabel getNumberOfWords() {
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

public void setNumberOfPhotos() {
	// TODO Auto-generated method stub
	
}

public JLabel getNumberOfPhotos() {
	// TODO Auto-generated method stub
	return null;
}
public JournalGreet getThis()
{
	return this;
}
}


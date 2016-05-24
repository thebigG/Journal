package JournalApp;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class InputLine extends JPanel implements ActionListener, AttributeSet
{
private  JTextPane textPane;
private  JSeparator separator;
private boolean Clicked;
static final int MaxCharacters = 52;
AbstractDocument doc;
private boolean EndofLine;
private StringBuilder content;
private char LastChar;
private int LineNum;
private ArrayList<Point> caretHistory;
private int Index =  0;
private int TabSize = 0;
private boolean StartofLine;
private int NumOfWords;
//TODO This field could decide weather this line is active or not. This could help me figure out the current line
private boolean Status;
private static StringOperations TextFunction;
//private JournalThreadManager<InputLine.InputLineThread> thisThreadManager;
//private InputLineThread thisThread;
private JPopupMenu Menu;
	public InputLine()  
	{
		//initInputLineThread();
		//initInputLineThreadManager();
		setTextFunction();
		setCaretHistory();
		setContent();
		setBackground(Color.WHITE);
		setBounds(20,0, JournalKit.Instance.MinimumSize.width-20,JournalKit.getInstance().InputLinePanelHeight);
		setLayout(null);
		
		 textPane = new JTextPane(); 
		 textPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
//					Debug.Print("Caret at:" + textPane.getCaret().getDot());
//					Debug.Print("Caret Mark at:" + textPane.getCaret().getMark());
					Debug.Print("Line Num=======> " + LineNum);
					updateLineContent();
					for(int j = 0;j<JournalKit.Instance.NumberofInputLines;j++)
					{
					}
					System.out.println("Current Content=====>" + content.toString());

				}
			});
		 textPane.addKeyListener(new KeyAdapter() {
//		 	@Override
//		 	public void keyPressed(KeyEvent e) 
//		 	{      		
//				updateLineContent();
//				if(e.getKeyCode() == KeyEvent.VK_TAB)
//		 		{
//					TabSize+=9;
//					Debug.Print("tabsize" + TabSize);
//		 		}
//				else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
//				{
//					if(TabSize>=9)
//					{
//						TabSize-=9;
//						Debug.Print("tabsize" + TabSize);
//
//					}
//				}
				//Debug.Print(content.toString());
//				Debug.Print("Current Content=====>" + content.toString());
//				Debug.Print("size of content:" + content.length());
//				Debug.Print("Caret at:" + textPane.getCaret().getDot());
//				Debug.Print("Caret Mark at:" + textPane.getCaret().getMark());
//				Debug.Print("Magic Pos:" + textPane.getCaret().getMagicCaretPosition());
		 //	}
		 	public void keyReleased(KeyEvent e)
		 	{
		 		
		 		updateLineContent();
				Debug.Print("Current Content=====>" + content.toString());
				
		 	}
		 	public void keyTyped(KeyEvent e)
		 	{                            
                            if(!JournalKit.hasSpecialChars(e) && (Journal.getInstance().getCurrentEntry().getEntryState() == EntryState.Saved) )
                            {
                                System.out.println("Modified");
                                Journal.getInstance().getCurrentEntry().setEntryState(EntryState.Modified);                                
                            }                                                           
                                updateLineContent();
		 	}

		 });
		 textPane.addCaretListener(new CaretListener() {
		 	public void caretUpdate(CaretEvent e) 
		 	{
		 		Debug.Print("caret dot at------"+e.getDot());
		 		if(e.getDot() == MaxCharacters)
		 		{
		 			EndofLine = true;
		 			Debug.Print(EndofLine);
		 		}
				updateLineContent();
				if(e.getDot() == 0)
				{
					StartofLine = true;
					Debug.Print("=======>???????????????? start of line :" +StartofLine);
				}
				Debug.Print("Current Content=====>" + content.toString());
				System.out.println(Journal.getInstance().getSize());
				//textPane.setDocument((StyledDocument)(new JTextFiledFilter(MaxCharacters)));
		 	}
		 });
		 textPane.setSize(WIDTH, JournalKit.getInstance().InputTextPaneHeight);
		 StyledDocument styledDoc = textPane.getStyledDocument();
		 if (styledDoc instanceof AbstractDocument) {
		     doc = (AbstractDocument)styledDoc;
		     doc.setDocumentFilter(new DocumentSizeFilter(MaxCharacters, DocumentSizeFilter_Type.No_Tab));
		 } 
		textPane.setFont(JournalKit.getInstance().PageFont);
		textPane.setBounds(0,JournalKit.getInstance().InputTextPaneYcord,  JournalKit.getInstance().MinimumSize.width,JournalKit.getInstance().InputTextPaneHeight);
		add(textPane);
		separator = new JSeparator();
		separator.setForeground(SystemColor.textHighlight);
		separator.setBounds(0, JournalKit.getInstance().InputSperatorYcord,JournalKit.getInstance().MinimumSize.width-48, JournalKit.getInstance().LineSepratorHeight);
		add(separator);
	}
public JTextPane getTextPane()
{ 
	return textPane;
}
public JSeparator getSeperator()
{
	return separator;
}
public void setClicked(boolean b)
{
	Clicked  = b;
}
public boolean getClicked()
{
return Clicked;	
}
public void setStartingPxlYPoint() 
{	
}
public boolean hasText()
{
	boolean test = false;
	if(textPane.getText().isEmpty())
	{
		test = false;
	}
	else
	{
		test = true;
	}
	return test;
}
public String getText()
{
	return textPane.getText();
}
public void setDataSaving()
{
	
}
public boolean isEndofLine()
{
	return EndofLine;
}
public void setStatus(Object o) 
{	
}
public void setContent()
{
	content = new StringBuilder();
}
public StringBuilder getContent()
{
	return content;
}
public String getLastWord()
{
	StringBuilder text = new StringBuilder();
	char[] arrayText = content.toString().toCharArray();
	for(int k = arrayText.length-1;k>=0 && !Character.isSpaceChar(arrayText[k]);k--)
	{
		Debug.Print(k);
		Debug.Print((!Character.isSpaceChar(arrayText[k])));
		{
			text.append(arrayText[k]);
		}
	}
	Debug.Print("Last word is"+ StringReverser.Reverse(text.toString().toCharArray(), text.length()));
	return StringReverser.Reverse(text.toString().toCharArray(), text.length());
}
/**
 * This method decides weather to pass text to the next line or not.
 * This method is intended to handle tab sizes since they can be messy.
 * @return
 */
public boolean passToNextLine()
{
	boolean test = false;
	if(content.length() >= MaxCharacters)
	{
		test = true;
	}
	return test;
}
//TODO Make this method static and that it takes two parameters 
//TODO The instance and the text which the last word will be extracted from
public void deleteWord(String word)
{
	char [] array = word.toCharArray();
	
	Debug.Print("length of the last word " + array.length);
	Debug.Print("calculation:"+ (content.length()-array.length) );
	final int Constant = content.length()-array.length;
	Debug.Print( "constant" +Constant);
	for(int k =content.length()-1;k>=Constant;k--)
	{
		Debug.Print("deleted char: "+content.charAt(k));;
		content.deleteCharAt(k);
	}
	Debug.Print("settext content:"+content.toString());
	textPane.setText(content.toString());
}
/**
 * deletes the characters from start (inclusive) to end (exclusive). 
 * @param start where to start deleting in the line.
 * @param end where to end deleting in the line.
 */
public void delete(int start, int end)
{
	content.delete(start, end);
	textPane.setText(content.toString());
}
public void setLastChar(char Letter)
{
	LastChar = Letter;
}
public char getLastChar()
{
return LastChar;	
}
public  void updateLineContent()
{
	content.delete(0, content.length());
	content.append(textPane.getText());
	//textPane.setText(content.toString());
	cleanTabs(textPane.getText(), MaxCharacters);
        if(Journal.getInstance().getPages() != null)
        {
	if(!Journal.getInstance().getPages().isEmpty())
	{
	updateNumOfWords(content.toString());
	InputPage.getNumberOfWordsDescription().setText((JournalKit.getNumberOfWords()) + " Words");
	//System.out.println(InputPage.getNumberOfWordsDescription().getText());
	InputPage.getCurrentLineDescription().setText("Current Line: " + InputPage.AddedCurrentLine);
        //System.out.println("Current line updating " + InputPage.CurrentLine);
	InputPage.getCurrentPageDescription().setText("Current Page: " + (InputPage.CurrentPage + 1) + " of " + (JournalKit.NumberOfPages));
	JournalKit.updatePages();
	}
	//System.out.println("Number of words :" + NumOfWords);
	if(!JournalKit.isNull(Journal.getInstance().getPages()) && Journal.getInstance().getPages().size()>0)
	{
	
		//System.out.println("Input Page Content " + Journal.getInstance().getPage(InputPage.CurrentPage).getContent().toString() + "End of page contemt");
		
	}
        }
}
public void setLineNum(int Line)
{
	LineNum = Line;
}

public int getLineNum()
{
	return LineNum;
}
public void formatTab()
{
	if(caretHistory.size()>=2)
	{
		Debug.Print("caret history:" + caretHistory);
	double Division = caretHistory.get(caretHistory.size()-2).x/(8.6);
	int Dot = (int) Math.round(Division);
	Debug.Print("new Dot at:" + Dot );
	textPane.getCaret().setDot(Dot);
	}
	else
	{
		double Division = caretHistory.get(caretHistory.size()-1).x/8.6;
		int Dot = (int) Math.round(Division);
		textPane.getCaret().setDot(Dot);
		textPane.getCaret().setDot(Dot);
	}
	
}
public void setCaretHistory()
{
	caretHistory = new ArrayList<Point>();
}
public int getTabSize()
{
	return TabSize;
}
public void addToSize(int num)
{
	TabSize +=num;
}
public void actionPerformed(ActionEvent e) 
{
	updateLineContent();
	System.out.println("Current Size:" + Journal.getInstance().getSize());
}
public boolean isStartofLine()
{
	return StartofLine;
}
public int getAttributeCount() {
	// TODO Auto-generated method stub
	return 0;
}
public boolean isDefined(Object attrName) {
	// TODO Auto-generated method stub
	return false;
}
public boolean isEqual(AttributeSet attr) {
	// TODO Auto-generated method stub
	return false;
}
public AttributeSet copyAttributes() {
	// TODO Auto-generated method stub
	return null;
}
public Object getAttribute(Object key) {
	// TODO Auto-generated method stub
	return null;
}
public Enumeration<?> getAttributeNames() {
	// TODO Auto-generated method stub
	return null;
}
public boolean containsAttribute(Object name, Object value) {
	// TODO Auto-generated method stub
	return false;
}
public boolean containsAttributes(AttributeSet attributes) {
	// TODO Auto-generated method stub
	return false;
}
public AttributeSet getResolveParent() {
	// TODO Auto-generated method stub
	return null;
}
/**
 * This method is ONLY for the first line of the first page of an Entry.
 * It accommodates this line for the date of this Entry
 */
public void formatLine()
{
	int NumberofSpaces;
	StringBuilder line = new StringBuilder();
	NumberofSpaces = (InputLine.MaxCharacters - JournalKit.getDate().length());
	for(int i = 0;i<NumberofSpaces + 1;i++)
	{
		if( i ==NumberofSpaces )
		{
			line.append(JournalKit.getDate());
		}
		else
		{
			line.append(" ");
		}
		textPane.setText(line.toString());
	}
	
}
/**
 * This version of formatLine is for an entry that already exists.    
 * @param text the date of this entry that already exists
 */
public void formatLine(String text)
{
    int NumberofSpaces;
	StringBuilder line = new StringBuilder();
	NumberofSpaces = (InputLine.MaxCharacters - text.length());
	for(int i = 0;i<NumberofSpaces + 1;i++)
	{
		if( i ==NumberofSpaces )
		{
			line.append(text);
		}
		else
		{
			line.append(" ");
		}
		textPane.setText(line.toString());
	}
}
public void setTextFunction()
{
	TextFunction = new StringOperations();
}
public  void updateNumOfWords(String text)
{
	TextFunction.setText(text);
	NumOfWords = TextFunction.WordCount(1, 0);
	//System.out.println("Words Count: " + NumOfWords);
	
}
public  int getWordCount()
{
	return NumOfWords;
}
public void setStatus(boolean s)
{
	Status = s;
}
public boolean getStatus()
{
	return Status;
}
public boolean isItFull()
{
	return(content.length() >= MaxCharacters);
}
/**
 * This line clears all of the content of this line.
 */
public void clearAll()
{
	this.getTextPane().setText("");
	content.delete(0, content.length());
}
/**
 * Adds text to this InputLine
 */
public void addText(String txt)
{
}
/**
 * This method INITIALIZES the thread manager of this class it DOES NOT start it.
 */
//private void initInputLineThreadManager()
//{
//	thisThreadManager = new JournalThreadManager<InputLineThread>(thisThread);
//}
//public void startInputLineThreadManager()
//{
//thisThreadManager.start();	
//}
//private void initInputLineThread()
//{
//	thisThread =  new InputLineThread();
//}
/**
 * This method eliminates any tabs found in text.
 * @param text The String to be scanned for Tabs
 * @return The string that has been modified.
 */
public String cleanTabs(String text)
{
	//System.out.print("Tab method");	
	//System.out.println(text);
	StringBuilder textBuilder  = new StringBuilder(text);
	for(int i = 0;i<textBuilder.length();i++)
	{
		if(textBuilder.charAt(i) == 9)
		{
			textBuilder.replace(i, i+1, "");
		}
			
	}
	//System.out.println("number of characters== " + textBuilder.length());
	return textBuilder.toString();
}
public String cleanTabs(String text, int CharLimit)
{
	
	//System.out.print("Tab method");	
	//System.out.println(text);
	StringBuilder textBuilder = new StringBuilder(text);
	int numberOfTabs = numberOfTabs(text);
	if((numberOfTabs * JournalKit.TabInSpaces.length()) + (text.length()) <= CharLimit)
	{
	for(int i = 0;i<textBuilder.length();i++)
		{
		if(textBuilder.charAt(i) == JournalKit.TabUnicode)
			{
			textBuilder.replace(i, i+1, JournalKit.TabInSpaces);
			}
		}		
	}
	else 
	{
		int numberOfSpaces =  (numberOfTabs * JournalKit.TabInSpaces.length()) - (CharLimit);
		//System.out.println("else statement");
		for(int i = 0;i<textBuilder.length();i++)
			{
			if(textBuilder.charAt(i) == JournalKit.TabUnicode)
				{
				textBuilder.replace(i, i+1, JournalKit.getNumberOfSpaces(numberOfSpaces));
				}
			}		
		
		
	}
	//System.out.println("number of characters== " + textBuilder.length());
	return textBuilder.toString();
}
public int numberOfTabs(String str)
{
	int NumberOfTabs = 0;
	for(int i = 0;i<str.length();i++)
	{
		NumberOfTabs++;
	}
	return NumberOfTabs;
}

//class InputLineThread implements JournalThread
//{
//
//	@Override
//	public void runJournalThread() 
//	{
//		//while(true)
//		{
//		cleanTabs(textPane.getText());
//		}
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
//	/**
//	 * This method will check text and replace tabs for JournalKit.TabSpaces number of Spaces 
//	 * @param text
//	 */
//	
//}
}

package JournalApp;

import javax.swing.*;
/**
 * The string Reverser class sets and stores data to reverse a string.
 * This class also reverses the string
 * @author LorenzoGomez
 *
 */
/*
 StringReverser
 ----------------------------
-Text : String
-Converter : char[]
-Counter : int
-------------------------------
+StringReverser(String, int)
+StringReverser()
+setText(String)
+getText()
+setCounter(int)
+getCounter()
+setCharConverter(String)
+gettCharConverter()
+Reverse(char[], int)
+BreakString(char[], String)
 */

public class StringReverser {
private String Text;
private char[] Converter;
private int Counter;
/**
 * The Constructor StringReverser sets the data needed to reverse a string
 * @param InputText the text from user
 * @param Counter a counter needed for recursive methods
 */

	public StringReverser(String InputText, int Counter ) 
	{
		setText(InputText);
		setCounter(Counter);
		setCharConverter(InputText);

	}
	public StringReverser()
	{
		
	}
	/**
	 * stores a string into the Text variable
	 * @param InputText text from user
	 */
public void setText(String InputText)
{
	Text = InputText;
}
/**
 * 
 * @return  the Text String
 */
public String getText()
{
	return Text;
}
/**
 * 
 * @param InputCounter
 */
public void setCounter(int InputCounter)
{
	Counter = InputCounter;
}
/**
 * 
 * @return
 */
public int getCounter()
{
	return Counter;
}
/**
 * The setCharConverter method creates an array of charactares
 * @param InputText the length  of this string is the size of the array 
 */
public void setCharConverter(String InputText)
{
	Converter = new char[InputText.length()]; 
}
/**
 * 
 * @return The converter array of characters
 */
public char[] gettCharConverter()
{
	return Converter;
}
/**
 * The Reverse method reverses an array of characters
 * @param Reverser the array of characters to be reversed
 * @param text the text to be reversed
 * @param Counter used as index to point at a specific character at the array
 * @return a reversed string
 */
public static String Reverse(char[] Reverser, int Counter)
{

	 if(Counter==0)
	{
		return "";
	}
	else{
	
		return Character.toString(Reverser[Counter-1] ) + Reverse(Reverser,  Counter-1); 
		}
}
/**
 * The BreakString methods turns a string into an array of characters
 * @param Converter the array of characters
 * @param Text the string to be converted into the array of charcaters
 */

public void BreakString(char[] Converter, String Text)
{
		for(int i = 0 ;i<Text.length();i++)
		{
			Converter[i] = Text.charAt(i); 
		}
	
}

}
	


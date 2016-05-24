package JournalApp;

import java.util.*;
/**
 * 
 * @author lorenzogomez
 *The StringOperations class has methods get specific data from text
 */
public class StringOperations 
{
private String text;
private char[] CharacterArray;
private CharacterCounter[] myCounter;
/**
 * This constructor stores a string in the text variable
 * @param Text
 */
	public StringOperations(String Text)
	{
		text = Text;

	}
	public StringOperations()
	{
	
	}
/**
 * The WordCount method counts the number of words that a line of text contains	
 * @param Counter This is the number of words that the line of text contains. This parameter should always be
 * passed as 1. If not, the method may not work properly
 * @param index the character Where the count of words should start in the line of text
 * @return The number of words that the text contains
 */
public  int WordCount(  int Counter, int index)
{
	if(text.isEmpty() || JournalKit.isNull(text))
	{
		return 0;
	}
	if(index ==0)
	{
		text = text.trim();
	}
	
	if(index<text.length())
	{
		if(Character.isLetter(text.charAt(index)))
		{
			if(index<text.length()-1)
			{
			if(Character.isWhitespace(text.charAt(index)) )
			{
			 return WordCount(  Counter+1, index+1);
			}
			}
		}
		if(Character.isWhitespace(text.charAt(index))) 
		{

			if(index<text.length()-1)
			{
				if(Character.isLetter(text.charAt(index+1)))
				{
				return WordCount( Counter+1, index+1);
				}
			}
		}
		return WordCount(Counter, index+1);
	}
	else
	{
		return Counter;
	}
	
}
/**
 * The arrayToString method displays the specified array of characters in a full line of text
 * @param Array the array of characters to be displayed
 */
public void arrayToString(char[] Array)
{
	for(int i = 0; i<Array.length;i++)
	{
	System.out.print( Character.toString(Array[i]));
	}
}
/**
 * The mostFrequent method calculates the most repeated character in a line of text
 * @return the most frequent/repeated character on a line of text
 */
public char mostFrequent()
{
	text.toUpperCase();
	text = text.trim();
	char[] CharArray = text.toCharArray();
	myCounter = new CharacterCounter[CharArray.length];
	for(int i = 0; i<myCounter.length; i++)
	{
		myCounter[i] = new CharacterCounter(CharArray[i]);
	}
	CharacterCounter MaxCounter = myCounter[myCounter.length-1];
	char MaxCharacter = MaxCounter.getCharacter();
	for(int letter = 0; letter<myCounter.length; letter++)
	{
		for(int index = 0;index<myCounter.length; index++ )
		{
			if(myCounter[letter].getCharacter() == myCounter[index].getCharacter() && index!=letter)
			{
				myCounter[letter].increaseChar();				
			}
			if(index == myCounter.length-1 && letter == myCounter.length-1)
			{
				for(int k = myCounter.length-1; k>0; k--)
					
				{
					
					if(myCounter[k-1].getCounter()> MaxCounter.getCounter())
					{
						MaxCharacter = myCounter[k-1].getCharacter();
						MaxCounter = myCounter[k-1];
					}
					else if(myCounter[k].getCounter()>MaxCounter.getCounter())
					{
						MaxCharacter = myCounter[k].getCharacter();
						MaxCounter = myCounter[k];
					}
				}
			}
		}
	}
	return MaxCharacter;
	
}
/**
 * The replaceSubstring method replaces a peace of text for another one
 * @param WholeText the entire text(without replacements)
 * @param Targetext the text that is inside WholeText and is meant to be replaced
 * @param newText the new text that will be replaced by TargetText
 * @return
 */
public String replaceSubstring(String WholeText, String Targetext, String newText)
{
	String Text = WholeText.replaceAll(Targetext, newText);
	return Text;
}
public void setText(String text)
{
	this.text = text;
}

}
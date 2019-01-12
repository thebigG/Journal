package JournalApp;

/**
 * The Character class has the ability to hold a character and keep a count of the specified character
 * @author LorenzoGoomez
 *
 */
public class CharacterCounter 

{
private Character LetterorDigit;
private int Counter = 1;
/**
 * 
 * @param Charcater The character that instance will hold
 */
	public CharacterCounter(char Charcater)
	{
		LetterorDigit = Charcater;
	}
	/**
	 * 
	 * @return the character that the current instance holds 
	 */
	public char getCharacter()
	{
		return LetterorDigit;
	}
	/**
	 * The increasedChar method increases the counter of the current instance by one
	 */
	public void increaseChar()
	{
		Counter+=1;
	}
	public int getCounter()
	{
		return Counter;
	}
	public String toString()
	{
		return LetterorDigit.toString();
	}
	
}

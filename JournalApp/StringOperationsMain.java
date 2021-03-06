package JournalApp;

import javax.swing.*;
import java.util.*;
/**
 * 
 * @author lorenzogomez
 * The StringOperationsMain class gets input from the user and performs operations from the StringOperations class
 * on that input
 *
 */
public class StringOperationsMain 
{

	public static void main(String[] args) 
	{	
		
		Scanner keyboard = new Scanner(System.in);
		// dashes for readability 
		String Dashes = "------------------------------------"; 
		// create a loading effect
		final String Loading = "Calculating...";
		// greet the user
		System.out.println("Hello, I can do some text operations.");
		// display dashes
		System.out.println(Dashes);
		// display a description for the user
		System.out.println("These include the follwoing:\n"
				+ "-Calculate the most frequent character in a line of text\n"
				+ "-Calculate the number of words there is in a line of text\n"
				+ "-Convert a list of characters to full text\n"
				+ "-replace one word for another in a line of text");		
		String FunctionInput;
		boolean Test = false;
		
		//get input from user and validate it	 
			do{
				System.out.println(Dashes);
				System.out.println("Select what you would like to do:");
				System.out.println("To calculate the most frequent character on a line of text type \"frequent\" and press <ENTER>");
				System.out.println("To calculate the number of words a line of text has type \"counter\" and press <ENTER>");
				System.out.println("To convert a  list of characters to full line of text type \"list\" and press <ENTER>");
				System.out.println("To replace/insert a peace of text type \"replace\" and press <ENTER>");
				FunctionInput = keyboard.nextLine();
				FunctionInput = FunctionInput.trim();
				FunctionInput.toLowerCase();
				FunctionInput.replaceAll("\"", "");
				FunctionInput = FunctionInput.trim();
				FunctionInput.toLowerCase();
				if(FunctionInput.equalsIgnoreCase("frequent"))
				{
					Test = true;
				}
				if(FunctionInput.equalsIgnoreCase("counter"))
				{
					Test = true;
				}
				if(FunctionInput.equalsIgnoreCase("list"))
				{
					Test = true;
				}
				if(FunctionInput.equalsIgnoreCase("replace"))
				{
					Test = true;
				}
			}while(!Test);
			
		// if the input from user was "frequent" get input from user and calculate the most frequent character on the text/input
		if(FunctionInput.equalsIgnoreCase("frequent"))
		{
			System.out.println("Enter the text in which the most frequent character will scanned in and press <ENTER>");
			String Text = keyboard.nextLine();
			StringOperations FrequentOperation = new StringOperations(Text);
			System.out.println(Dashes);
			System.out.println(Loading);
			System.out.println(Dashes);
			char MostCharacter = FrequentOperation.mostFrequent();
			System.out.println("The most frequent character in the text is:");
			System.out.println(MostCharacter);			
		}
		// if the input from user was "counter" get input from user and count the number of words the input/text contains 
		if(FunctionInput.equalsIgnoreCase("counter"))
		{
			System.out.println("Enter the text to count words from and press <ENTER>");
			String Text = keyboard.nextLine();
			StringOperations CounterOperation = new StringOperations(Text);
			System.out.println(Dashes);
			System.out.println(Loading);
			System.out.println(Dashes);
			int Number = CounterOperation.WordCount(1, 0);
			System.out.println("The text contains " + Number + " words");
		}
		// if the  input from user was "list" get input from user and display the text representation of the list of charcaters 
		if(FunctionInput.equalsIgnoreCase("list"))
		{
			int size = 100;
			char[] myArray = new char[size];
			String Question;
			for(int i = 0; i<myArray.length;i++)
			{
				System.out.println("Enter Character #" + 1);
				myArray[i] = keyboard.nextLine().charAt(0);
				System.out.println("Are you done?\n"
						+ "Enter \"y\" for yes and \"n\" for no");
				Question = keyboard.nextLine();
				if(Question.charAt(0) == 'y')
				{
					i = myArray.length;
				}
				
			}
			new StringOperations().arrayToString(myArray);

		}
		// if the input from user was "replace" get input from user and replace the text specify by yser
		if(FunctionInput.equalsIgnoreCase("replace"))
		{
			StringOperations ReplaceOperation = new StringOperations();
			String WholeText, TargetText, newText;
			System.out.println("Enter the entire text(including the text to be replaced)");
			WholeText = keyboard.nextLine();
			System.out.println("Enter the text to be replaced(the text inside the entire text)");
			TargetText = keyboard.nextLine();
			System.out.println("Enter the replacement text(this text will be replaced by the previous text entered in the entire text)");
			newText = keyboard.nextLine();
			 String Text = ReplaceOperation.replaceSubstring(WholeText, TargetText, newText);
			 System.out.println("Here is the old text(not replacement has ben done!)");
			 System.out.println(WholeText);
			 System.out.println("Here is the new text:");
			 System.out.println(Text);
			
			
		}
	}
	
	
}
/*
 * Hello, I can do some text operations.
------------------------------------
These include the following:
-Calculate the most frequent character in a line of text
-Calculate the number of words there is in a line of text
-Convert a list of characters to full text
-replace one word for another in a line of text
------------------------------------
Select what you would like to do:
To calculate the most frequent character on a line of text type "frequent" and press <ENTER>
To calculate the number of words a line of text has type "counter" and press <ENTER>
To convert a  list of characters to full line of text type "list" and press <ENTER>
To replace/insert a peace of text type "replace" and press <ENTER>
list
Enter Character #1
j
Are you done?
Enter "y" for yes and "n" for no
n
Enter Character #1
u
Are you done?
Enter "y" for yes and "n" for no
y
ju                                                                                                  
 */



/*
 * Hello, I can do some text operations.
------------------------------------
These include the follwoing:
-Calculate the most frequent character in a line of text
-Calculate the number of words there is in a line of text
-Convert a list of characters to full text
-replace one word for another in a line of text
------------------------------------
Select what you would like to do:
To calculate the most frequent character on a line of text type "frequent" and press <ENTER>
To calculate the number of words a line of text has type "counter" and press <ENTER>
To convert a  list of characters to full line of text type "list" and press <ENTER>
To replace/insert a peace of text type "replace" and press <ENTER>
computer science
------------------------------------
Select what you would like to do:
To calculate the most frequent character on a line of text type "frequent" and press <ENTER>
To calculate the number of words a line of text has type "counter" and press <ENTER>
To convert a  list of characters to full line of text type "list" and press <ENTER>
To replace/insert a peace of text type "replace" and press <ENTER>
frquent
------------------------------------
Select what you would like to do:
To calculate the most frequent character on a line of text type "frequent" and press <ENTER>
To calculate the number of words a line of text has type "counter" and press <ENTER>
To convert a  list of characters to full line of text type "list" and press <ENTER>
To replace/insert a peace of text type "replace" and press <ENTER>
frequent
Enter the text in which the most frequent character will scanned in and press <ENTER>
computer science
------------------------------------
Calculating...
------------------------------------
The most frequent character in the text is:
e

 */

/*
 * Hello, I can do some text operations.
------------------------------------
These include the follwoing:
-Calculate the most frequent character in a line of text
-Calculate the number of words there is in a line of text
-Convert a list of characters to full text
-replace one word for another in a line of text
------------------------------------
Select what you would like to do:
To calculate the most frequent character on a line of text type "frequent" and press <ENTER>
To calculate the number of words a line of text has type "counter" and press <ENTER>
To convert a  list of characters to full line of text type "list" and press <ENTER>
To replace/insert a peace of text type "replace" and press <ENTER>
replace
Enter the entire text(including the text to be replaced)
Java if you were a person I will hunt you down to tell you that I hate you ;)
Enter the text to be replaced(the text inside the entire text)
hate
Enter the replacement text(this text will be replaced by the previous text entered in the entire text)
love
Here is the old text(not replacement has ben done!)
Java if you were a person I will hunt you down to tell you that I hate you ;)
Here is the new text:
Java if you were a person I will hunt you down to tell you that I love you ;)

 */

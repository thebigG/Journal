package JournalApp;

import javax.swing.*;
import javax.swing.text.*;
/***
 * Classes that receive input from user should implement this interface.
 * This interface makes it easy for having ONLY 1 caret for ALL JTEXTCOMPONENT.
 * @author LorenzoGomez
 *
 */
public interface UniversalCaret 
{
void setCaretFor(JTextComponent c);
}

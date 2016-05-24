package JournalApp;

import java.awt.Toolkit;

import javax.swing.text.*;
import javax.swing.text.DocumentFilter.FilterBypass;
/**
 * This class uses almost the same exact code used in DocumentSizeFilter. The difference between the two is the fact
 * that this filter works for JextField while DocumentSizeFilter does not.
 * @author LorenzoGomez
 */
public class JTextFieldFilter extends PlainDocument {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maxCharacters;
	private JTextComponent Component;
    boolean DEBUG = false;
    public JTextFieldFilter(int maxChars, JTextComponent component) 
    {
        maxCharacters = maxChars;
        this.Component = component;
    }

    public void insertString(int offs,
            String str,
            AttributeSet a)
        throws BadLocationException {
        
    	System.out.println("inserting string....." + str);
    	if (DEBUG) {
            System.out.println("in DocumentSizeFilter's insertString method");
        }

        //This rejects the entire insertion if it would make
        //the contents too long. Another option would be
        //to truncate the inserted string so the contents
        //would be exactly maxCharacters in length.
        if ((Component.getText().length()) <= maxCharacters)
            super.insertString(offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }                  

}

package JournalApp;


import javax.swing.text.*;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.Toolkit;
import java.io.*;

import javax.swing.*;


public class DocumentSizeFilter extends DocumentFilter implements Serializable
{
 private  int maxCharacters;
 private  boolean DEBUG = false;
  private DocumentSizeFilter_Type Restriction;
    public DocumentSizeFilter(int maxChars, DocumentSizeFilter_Type restriction) 
    {
        maxCharacters = maxChars;
        Restriction = restriction;
    }
    
    public void insertString(FilterBypass fb, int offs,
                             String str, AttributeSet a) 
        throws BadLocationException {
        if (DEBUG) {
            System.out.println("in DocumentSizeFilter's insertString method");
        }

        //This rejects the entire insertion if it would make
        //the contents too long. Another option would be
        //to truncate the inserted string so the contents
        //would be exactly maxCharacters in length.
        if ((fb.getDocument().getLength() + str.length()) <= maxCharacters)
            super.insertString(fb, offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
    
    public void replace(FilterBypass fb, int offs,
                        int length, 
                        String str, AttributeSet a)
        throws BadLocationException {
        if (DEBUG) 
        {
            System.out.println("in DocumentSizeFilter's replace method");
        }
        if(Restriction == DocumentSizeFilter_Type.No_Tab)
        {
        	str = JournalKit.cleanTabs(str);
        }
        //This rejects the entire replacement if it would make
        //the contents too long. Another option would be
        //to truncate the replacement string so the contents
        //would be exactly maxCharacters in length.
        if ((fb.getDocument().getLength() + str.length()
             - length) <= InputLine.MaxCharacters)
            super.replace(fb, offs, length, str, a);
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }

}



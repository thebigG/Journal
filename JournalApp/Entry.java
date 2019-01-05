package JournalApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
public class Entry extends Object implements Serializable, Editable,ActionListener
{
private String EntryName;
private String Author;
private ArrayList<String> Tags;
private ArrayList<EntryPage> Pages;
private String Date;
private EntryState State;
private Timer thisTimer;
private final static long serialVersionUID = 5;
private boolean LikeThisEntry;
{
    setLIkeThisEntry(false);
}
public Entry() 
{
    State = EntryState.New;
    setThisTimer();
    getThisTimer().start();
}
public Entry(String entryname, String author, String date ,ArrayList<String> tags, ArrayList<EntryPage> pages)
{
    EntryName = entryname;
    Author = author;
    Tags = tags;
    Date = date;
    Pages = pages;
    //getThisTimer().start();
//    System.out.println("constructor called");
}

public void setEntry()
{
    
}
public void setEntryName(String name)
{
    EntryName = name;
}
public String getEntryName()
{
    return EntryName;
}
	
public Entry getEntry()
{
    return this;
}
public void setAuthorName(String name)
{
    Author =  name;
}
public String getAuthorName()
{
    return Author;
}
public void setPages(ArrayList<EntryPage> pages)
{
    this.Pages = pages;
}
public ArrayList<EntryPage> getPages()
{
    return Pages;
}
public EntryPage getPage(int PageNumber)
{
    return Pages.get(PageNumber);
}
public void setEntryState(EntryState state)
{
    State = state;
}
public EntryState getEntryState()
{
    return State;
}
public void setDate(String d)
{
this.Date = d;
}
public String getDate()
{
    return Date;
}
public String toString()
{
    StringBuilder content = new StringBuilder();
    for(int i = 0; i<Pages.size();i++)
    {
    content.append("  page number:").append(i+1).append(Pages.get(i).toString());
    }
   // System.out.println("number of pages :" + Pages.size());
   // System.out.println("reference of this entry: " + super.toString());
    return content.toString();
}
public ArrayList<String> getTags()
{
    return Tags;
}

public void setThisTimer()
{
    thisTimer = new Timer(1,this);
}
public Timer getThisTimer()
{
    return thisTimer;
}
@Override
public void actionPerformed(ActionEvent e) 
{
//    
//  //  State = EntryState.Saved;
//    switch(State)
//    {
//        case Saved:
//        {
//            System.out.println("saved");            
//            break;            
//        }
//        case New:
//        {
//            System.out.println("New ");
//            break;
//        }
//        default:
//        {
//            System.out.println("default");
//            break;
//        }
//        
//    }
//    System.out.println(super.toString());
//    
// System.out.println("timer entry " + super.toString());       
}
@Override
public void finalize() throws Throwable
{
    try {
        thisTimer.stop();
        System.out.println("timer stopped");
    } finally {
        super.finalize();
    }
}
public void setTags(ArrayList<String> tags)
{
this.Tags = tags;
}
public boolean equals(Entry e)
{
    boolean test = true;    
    if(this.Tags.size() != e.getTags().size())
    {
    return false;    
    }
    if(this.getPages().size() != e.getPages().size())
    {
    return false;
    }
    if(!e.getEntryName().equals(this.getEntryName()))
    {
        return false;
    }
    for(int i =0 ;i<e.getPages().size();i++)
    {
        
    }    
    return test;
}
/**
 * This method deletes this Entry object. This doesn't just delete the reference to this object. It also deletes any File/Attachments that may be used by this entry.
     * @param e The entry to be deleted
 */
public static void delete(Entry e)
{
  for(int i = 0;i<e.getPages().size();i++)
  {
      EntryPage.delete(e.getPages().get(i));
  }
  e.setAuthorName(null);
  e.setEntryName(null);
  e.setEntryState(null);
  e.setDate(null);
  e.setTags(null);
  e.setPages(null);
  e.getThisTimer().stop();
 e = null;
 Journal.getInstance().getCurrentEntrySavedFile().delete();
}
public void setLIkeThisEntry(boolean t)
{
LikeThisEntry = t;
}
public boolean getLikeThisEntry()
{
return LikeThisEntry;
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JournalApp;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author lorenzogomez
 */
public class EntryPage implements Serializable
{
private ArrayList<String> Content;
private File Attachment;
private final static long serialVersionUID = 0b110;
public EntryPage(ArrayList<String> content, File attachment)
 {
    Content = content;
    Attachment = attachment;
 }
 public EntryPage(ArrayList<String> content)
 {
      Content = content;
 }
  public EntryPage( File attachment)
 {
    Attachment = attachment;
 }

    public EntryPage()
    {
    }
  
 public void setContent(ArrayList<String> content)
 {
     this.Content = content;
 }
 public ArrayList<String> getContent()
 {
     return this.Content;
 }
 public void setAttachment(File f)
 {
     this.Attachment = f;
 }
 public File getAttachment()
 {
     return this.Attachment;
 }
 public String toString()
 {
     StringBuilder content = new StringBuilder();
     if(Content !=null)
     for(int i = 0;i<Content.size();i++)
     {
      content.append(Content.get(i));   
     }
     return content.toString();
 }
 public boolean equals(EntryPage e)
 {
 boolean test = true;
 
     return test;
 }
 public static void delete(EntryPage p)
 {
     p.setContent(null);   
     if(p.getAttachment() != null)
     p.getAttachment().delete();
 }
}

package JournalApp;

import java.io.File;

/**
 * 
 * @author lorenzogomez
 */

public abstract class PlayableMediaAttachment extends Attachment implements JournalMedia
{

 private String Source;
 private String Title;
   public PlayableMediaAttachment()
   {
       super();
   }
   public PlayableMediaAttachment(String source)
   {
       super(new File(source), Path_Type.Entry);
       setTitle();
   }
public void setSource(String s)
{
    Source = s;
}
public String getSource()
{
    return Source;
}
/**
 * 
 * @return the duration of the media in seconds
 */
public abstract double getDuration();
 @Override
 public void setTitle()
{
   Title  = getFile().toString();
}
 public void setTitle(String title)
 {
     Title = title;
 }
 public String getTitle()
 {
     return Title;
 }
}
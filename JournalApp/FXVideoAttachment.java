package JournalApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lorenzogomez
 */
import javafx.scene.media.*;
public class FXVideoAttachment extends PlayableMediaAttachment
{
private Media thisVideo;
{
    setAttachmentType(Attachment_Type.Video);
}
public FXVideoAttachment(String source)
{
 super(source);   
 setThisVideo(source);
}
public FXVideoAttachment()
{
    
} 
public FXVideoAttachment(Media source)
{
    super();
    thisVideo = source;
}
@Override
    
/**
 * This method returns the duration of this video in seconds 
 */
    public double getDuration() 
    {
        return thisVideo.getDuration().toSeconds(); 
    }  

  public void setThisVideo()
  {
      thisVideo = new Media(getSource());
  }
  public void setThisVideo(String source)
  {
  thisVideo = new Media(source);
  }
  public Media geThisVideo()
  {
      return thisVideo;
  }
  public void delete()
  {
  }

    @Override
    public void setDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDateCreated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDateCreated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDateCreated(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

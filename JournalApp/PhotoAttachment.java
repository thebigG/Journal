package JournalApp;

import ImageUtil.CroppedImage;
import ImageUtil.Format_Type;
import ImageUtil.ImageLoader;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * This class represents a PhotoAttachment. Now, the class itself doe not 
 * represent a photo/JComponent but it does contain one.
 * @author lorenzogomez
 */
public class PhotoAttachment extends Attachment implements JournalMedia
{
private String FilePath;
private Drawing Photo;
private CroppedImage OriginalPhotoImage;
private ImageIcon NewPhotoImage;
private String PhotoAttachmentName;
private Path_Type Type;
{
	setAttachmentType(Attachment_Type.Photo);
}
public PhotoAttachment(File f, Path_Type t)
{
super(f,t);
this.FilePath = f.getAbsolutePath();
System.out.println("What am I passing :"+ FilePath );
this.Type = t;
this.setPhoto();
}
public PhotoAttachment(String FilePath, Path_Type type)
{        
	super(Attachment_Type.Photo, JournalKit.getFormat(FilePath));
        this.FilePath = FilePath;
        if(JournalKit.getFormat(FilePath) == Format_Type.UNKNOWN)
        {
        this.FilePath = this.FilePath + ".JPG";
        }
        setThisJLabel();
        this.Type = type;
        setPhoto();
}
public void setThisJLabel()
{
}
/**
 * This method will set up the drawing object with a NEW resized picture
 */
    private void setPhoto()
{
    try {
        switch(Type)
        {
          
            case Local:              
              OriginalPhotoImage = ImageLoader.fromFile(this.FilePath );
              break;            
            case URL:
             OriginalPhotoImage = ImageLoader.fromUrl((this.FilePath));
             System.out.println("URL lodaing....." + OriginalPhotoImage.getBufferedImage());
             break;
            case Entry:
            OriginalPhotoImage = ImageLoader.fromFile((FilePath));
                break;
        }          
         
         if(OriginalPhotoImage.getWidth()>JournalKit.WidthOfPhoto && this.Type != Path_Type.Entry )
         {     
          OriginalPhotoImage = OriginalPhotoImage.getResizedToWidth(JournalKit.WidthOfPhoto);             
         
        OriginalPhotoImage.writeToJPG(super.getFile().getAbsolutePath(), 0.95f, JournalKit.getFormat(this.FilePath));
         }
        System.out.println("Path: " + super.getFile().getAbsolutePath());
        NewPhotoImage = new ImageIcon(super.getFile().getAbsolutePath());
        System.out.println("image passsed to drawing:" + NewPhotoImage.getDescription());
    } catch (IOException ex) {
        Logger.getLogger(PhotoAttachment.class.getName()).log(Level.SEVERE, null, ex);
    }
            Photo = new Drawing( OriginalPhotoImage.getWidth(),  OriginalPhotoImage.getHeight(),  0,  0, Drawing_Type.Image_Type, NewPhotoImage);
            System.out.println(Photo.getSize());
            Photo.setImageMenu();
            Photo.add(Photo.getImageMenu());
            Photo.setDrawingActions();
            
}
public Drawing getPhoto()
{
    return Photo;
}

    @Override
    public void setTitle() 
    {
     PhotoAttachmentName = getFile().toString();   
    }
@Override
    public void setTitle(String name)
    {
    PhotoAttachmentName = name;
    }
    @Override
    public String getTitle() 
    {
        return PhotoAttachmentName;
    }
    /**
     * Deletes the image file used by this PhotoAttachment instance
     */
    public void delete()
    {
        super.getFile().delete();
    }
    public CroppedImage getNewPhotoImage()
    {
        return OriginalPhotoImage;
    }

    @Override
    public void setDescription() {
        
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescription(String text) {
       
    }

    @Override
    public void setDateCreated() {
       
    }

    @Override
    public String getDateCreated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDateCreated(String date) {
     
    }
}   

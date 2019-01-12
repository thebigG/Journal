package JournalApp;

import ImageUtil.Format_Type;
import JournaLIO.JournaLIOLogic_Type;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author LorenzoGomez
 *This class is an abstract representation of ANY and ALL attachment classes.
 *All attachment interfaces should implement this interface. 
 */
public abstract class Attachment  
{
 
private File AttachmentFile;
private Attachment_Type AttachmentType;
private String fileExtension;
private Path_Type PathType;
public Attachment()
{
	
}
public Attachment(File f, Path_Type path)
{
	setFile(f);
        PathType = path;
        
}
public Attachment(Attachment_Type type, Format_Type fileType)
{
    switch (fileType)
{
    case JPG:
        fileExtension = ".JPG";
        break;
    case PNG:
        fileExtension = ".PNG";
        break;
    case GIF:
        fileExtension = ".GIF";
        break;
        
    default:
        System.out.println("default running!!!!");
        fileExtension = ".JPG";
        break;
}
switch(type)
{
    case Photo:
     setFile("Photo");
     System.out.println("photo case selected");
        break;
    case Video:
        setFile("Video");
        break;
    case Audio:
        setFile("Audio");
        break;
    default:
        System.out.println("the file field of the Attachment class is not set");
        break;        
}

}
public void setFile(File f)
{
	AttachmentFile = f;
}
public void setFile(String FilePath)
{    
    System.out.println("is this running??");
     String FileName = JournalKit.generateRandomName(6);
     switch(JournalKit.DoesThisNameExist(FilePath, FileName))
     {
         case True_TheFileExists:
             System.out.println("true repeated");
             FileName = JournalKit.generateRandomName(6);
             while(JournalKit.DoesThisNameExist(FilePath, FileName) == JournaLIOLogic_Type.True_TheFileExists)
                 FileName = JournalKit.generateRandomName(6);
         break;                 
     }
     File Directory = new File(FilePath);
     if(!Directory.isDirectory())
         Directory.mkdir();
    AttachmentFile = new File(FilePath +  "/" + FileName +fileExtension );
}

public File getFile()
{
	return AttachmentFile;
}
public void setAttachmentType(Attachment_Type type)
{
	AttachmentType = type; 
}
public Attachment_Type getMediaType()
{
    return AttachmentType;
}

}
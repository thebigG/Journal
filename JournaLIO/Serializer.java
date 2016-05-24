package JournaLIO;
import JournalApp.JournalKit;
import java.io.*;

/**
 *
 * @author lorenzogomez
 * This is a FUNCTIONAL class that has the ability to serialize and de-serialize Journal Entries. 
 */
public class Serializer 
{
public static void serialize( Object o) throws FileNotFoundException, IOException
{
String FileName  = JournalKit.generateRandomName(6);
switch(JournalKit.DoesThisNameExist(JournalKit.EntriesPath + "/" + FileName, FileName))
{
    case True_TheFileExists:
        while ((JournalKit.DoesThisNameExist(JournalKit.EntriesPath + "/" + FileName, FileName)) == JournaLIOLogic_Type.True_TheFileExists) 
           FileName  = JournalKit.generateRandomName(6); 
        break;    
}
FileOutputStream writer = new FileOutputStream(JournalKit.EntriesPath + "/" + FileName);
ObjectOutputStream ObjectWriter = new ObjectOutputStream(writer);
ObjectWriter.writeObject(o);
ObjectWriter.close();
}
/**
     * @return 
 * @TODO Remember to check this method. It is NOT completed. There should be a safety net for files names that exist
 * @param o
 * @param fileName
 * @throws FileNotFoundException
 * @throws IOException 
 * @return if it returns true or directory, the object is NOT serialized. If this returns FALSE, then the object was serialized sucessfully.
 */
public static File serialize(Object o, String fileName) throws FileNotFoundException, IOException
{
String FileName  = fileName;
String test = fileName.split("\\.")[0];

switch(JournalKit.DoesThisNameExist(JournalKit.EntriesPath + "/" + FileName, FileName))
{
    case True_TheFileExists:
        // return JournalKit.DoesThisNameExist(JournalKit.EntriesPath + "/" + FileName, FileName);
    case Directory:
         //return JournalKit.DoesThisNameExist(JournalKit.EntriesPath + "/" + FileName, FileName);
}
FileOutputStream writer = new FileOutputStream(JournalKit.EntriesPath + "/" + FileName);
ObjectOutputStream ObjectWriter = new ObjectOutputStream(writer);
ObjectWriter.writeObject(o);
ObjectWriter.close();
return new File(JournalKit.EntriesPath + "/" + FileName);
}
public static <E > E DeSerialize(String FilePath) throws FileNotFoundException, IOException, ClassNotFoundException
{
   FileInputStream Reader = new FileInputStream(FilePath);
    ObjectInputStream ObjectReader = new ObjectInputStream(Reader);    
    E object = (E) ObjectReader.readObject();
return object;
}
}

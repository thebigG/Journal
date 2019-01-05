package JournalApp;


/**
 *This is interface should be implemented by all classes that represent some form video,audio or anything visual like a photo, gif, etc.
 * @author lorenzogomez
 */
public interface JournalMedia 
{
public void setTitle();
public void setTitle(String name);
public String getTitle();
public void delete();
public void setDescription();
public String getDescription();
public void setDescription(String text);
public void setDateCreated();
public String getDateCreated();
public void setDateCreated(String date);
}

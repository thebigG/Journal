package JournalApp;


import java.util.ArrayList;

/**
 * All journal media players MUST extend this class. This is a FUNCTIONAL class to play PlayableMedia. 
 */
public abstract class JournalMediaPlayer<E extends PlayableMediaAttachment>
{
private ArrayList<E> thisMedia;
private ArrayList<String> Sources;
{
    thisMedia = new ArrayList<E>();
}
public JournalMediaPlayer(E... media)
{
    setThisMedia(media);
}
public JournalMediaPlayer()
{

}

public void setThisMedia(E... media)
{
    for (E x : media) 
    {
        thisMedia.add(x);
    }
}
public ArrayList<E>  getThisMedia()
{
    return thisMedia;
}
public E getThisMedia(int media)
{
    return getThisMedia().get(media);
}
}

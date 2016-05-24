package JournalApp;

import javax.swing.*;
import java.awt.*;
public interface TagManager extends Switchable
{
String hash = "#";
public void setTagManager();
public JComponent getTagManager();
public void updateTagManager();
public int getCurrentCaretDot();
public Point getCurrentMagicCaretPos();
public boolean isEmpty();
}

package JournalApp;

import java.awt.*;
/**
 * @author LorenzoGomez
 * <br>
 * <br>
 *This interface should be implemented by classes that have a field for the color that belongs to every instance of the 
 * the class.
 * <br>
 */
public interface ColorAdjustable 
{
public void setColor(Color c);
public Color getColor();
public void setBrightness(int n);
public int getBrightness();
public void setDarkness(int n);
public void makeBrighter();
public void makeDarker();
public void updateComponentColor();
}

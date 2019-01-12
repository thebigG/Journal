package JournalApp;

import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author lorenzogomez
 *This interface should ONLY be implemented when the layout manager of 
 * a Component is NOT set to NULL.
 */
public interface LayoutManageable 
{
void seThisLayoutManager();
LayoutManager getThisLayoutManager();
void updateLayoutManager();
}

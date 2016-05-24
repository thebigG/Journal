package JournalApp;

import java.awt.*;
import javax.swing.*;
public interface BottomUtility 
{
JLabel SaveButton = new JLabel();
JLabel PreviousPageButton = new JLabel();
ImageIcon PreviousIcon = new ImageIcon(BottomUtility.class.getResource("/Images/PreviousPageIconV2.jpg"));
JLabel NextPageButton = new JLabel();
ImageIcon NextIcon = new ImageIcon(BottomUtility.class.getResource("/Images/NextPageIconV2.jpg"));
ImageIcon TagIcon = new ImageIcon(BottomUtility.class.getResource("/Images/TagIconV3.jpg"));
int NumberOfPages = 1;
}

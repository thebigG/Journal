package JournalApp;

import java.awt.*;
import javax.swing.*;
public interface Utility extends TopUtility,BottomUtility
{
//ImageIcon WriteEntryPic = new ImageIcon(Utility.class.getResource("Images/WriteEntryV2.png"));
//ImageIcon ViewEntryPic = new ImageIcon(Utility.class.getResource("Images/ViewEntries-V2.png"));
ImageIcon Logo = new ImageIcon(Utility.class.getResource("Images/JournalLogoV2.png"));
InfoDialog NoFilesSelectedDialog = new InfoDialog("You did not select any filles to be attached");

}

package JournalApp;

import java.awt.*;
import java.awt.event.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import java.io.*;
public class FileBrowser extends JFileChooser
{


	public FileBrowser()
        {
           super();  
	}
        public FileBrowser(File Path)
        {
            super(Path);
        }
}
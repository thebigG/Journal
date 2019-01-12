package JournalApp;

import javax.swing.*;
public interface Tag 
{
int pxlsPerLetter = 10; // constant to be used when drawing letters - This is an estimate 
char hash = '#';
int ActiveStatus = 1;
int  UnActiveStatus  = 0;
public void setTag(String newTag);
public Tag getTag();
public void updateTag(String newTag);
}

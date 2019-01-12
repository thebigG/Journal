package JournalApp;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import javafx.scene.control.Slider;
import java.beans.*;
import javafx.scene.*;
import javafx.scene.media.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is meant to be FUNCTIONAL. This class is able to play video and audio.
 * @author lorenzogomez
 * @param <E>
 */
public class JournalFXMediaPlayer extends JournalMediaPlayer<FXVideoAttachment>
{
private MediaPlayer thisMediaPlayer;
private MediaView thisVideoViewer;
private ArrayList<FXVideoAttachment> Videos;
private ArrayList<String> VideoSources;
private Group thisGroup;
private Scene thisScene;
private Slider thisSlider;
/**
 * 
 * @param width This is the desired width of the Video, if there is one to show.
 * @param height This is the desired height of the Video, if there is one to show.
 * 
 */
{
   Videos = new ArrayList<>() ;
   VideoSources = new ArrayList<>();
}
public JournalFXMediaPlayer(int width, int height, FXVideoAttachment... MediaSource)
{
    super(MediaSource);
    setVideoSources();
    setThisMediaPlayer();
    setThisMediaViewer(width, height);
    setThisGroup();
    setThisScene(width, height);
    ((Group)getThisScene().getRoot()).getChildren().add(thisVideoViewer);
}
public JournalFXMediaPlayer(int width, int height, String... MediaSource)
{
    
    setVideoSources();
    setThisMediaPlayer();
    setThisMediaViewer(width, height);
    setThisGroup();
    setThisScene(width, height);
    ((Group)getThisScene().getRoot()).getChildren().add(thisVideoViewer);
}
public void init()
{
    
}
/**
 * This method ASSUMES that there is only ONE video to play.
 */
private void setThisMediaPlayer()
{
    thisMediaPlayer = new MediaPlayer(getThisMedia(0).geThisVideo());
}

public MediaPlayer getThisMediaPlayer()
{
    return thisMediaPlayer;
}
private void setThisMediaViewer()
{
thisVideoViewer  = new MediaView(); 

thisVideoViewer.setOnMouseClicked(EventHandler->
{
{
    switch(thisMediaPlayer.getStatus())
    {
        case PLAYING:
            thisMediaPlayer.pause();
            break;
        case PAUSED:
            thisMediaPlayer.play();
            break;           
    }
}
});
}
public void setThisMediaViewer(int w, int h)
{
    
thisVideoViewer  = new MediaView(); 

thisVideoViewer.setOnMouseClicked(EventHandler->
{
{
    switch(thisMediaPlayer.getStatus())
    {
        case PLAYING:
            thisMediaPlayer.pause();
            break;
        case PAUSED:
            thisMediaPlayer.play();
            break;           
    }
}
});
thisVideoViewer.setFitHeight(h);
thisVideoViewer.setFitWidth(w);
}
public void setThisGroup()
{
    thisGroup = new Group();
}
public Group getThisGroup()
{
    return thisGroup;
}
public void setThisScene(int w, int h)
{
    thisScene = new Scene(thisGroup ,w,h);
 }
public Scene getThisScene()
{
    return thisScene;
}
/**
 * This method ASSUMES that the Videos(FXVideoAttachment objects) of this class
 * are already instantiated.   
 */
public void setVideoSources()
{
for(FXVideoAttachment x: Videos)
{
    VideoSources.add(x.getSource());
}
}
public void setVideoSources(String... source)
{
    VideoSources.addAll(Arrays.asList(source));
}
public ArrayList<String> getVideoSources()
{
    return VideoSources;
}
}
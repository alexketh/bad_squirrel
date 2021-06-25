package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound{

    private Media m;

    public Sound(String fileName, String location) {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
            m = MediaManager.createMedia(is, location);
        }
        catch(Exception e) {
            System.out.println("Unable to play sound");
        }
    }

    public void pause() {
        m.pause();
    }

    public void play() {
        //start playing from time zero (beginning of the sound file)
        m.setTime(0);
        m.play();
    }

}

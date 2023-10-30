package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundPlayer {

    AudioInputStream inputSound;

    Clip clip;

    public SoundPlayer(String SoundFileURL) {
        try {
            File file = new File(SoundFileURL);
            System.out.print(file.getPath());
            inputSound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputSound);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't read the file");
        }
    }

    public synchronized void startPlay(int loudness) {
        try {

            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            System.out.println("Couldn't play the music");
        }
    }

    public synchronized void startPlayLoop() {
        try {

            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.out.println("Couldn't play the music");
        }
    }


    public synchronized  void stopPlayer() {
        clip.stop();
    }
}
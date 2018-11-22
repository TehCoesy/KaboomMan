package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameMusic {
    File music1 = new File("Data/Audio/game.wav");
    private Clip clip;

    public GameMusic() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        try {
            if (clip.isActive()) {
                clip.stop();
            }

            AudioInputStream _AIS = AudioSystem.getAudioInputStream(music1);

            clip.open(_AIS);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

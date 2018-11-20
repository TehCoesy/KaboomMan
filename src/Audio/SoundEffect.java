package Audio;

import javax.sound.sampled.*;
import java.io.File;

public class SoundEffect {
    private boolean done;

    public SoundEffect(AudioInputStream _AIS) {
        try {
            Clip clip = AudioSystem.getClip();

            clip.open(_AIS);

            clip.start();

            while (clip.isActive()) {
                continue;
            }

            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDone() { return this.done; }
}

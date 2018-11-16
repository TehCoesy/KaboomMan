package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class myAudio {
    private boolean done;

    public myAudio(AudioInputStream _AIS) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(_AIS);
            clip.start();

            while (clip.isActive()) {

            }

            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDone() {
        return this.done;
    }
}

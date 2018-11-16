package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {
    List<myAudio> Sounds = new ArrayList<>();

    public void playExplosion() {
        try {
            File file = new File("Data/Audio/Bomb.wav");
            AudioInputStream _AIS = AudioSystem.getAudioInputStream(file);
            Sounds.add(new myAudio(_AIS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        List<myAudio> done = new ArrayList<>();

        for (myAudio audio : Sounds) {
            if (audio.isDone()) {
                done.add(audio);
            }
        }

        Sounds.removeAll(done);
    }
}

package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {
    private int soundLevel = 1;

    List<SoundEffect> soundEffects = new ArrayList<>();
    GameMusic music = new GameMusic();

    public void update() {
        List<SoundEffect> remove = new ArrayList<>();

        for (SoundEffect element : soundEffects) {
            if (element.isDone()) {
                remove.add(element);
            }
        }

        soundEffects.removeAll(remove);
    }

    public void EXPLODE() {
        try {
            AudioInputStream _AIS = AudioSystem.getAudioInputStream(new File("Data/Audio/bomb_explode.wav"));
            soundEffects.add(new SoundEffect(_AIS));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void SPLASH_MUSIC() {
        try {
            AudioInputStream _AIS = AudioSystem.getAudioInputStream(new File("Data/Audio/oxp.wav"));
            soundEffects.add(new SoundEffect(_AIS));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void PLAYER_WALK() {
        try {
            AudioInputStream _AIS = AudioSystem.getAudioInputStream(new File("Data/Audio/player_walk.wav"));
            soundEffects.add(new SoundEffect(_AIS));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playMusic() {
        music.playMusic();
    }
}

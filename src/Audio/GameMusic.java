package Audio;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class GameMusic {
    public void gameMusic() {
        final JFXPanel fxPanel = new JFXPanel();

        String music = "Data/Audio/game.mp3";

        Media hit = new Media(new File(music).toURI().toString());

        MediaPlayer player = new MediaPlayer(hit);
        player.play();
    }
}

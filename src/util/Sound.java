/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Sidi
 */
public class Sound {

    private static String playingSound = "";
    private static MediaPlayer mediaPlayer = null;

    public static void play(String soundFile) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (playingSound == soundFile) {
            playingSound = "";
            return;
        } else {
            playingSound = soundFile;
        }

        Media sound = new Media(new File(playingSound).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

}

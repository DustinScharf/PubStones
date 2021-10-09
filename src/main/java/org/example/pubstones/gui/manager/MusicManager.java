package org.example.pubstones.gui.manager;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.example.pubstones.gui.util.DelayRunner;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private MediaPlayer mediaPlayer;

    private final Map<String, Media> music;

    private double lastVolume = 1; // MAX VOLUME

    private boolean isPlaying = false;
    private boolean isMuted = false;

    public MusicManager() {
        this.music = new HashMap<>();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMusic(String mp3FilePath) {
//        Media musicMedia = this.music.computeIfAbsent(mp3FilePath, mp3FilePathTemp -> {
//            URL url = getClass().getResource(mp3FilePathTemp);
//            if (url == null) {
//                throw new NullPointerException(); // TODO write own exception
//            }
//
//            String path = url.toString();
//            return new Media(path);
//        });
        Media musicMedia = new Media(getClass().getResource(mp3FilePath).toString());
        this.mediaPlayer = new MediaPlayer(musicMedia);
        this.mediaPlayer.setVolume(this.isMuted ? 0 : this.lastVolume);
        this.mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    public void playMusic(int fadeInSeconds) {
        if (this.isPlaying) {
            return;
        }

        if (fadeInSeconds > 0 && !this.isMuted) {
            this.mediaPlayer.setVolume(0);
            this.mediaPlayer.play();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(fadeInSeconds),
                            new KeyValue(this.mediaPlayer.volumeProperty(), 1)));
            timeline.play();
        }
        this.isPlaying = true;
    }

    public void stopMusic(int fadeOutSeconds) {
        if (!this.isPlaying) {
            return;
        }

        DelayRunner.startSleeper(fadeOutSeconds).setOnSucceeded(event -> this.mediaPlayer.stop());

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(fadeOutSeconds),
                        new KeyValue(this.mediaPlayer.volumeProperty(), 0)));
        timeline.play();
    }

    public void muteMusic(int fadeOutSeconds) {
        if (this.isMuted) {
            return;
        }

        this.lastVolume = this.mediaPlayer.getVolume();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(fadeOutSeconds),
                        new KeyValue(this.mediaPlayer.volumeProperty(), 0)));
        timeline.play();

        this.isMuted = true;
    }

    public void unMuteMusic(int fadeInSeconds) {
        if (!this.isMuted) {
            return;
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(fadeInSeconds),
                        new KeyValue(this.mediaPlayer.volumeProperty(), this.lastVolume)));
        timeline.play();

        this.isMuted = false;
    }
}

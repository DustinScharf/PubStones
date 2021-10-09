package org.example.pubstones.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private MediaPlayer mediaPlayer;

    private final Map<String, Media> music;

    public MusicManager() {
        this.music = new HashMap<>();
    }

    public void setMusic(String mp3FilePath) {
        Media musicMedia = this.music.computeIfAbsent(mp3FilePath, mp3FilePathTemp -> {
            URL url = getClass().getResource(mp3FilePathTemp);
            if (url == null) {
                throw new NullPointerException(); // TODO write own exception
            }

            String path = url.toString();
            return new Media(path);
        });
        this.mediaPlayer = new MediaPlayer(musicMedia);
    }

    public void playMusic(int fadeInSeconds) {
        this.mediaPlayer.setVolume(0);
        this.mediaPlayer.play();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(fadeInSeconds),
                        new KeyValue(this.mediaPlayer.volumeProperty(), 1)));
        timeline.play();
    }

    public void stopMusic(int fadeInSeconds) {
        // TODO extract basic sleeper structure to other class
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> this.mediaPlayer.stop());
        new Thread(sleeper).start();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(fadeInSeconds),
                        new KeyValue(this.mediaPlayer.volumeProperty(), 0)));
        timeline.play();
    }
}

package org.example.pubstones.gui.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private MediaPlayer mediaPlayer;

    private final Map<String, Media> music;

    private double volume;

    public MusicManager() {
        this.music = new HashMap<>();
        this.volume = 0;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setVolume(this.volume);
        }
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
        if (this.mediaPlayer != null && this.mediaPlayer.getMedia().equals(musicMedia)) {
            return;
        }

        this.mediaPlayer = new MediaPlayer(musicMedia);
        this.mediaPlayer.setVolume(this.volume);
        this.mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    public void playMusic() {
        if (this.mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            return;
        }

        this.mediaPlayer.play();

//        int fadeInSeconds = 3;
//        if (fadeInSeconds > 0 && !this.isMuted) {
//            this.mediaPlayer.setVolume(0);
//            this.mediaPlayer.play();
//            Timeline timeline = new Timeline(
//                    new KeyFrame(Duration.seconds(fadeInSeconds),
//                            new KeyValue(this.mediaPlayer.volumeProperty(), 1)));
//            timeline.play();
//        }
//        this.isPlaying = true;
    }

    public void stopMusic() {
        this.mediaPlayer.stop();
    }
}

package org.example.pubstones.gui.util;

import javafx.concurrent.Task;

@Deprecated
public class DelayRunner {
    public static Task<Void> startSleeper(int seconds) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(seconds * 1000L);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        new Thread(sleeper).start();

        return sleeper;
    }
}

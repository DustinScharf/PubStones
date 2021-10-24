package org.example.pubstones.gui.util;

import javafx.concurrent.Task;

/**
 * This class can create a sleeper(-task)
 * and this sleeper can run a methode,
 * when the sleep time is finished
 */
@Deprecated
public class DelayRunner {
    public static Task<Void> startSleeper(int seconds) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(seconds * 1000L);
                } catch (InterruptedException ignored) {
                    // todo handle
                }
                return null;
            }
        };
        new Thread(sleeper).start();

        return sleeper;
    }
}

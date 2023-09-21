package com.example.pokejournal.application.fetchers;

import android.os.Handler;
import android.os.HandlerThread;
public class Fetcher {
    public static void async(Runnable runnable){
        long currentTimeMillis = System.currentTimeMillis();

        String threadName = String.format("Thread_%s", currentTimeMillis);
        HandlerThread thread = new HandlerThread(threadName);
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                runnable.run();
            }finally {
                thread.quit();
            }
        });
    }
}

package com.masterwok.simplevlcplayer.helpers;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;


/**
 * This helper class provides threading convenience methods.
 */
public class ThreadHelper {

    private ThreadHelper() {
    }

    /**
     * Execute the provided runnable on a background thread.
     *
     * @param runnable The runnable instance.
     */
    public static void onBackground(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }

    /**
     * Execute the provided runnable on a main thread.
     *
     * @param runnable The runnable instance.
     */
    public static void onMain(Runnable runnable) {
        Thread mainThread = Looper.getMainLooper().getThread();

        // Already on main thread, execute and return.
        if (Thread.currentThread() == mainThread) {
            runnable.run();
            return;
        }

        // Push work to main thread and execute.
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}

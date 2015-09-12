package com.esc861.screenshotlistener;

import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * A class that handles listening for screenshots taken by the user.
 * Once listening has begun, the abstract method, onScreenshotDeteceted, will be called.
 */
public abstract class ScreenshotListener {
    ArrayList<FileObserver> observers;
    Handler handler;

    /**
     * Constructor
     */
    public ScreenshotListener() {
        init();
    }

    private void init() {
        handler = new Handler();
        observers = new ArrayList<FileObserver>();
        File pictures = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES);

        File screenshots = new File(pictures, "Screenshots");

        File sdCard = Environment.getExternalStorageDirectory();
        File screenCapture = new File(sdCard, "ScreenCapture");
        Log.i("ScreenshotListener", screenshots.getPath());

        String[] filePaths = {screenshots.getPath(), screenCapture.getPath()};

        for (final String filePath : filePaths) {
            observers.add(new FileObserver(filePath, FileObserver.CREATE) {
                @Override
                public void onEvent(int event, final String path) {
                    if ((event & FileObserver.CREATE) == FileObserver.CREATE) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onScreenshotDetected(filePath + "/" + path);
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * Start listening for screenshots.
     * Monitors the screenshot directories
     */
    public void startListening() {
        for (FileObserver observer : observers) {
            observer.startWatching();
        }
    }

    /**
     * Stop listening for screenshots.
     */
    public void stopListening() {
        for (FileObserver observer : observers) {
            observer.stopWatching();
        }
    }

    /**
     * Abstract method that will be called when a screenshot is detected
     *
     * @param path The path to the new screenshot file
     */
    public abstract void onScreenshotDetected(String path);
}
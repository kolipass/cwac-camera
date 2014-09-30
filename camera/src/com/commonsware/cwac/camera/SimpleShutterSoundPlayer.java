package com.commonsware.cwac.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaPlayer;

/**
 * Created by kolipass on 30.09.14.
 */
public class SimpleShutterSoundPlayer extends AbstractCustomShutterSoundPlayer {
    protected MediaPlayer media;
    protected int resId;

    public SimpleShutterSoundPlayer(Activity activity, Camera camera, int resId) {
        super(activity, camera);
        this.resId = resId;
        media = MediaPlayer.create(activity, resId);
    }

    @Override
    public void onShutter() {
        media.start();
    }
}

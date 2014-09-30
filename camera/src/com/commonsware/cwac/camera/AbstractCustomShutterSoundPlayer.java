package com.commonsware.cwac.camera;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;

/**
 * Created by kolipass on 30.09.14.
 */
public abstract class AbstractCustomShutterSoundPlayer implements Camera.ShutterCallback, Camera.PictureCallback {

    protected Camera camera;
    protected int currentVolume;
    protected boolean isVolumeChanged;
    protected AudioManager audio;

    protected AbstractCustomShutterSoundPlayer(Activity activity, Camera camera) {
        audio = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        this.camera = camera;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            camera.enableShutterSound(false);
        } else {
            currentVolume = audio.getStreamVolume(AudioManager.STREAM_SYSTEM);
            audio.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

            isVolumeChanged = true;
        }
    }


    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        if (isVolumeChanged) {
            audio.setStreamVolume(AudioManager.STREAM_SYSTEM, currentVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        }
    }
}
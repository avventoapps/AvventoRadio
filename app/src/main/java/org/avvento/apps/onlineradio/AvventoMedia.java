package org.avvento.apps.onlineradio;

import android.net.Uri;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class AvventoMedia {
    private static AvventoMedia instance;
    private SimpleExoPlayer player;
    private static String URL = "http://radio.avventohome.org/radio/8000/avvento.mp3";
    private AvventoMedia(MainActivity mainActivity) {
        player = new SimpleExoPlayer.Builder(mainActivity).build();
        player.setMediaItem( MediaItem.fromUri(Uri.parse(URL)));
        player.prepare();
    }

    public static AvventoMedia getInstance(MainActivity mainActivity) {
        if(instance == null) {
            synchronized (AvventoMedia.class) {
                instance = new AvventoMedia(mainActivity);
            }
        }
        return instance;
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void play() {
        player.setPlayWhenReady(true);
    }

    public void pause() {
        player.setPlayWhenReady(false);
    }

}

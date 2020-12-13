package org.avvento.apps.onlineradio;

import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import okhttp3.OkHttpClient;

public class AvventoMedia {
    private static AvventoMedia instance;
    private SimpleExoPlayer avventoMedia;
    private static String URL = "http://radio.avventohome.org/radio/8000/avvento.mp3";
    private AvventoMedia(MainActivity mainActivity) {
        avventoMedia = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(mainActivity.getApplicationContext()), new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
        avventoMedia.addListener(mainActivity);
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(URL), new OkHttpDataSourceFactory(new OkHttpClient(), "AvventoMedia", null), new DefaultExtractorsFactory(), null, null);
        avventoMedia.prepare(mediaSource);
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
        return avventoMedia.getPlaybackState() == Player.STATE_READY && avventoMedia.getPlayWhenReady();
    }

    public void play() {
        avventoMedia.setPlayWhenReady(true);
    }

    public void pause() {
        avventoMedia.setPlayWhenReady(false);
    }

}

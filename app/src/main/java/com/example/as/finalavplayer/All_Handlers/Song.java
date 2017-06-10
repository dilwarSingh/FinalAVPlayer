package com.example.as.finalavplayer.All_Handlers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.as.finalavplayer.R;

/**
 * Created by sam on 25-Apr-16.
 */

public class Song {

    private long id;
    private String title;
    private Bitmap icon;
    private String artist;
    private long duration;


    public Song(long id, String title, Bitmap icon, String artist, long duration) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.artist = artist;
        this.duration = duration;
    }

    public Song(long id, String title, String artist, long duration) {
        this.id = id;
        this.title = title;

        this.artist = artist;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getArtist() {
        return artist;
    }

}

package com.example.tuyenga.mp3android;

/**
 * Created by matur on 30/09/2017.
 */

public class Song {
    String title;
    String path;

    public Song(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

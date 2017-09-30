package com.example.tuyenga.mp3android.Models;

/**
 * Created by tuyenga on 30/09/2017.
 */

public class SongModel {
    public String title;
    public String path;

    public SongModel(String title, String path) {
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

package com.example.tuyenga.mp3android.Utility;

import android.os.Environment;

import com.example.tuyenga.mp3android.Models.SongModel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by tuyenga on 01/10/2017.
 */

public class CommonActions {
    public CommonActions() {
    }
    public static ArrayList<SongModel> getPlayList(String download) {
        ArrayList<SongModel> songsList = new ArrayList<>();
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        File home = new File(sdcard + File.separator + "Download");
        try {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                String songTitle = file.getName().substring(0, (file.getName().length() - 4));
                String songPath = file.getPath();
                SongModel song = new SongModel(songTitle, songPath);

                songsList.add(song);
            }
            return songsList;
        }
        catch (Exception e) {
            return songsList;
        }
    }

    /**
     * Class to filter files which are having .mp3 extension
     */
//you can choose the filter for me i put .mp3
    static class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}

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
    public static ArrayList<SongModel> getPlayList(String musicFolder) {
        ArrayList<SongModel> songsList = new ArrayList<>();
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        File home = new File(sdcard + File.separator + musicFolder);
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

    public static ArrayList<String> getRootFolderCountMusic() {
        ArrayList<String> list = new ArrayList<>();
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        File home = new File(sdcard);
        try {
            for (File file : home.listFiles()) {
               int count = file.listFiles(new FileExtensionFilter()).length;
                list.add(String.format("%s (%s)", file.getName(), count));
            }
            return list;
        }
        catch (Exception e) {
            return list;
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

    public static int findIndexStartWith(String start, ArrayList<String> list) {
        for (String s : list) {
            if (s.startsWith(start)) {
                return list.indexOf(s);
            }
        }
        return -1;
    }
}

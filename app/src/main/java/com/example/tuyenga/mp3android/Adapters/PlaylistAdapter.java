package com.example.tuyenga.mp3android.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuyenga.mp3android.R;
import com.example.tuyenga.mp3android.Models.SongModel;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter{
    private ArrayList<SongModel> songList;
    private Activity context;

    public PlaylistAdapter( Activity context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    public ArrayList<SongModel> getData() {
        return songList;
    }

    public void setData(ArrayList<SongModel> songList) {
        this.songList = songList;
    }

    @Override
    public SongModel getItem(int i) {
        return songList.get(i);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.playlist_item, null);
        TextView tvTitle = view.findViewById(R.id.tv_item_title);
        tvTitle.setText(getItem(i).getTitle());
        return view;
    }
}

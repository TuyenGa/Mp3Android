package com.example.tuyenga.mp3android.Controllers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuyenga.mp3android.Models.SongModel;
import com.example.tuyenga.mp3android.R;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter{
    private ArrayList<SongModel> songList = new ArrayList<>();
    private Activity context;

    public PlaylistAdapter(ArrayList<SongModel> songList, Activity context) {
        this.songList = songList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return songList.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.playlist_item,viewGroup,false);
       // view = inflater.inflate(R.layout.playlist_item, null);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tv_item_title);
        tvTitle.setText(songList.get(i).getTitle());
        return rowView;
    }
}

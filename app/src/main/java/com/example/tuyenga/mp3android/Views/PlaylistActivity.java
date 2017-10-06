package com.example.tuyenga.mp3android.Views;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tuyenga.mp3android.Controllers.PlaylistAdapter;
import com.example.tuyenga.mp3android.Models.SongModel;
import com.example.tuyenga.mp3android.R;
import com.example.tuyenga.mp3android.Utility.CommonActions;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ArrayList<SongModel> songList;
    Toolbar toolbar;
    PlaylistAdapter playlistAdapter;
    EditText musicFolder;
    ListView playlist;
    Button btnReloadMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        connectView();
        songList = CommonActions.getPlayList("sdcard/Download");
        playlistAdapter = new PlaylistAdapter(songList, this);
        playlist.setAdapter(playlistAdapter);
        toolbar.setTitle("Playlist");
        toolbar.setTitleTextColor(Color.WHITE);




    }

    private void connectView(){
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        playlist = (ListView) findViewById(R.id.playlist);
        playlist.setOnItemClickListener(this);
        playlist.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent = new Intent();
        intent.putExtra("id",position);

        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.print(playlistAdapter.getItem(position).getTitle());
        System.out.println(" -- long clicked at :"+position);
        playlistAdapter.notifyDataSetChanged();
        return false;
    }

}

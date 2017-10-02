package com.example.tuyenga.mp3android.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tuyenga.mp3android.Controllers.PlaylistAdapter;
import com.example.tuyenga.mp3android.R;
import com.example.tuyenga.mp3android.Song;
import com.example.tuyenga.mp3android.Utility.CommonActions;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    private ArrayList<Song> songList;
    PlaylistAdapter playlistAdapter;
    EditText musicFolder;
    ListView playlist;
    Button btnReloadMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        connectView();
        songList = CommonActions.getPlayList(musicFolder.getText().toString());
        playlistAdapter = new PlaylistAdapter(songList, this);
        playlist.setAdapter(playlistAdapter);
    }

    private void connectView(){
        musicFolder = (EditText) findViewById(R.id.musicFolder);
        btnReloadMusic = (Button) findViewById(R.id.btnReloadMusic);
        btnReloadMusic.setOnClickListener(this);
        playlist = (ListView) findViewById(R.id.playlist);
        playlist.setOnItemClickListener(this);
        playlist.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.print(playlistAdapter.getItem(position).getTitle());
        System.out.println(" -- clicked at :"+position);
        playlistAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.print(playlistAdapter.getItem(position).getTitle());
        System.out.println(" -- long clicked at :"+position);
        playlistAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnReloadMusic) {
            System.out.println(musicFolder.getText().toString());
            songList = CommonActions.getPlayList(musicFolder.getText().toString());
            System.out.println(songList.size()+ "new size::::");
            playlistAdapter.notifyDataSetChanged();
        }
    }
}

package com.example.tuyenga.mp3android.Activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tuyenga.mp3android.Adapters.PlaylistAdapter;
import com.example.tuyenga.mp3android.R;
import com.example.tuyenga.mp3android.Models.SongModel;
import com.example.tuyenga.mp3android.Utility.CommonActions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PlaylistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    PlaylistAdapter playlistAdapter;
    ListView playlist;
    TextView noneMusic;
    ArrayList<String> ListMusicFolder;
    Spinner spinnerListMusicFolder;
    int selectedPosition;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        connectView();
        playlistAdapter = new PlaylistAdapter(context);
        displayPlaylist("Download");
    }

    private void connectView() {
        playlist = (ListView) findViewById(R.id.playlist);
        registerForContextMenu(playlist);
        playlist.setOnItemClickListener(this);
        playlist.setOnItemLongClickListener(this);
        noneMusic = (TextView) findViewById(R.id.noneMusic);
        spinnerListMusicFolder = (Spinner) findViewById(R.id.spinnerMusicFolders);
        ListMusicFolder = CommonActions.getRootFolderCountMusic();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ListMusicFolder);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerListMusicFolder.setAdapter(adapter);
        spinnerListMusicFolder.setSelection(CommonActions.findIndexStartWith("Download", ListMusicFolder));
        spinnerListMusicFolder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String folder = spinnerListMusicFolder.getSelectedItem().toString().split(" ")[0];
                displayPlaylist(folder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void displayPlaylist(String folder) {
        ArrayList<SongModel> songList = CommonActions.getPlayList(folder);
        if (songList.size() == 0) {
            noneMusic.setVisibility(View.VISIBLE);
            playlist.setVisibility(View.INVISIBLE);
            return;
        }
        playlistAdapter.setData(songList);
        playlist.setAdapter(playlistAdapter);
        noneMusic.setVisibility(View.INVISIBLE);
        playlist.setVisibility(View.VISIBLE);
        playlistAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.print(playlistAdapter.getItem(position).getPath());
        System.out.println(" -- clicked at :" + position);
        playlistAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.print(playlistAdapter.getItem(position).getPath());
        selectedPosition = position;

        playlistAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnDelete: {
                System.out.println("dang delete item: "+selectedPosition);
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu1, menu);

    }
}

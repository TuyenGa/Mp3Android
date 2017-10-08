package com.example.tuyenga.mp3android.Views;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tuyenga.mp3android.Models.SongModel;
import com.example.tuyenga.mp3android.R;
import com.example.tuyenga.mp3android.Utility.CommonActions;
import com.example.tuyenga.mp3android.Utility.Util;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {
    public final int SELECT_SONG_REQUEST = 0;
    private Toolbar toolbar;
    private SongModel songModel;

    public static ArrayList<SongModel> arrSongs = new ArrayList<>();
    private int seekForwardTime = 5000;
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex = 0;

    // media player
    private MediaPlayer mediaPlayer;

    //Handler
    private ImageView btnPlay;
    private ImageView btnForward;
    private ImageView btnBackward;
    private ImageView btnNext;
    private ImageView btnPrevious;

    private SeekBar songProgressBar;
    private TextView lblCurrentDuration;
    private TextView lblTotalDuration;

    private Handler handler = new Handler();


    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set toolbar
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("MP3 Player");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_listen);
        setSupportActionBar(toolbar);

        // get all song
        arrSongs = CommonActions.getPlayList("Download");

        // media player
        mediaPlayer = new MediaPlayer();

        // find views
        btnPlay = (ImageView) findViewById(R.id.btn_play);
        btnNext = (ImageView) findViewById(R.id.btn_nextSong);
        btnBackward = (ImageView) findViewById(R.id.btn_backward);
        btnForward = (ImageView) findViewById(R.id.btn_forwards);
        btnPrevious = (ImageView) findViewById(R.id.btn_previous);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        lblCurrentDuration = (TextView) findViewById(R.id.lblCurrentDuration);
        lblTotalDuration = (TextView) findViewById(R.id.lblTotalDuration);

        btnPlay.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        // progress song bar change

        songProgressBar.setOnSeekBarChangeListener(this);
        mediaPlayer.setOnCompletionListener(this);
        playSong(currentSongIndex);


    }




    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btn_play)
        {
            /**
             * play button click
             */

            if(mediaPlayer.isPlaying())
            {
                if(mediaPlayer!=null)
                {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.mipmap.play_icon);
                }
            }else {
                if (mediaPlayer != null)
                {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.mipmap.ic_pause);
                }
            }
        }

        if (id == R.id.btn_forwards)
        {
            int currentPosition = mediaPlayer.getCurrentPosition();

            if (currentPosition + seekForwardTime  <= mediaPlayer.getDuration())
            {
                mediaPlayer.seekTo(currentPosition + seekForwardTime);
            }else {
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        }
        if (id == R.id.btn_backward)
        {
            int currentPosition = mediaPlayer.getCurrentPosition();

            if (currentPosition - seekBackwardTime >= 0)
            {
                mediaPlayer.seekTo(currentPosition - seekBackwardTime);
            }
            else {
                mediaPlayer.seekTo(0);
            }
        }
        if (id == R.id.btn_nextSong)
        {
            if (currentSongIndex <(arrSongs.size() -1))
            {
                playSong(currentSongIndex +1);
                currentSongIndex = currentSongIndex +1;
            }
            else {
                playSong(0);
                currentSongIndex = 0;
            }
            buildNotification();
        }
        if (id == R.id.btn_previous)
        {
            if (currentSongIndex > 0)
            {
                playSong(currentSongIndex - 1);
                currentSongIndex = currentSongIndex -1;
            }
            else {
                playSong(arrSongs.size()-1);
                currentSongIndex = arrSongs.size() -1;
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mediaPlayer.getDuration();
        int currentPosition = Util.progressToTimer(seekBar.getProgress(),totalDuration);

        mediaPlayer.seekTo(currentPosition);
        updateProgressBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.header_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.list_songs:
                Intent intent = new Intent(this, PlaylistActivity.class);
                startActivityForResult(intent,SELECT_SONG_REQUEST);
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_SONG_REQUEST && resultCode == RESULT_OK)
        {
            currentSongIndex = data.getExtras().getInt("id");
            playSong(currentSongIndex);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (currentSongIndex < (arrSongs.size() -1))
        {
            playSong(currentSongIndex + 1);
            currentSongIndex = currentSongIndex +1;
        }
        else {
            playSong(0);
            currentSongIndex = 0;
        }
    }
    public void playSong(int songIndex)
    {
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(arrSongs.get(songIndex).getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            // update title toolbar
            toolbar.setTitle(arrSongs.get(songIndex).getTitle());

            //change button play to pause

            btnPlay.setImageResource(R.mipmap.ic_pause);

            //set progress bar value

            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            updateProgressBar();

            //build notification

            buildNotification();


        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }catch (IllegalStateException ie)
        {
            ie.printStackTrace();
        }catch(IOException Oe)
        {
            Oe.printStackTrace();
        }
    }
    public void updateProgressBar()
    {
        handler.postDelayed(mUpdateTimeTask,100);
    }
    public Runnable mUpdateTimeTask = new Runnable() {


        @Override
        public void run() {
            try {
                long totalDuration = mediaPlayer.getDuration();
                long currentDuration = mediaPlayer.getCurrentPosition();

                // displaying total duration times

                lblTotalDuration.setText(""+ Util.milliSecondsToTimer(totalDuration));
                // set displaying time complete

                lblCurrentDuration.setText("" + Util.milliSecondsToTimer(currentDuration));

                int progress = (int) (Util.getProgressPercentage(currentDuration, totalDuration));


                songProgressBar.setProgress(progress);

                // Running this thread after 100 milliseconds

                handler.postDelayed(this,100);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };

    public void buildNotification()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(),1,intent,0);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.music_icon)
                .setContentTitle("Media Artist")
                .setContentText(arrSongs.get(currentSongIndex).getTitle())
                .setDeleteIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    @Override
    protected void onPause() {
        mediaPlayer.pause();
        btnPlay.setImageResource(R.mipmap.play_icon);
        super.onPause();
    }

    @Override
    protected void onResume() {
        mediaPlayer.start();
        btnPlay.setImageResource(R.mipmap.ic_pause);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }
}

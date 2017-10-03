package com.example.tuyenga.mp3android.Activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tuyenga.mp3android.R;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t_sleep = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {

                } finally {
                    Intent listIntent = new Intent(context, PlaylistActivity.class);
                    startActivity(listIntent);
                }
            }
        };
        t_sleep.start();
    }

    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause() {
        super.onPause();
        finish();
    }
}

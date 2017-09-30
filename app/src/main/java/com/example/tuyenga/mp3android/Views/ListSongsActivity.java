package com.example.tuyenga.mp3android.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tuyenga.mp3android.R;

/*
    Ban làm phần nay đơn giản thôi cũng được chỉ có tên vs path thôi.
    Cái này cần show tên ra và khi click vào item sẽ nhảy sang detail và play.
 */
public class ListSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);
    }
}

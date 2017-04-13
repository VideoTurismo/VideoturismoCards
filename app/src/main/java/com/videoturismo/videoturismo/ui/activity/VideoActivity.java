package com.videoturismo.videoturismo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;

import com.videoturismo.videoturismo.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);


        VideoView vv = (VideoView)findViewById(R.id.videoViewPelicula);


        String path = getIntent().getStringExtra("URL");
        Log.d("Video",path);
        vv.setVideoPath(path);
        vv.start();


    }
}

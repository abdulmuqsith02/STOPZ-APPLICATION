package com.example.xyz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends AppCompatActivity {

    Button b1;
    VideoView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        v=findViewById(R.id.videoView);
        b1=findViewById(R.id.close);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(video.this,InstalledApps.class);
                startActivity(i);
            }
        });
        videoplay();
    }
    public void videoplay(){
        String videopath="android.resource://"+getPackageName()+"/"+R.raw.demo;
        Uri uri=Uri.parse(videopath);
        v.setVideoURI(uri);

        MediaController mediaController=new MediaController(this);
        //v.setMediaController(mediaController);
       // mediaController.setAnchorView(v);
        v.start();
    }
}
package com.lijunyan.tjut.text_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by LJY on 2015/10/3.
 */
public class AtyVideoViewer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vv =new VideoView(this);
        vv.setMediaController(new MediaController(this));
        setContentView(vv);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if (path!=null){
            vv.setVideoPath(path);
        }else{
            finish();
        }
    }

    private VideoView vv;

    public static final String EXTRA_PATH = "path";
}

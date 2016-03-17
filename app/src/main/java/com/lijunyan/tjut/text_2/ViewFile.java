package com.lijunyan.tjut.text_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

/**
 * Created by LJY on 2015/10/15.
 */
public class ViewFile extends Activity {

    @Override
    protected void onCreate(Bundle saveInatanceState){
        super.onCreate(saveInatanceState);
        setContentView(R.layout.viewfile);
        String s = getIntent().getStringExtra("name");
        Log.v("1", "ok");
        Intent intent = new Intent();
        Log.v("2", "ok");
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Log.v("3", "ok");
        File file = new File("/storage/emulated/0/XueBa_Note_Pic/"+s+".jpg");
        Log.v("4", "ok");
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        Log.v("5", "ok");
        startActivity(intent);
    }
}

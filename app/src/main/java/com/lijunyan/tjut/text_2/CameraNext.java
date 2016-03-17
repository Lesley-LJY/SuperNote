package com.lijunyan.tjut.text_2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraNext extends Activity {
    private Button btn;
    private ImageView img;
    private Button open;


    Uri originalUri;
    Bitmap bm = null;
    Handler h = new Handler();
    private static final int CAMERA_WITH_DATA = 3023;
    private static final int PHOTO_PICKED_WITH_DATA = 3021;
//
//	private static final File PHOTO_DIR = new File(
//			Environment.getExternalStorageDirectory() + "/XueBa_Note_Pic");

    private static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/XueBa_Note_Pic");
    String s = Environment.getExternalStorageState()+"/XueBa_Note_Pic";
    private String picName = "";
    String imgName = "";
    private String userSelectPath = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_next);
        init();
        btn.setOnClickListener(new btn_OnClick());
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CameraNext.this,ListAllFileActivity.class);
                startActivity(i);

            }
        });
    }


    private class btn_OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String status = Environment.getExternalStorageState();

            if (status.equals(Environment.MEDIA_MOUNTED)) {
                doTakePhoto();
            } else {
                Toast.makeText(CameraNext.this, "SD卡出错", Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case PHOTO_PICKED_WITH_DATA: {
                try {

                    originalUri = data.getData();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                String[] proj = { MediaStore.Images.Media.DATA };

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                userSelectPath = cursor.getString(column_index);
                if (userSelectPath.indexOf("/") != -1) {
                    imgName = userSelectPath.substring(userSelectPath
                            .lastIndexOf("/") + 1);
                }
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    options.inJustDecodeBounds = true;
                    bm = BitmapFactory.decodeFile(userSelectPath, options);
                    options.inJustDecodeBounds = false;

                    int be = (int) (options.outHeight / (float) 200);
                    if (be <= 0)
                        be = 1;
                    options.inSampleSize = be;

                    bm = BitmapFactory.decodeFile(userSelectPath, options);
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                    System.out.println("PHOTO_PICKED_WITH_DATA:e=" + e.getMessage());
                }
                break;
            }

            case CAMERA_WITH_DATA: {
                try {
                    userSelectPath = PHOTO_DIR + "/" + picName;
                    imgName = picName;
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    options.inJustDecodeBounds = true;
                    bm = BitmapFactory.decodeFile(userSelectPath, options);
                    options.inJustDecodeBounds = false;

                    int be = (int) (options.outHeight / (float) 200);
                    if (be <= 0)
                        be = 1;
                    options.inSampleSize = be;

                    bm = BitmapFactory.decodeFile(userSelectPath, options);
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            img.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                    System.out.println("CAMERA_WITH_DATA:e=" + e.getMessage());
                }
                break;
            }
        }
    }


    protected void doTakePhoto() {
        try {
            PHOTO_DIR.mkdirs();
            Intent imageCaptureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            picName = getPhotoFileName();
            picName = picName.replace("-", "");
            picName = picName.replace(":", "");
            File out = new File(PHOTO_DIR, picName);
            Uri uri = Uri.fromFile(out);
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            imageCaptureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(imageCaptureIntent, CAMERA_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "doTakePhoto��e=" + e, Toast.LENGTH_LONG)
                    .show();
        }
    }


    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        return dateFormat.format(date) + ".jpg";
    }


    private void init() {
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
        open = (Button)findViewById(R.id.open);
    }
}
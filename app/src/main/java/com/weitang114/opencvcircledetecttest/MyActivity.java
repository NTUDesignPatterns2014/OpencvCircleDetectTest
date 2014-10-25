package com.weitang114.opencvcircledetecttest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyActivity extends Activity {


    private ImageView mImgViewOri;
    private ImageView mImgViewNew;

    public static final String TAG = "MyActivity";

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    // Load native library after(!) OpenCV initialization

                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mImgViewOri = (ImageView) findViewById(R.id.image_ori);
        mImgViewNew = (ImageView) findViewById(R.id.image_new);


    }
    @Override
    public void onResume()
    {
        super.onResume();
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
            Log.e(TAG, "init failed");
        }

        mImgViewOri.setImageDrawable(getResources().getDrawable(R.drawable.coins));
        Bitmap bitmapOri = drawableToBitmap(getResources().getDrawable(R.drawable.coins));
        Bitmap bitmapNew = bitmapOri.copy(bitmapOri.getConfig(), true);
        Mat mat = new Mat();
        Utils.bitmapToMat(bitmapOri, mat);
        NativeCircleDetector detector = new NativeCircleDetector();
        detector.detect(mat);

        Utils.matToBitmap(mat, bitmapNew);
        mImgViewNew.setImageBitmap(bitmapNew);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        return bitmap;
    }

    static {
        System.loadLibrary("circle_detection");
    }
}

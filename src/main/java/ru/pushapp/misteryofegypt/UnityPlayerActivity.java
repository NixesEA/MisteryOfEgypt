package ru.pushapp.misteryofegypt;

import com.unity3d.player.*;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class UnityPlayerActivity extends FragmentActivity {

    // Setup activity layout
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        Log.i("life","onCreateBase");

    }

    public void setTextView(String msg) {
        Log.i("TEST", "setTextView: " + msg);
    }

    // Quit Unity
    @Override protected void onDestroy ()
    {
        Log.i("life","onDestrBase");
        super.onDestroy();
    }

    // Pause Unity
    @Override protected void onPause()
    {
        Log.i("life","onPauseBase");
        super.onPause();
    }

    // Resume Unity
    @Override protected void onResume()
    {
        Log.i("life","onResumeBase");
        super.onResume();
    }

    @Override protected void onStart()
    {
        Log.i("life","onStartBase");
        super.onStart();
    }

    @Override protected void onStop() {
        Log.i("life", "onStopBase");
        super.onStop();
    }
}

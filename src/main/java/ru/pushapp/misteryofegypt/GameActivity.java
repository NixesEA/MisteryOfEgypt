package ru.pushapp.misteryofegypt;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

public class GameActivity extends Activity {

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mUnityPlayer = new UnityPlayer(this);
        mUnityPlayer.requestFocus();

        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);

        View playerView = mUnityPlayer.getView();
        FrameLayout layout = findViewById(R.id.unity_frame);
        layout.addView(playerView,0);
    }


    public void setTextView(String msg) {
//        Log.i("TEST", "setTextView: " + msg);
//        testMessage = "Message = " + msg;
//        textView.setText(testMessage);
    }

    public void updateLife(String msg) {
        int life = Integer.valueOf(msg);
        Log.i("test update", "file: " + msg);
        Log.i("test update", "file: " + life);

//        testMessage = "Message = " + msg;
//        textView.setText(testMessage);
    }
    public void updateMoney(String msg) {
        Log.i("test update", "money: " + msg);
//        testMessage = "Message = " + msg;
//        textView.setText(testMessage);
    }


    @Override protected void onNewIntent(Intent intent)
    {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override protected void onDestroy ()
    {
        Log.i("life","onDestr");
        mUnityPlayer.destroy();
        super.onDestroy();
    }

    // Pause Unity
    @Override protected void onPause()
    {
//        mUnityPlayer.pause();
        Log.i("life","onPause");
        super.onPause();
    }

    // Resume Unity
    @Override protected void onResume()
    {
        Log.i("life","onResume");
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override protected void onStart()
    {
        Log.i("life","onStart");
        super.onStart();
        mUnityPlayer.start();
    }

    @Override protected void onStop()
    {
        Log.i("life","onStop");
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override public void onLowMemory()
    {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }
}

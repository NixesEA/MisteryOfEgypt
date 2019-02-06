package ru.pushapp.misteryofegypt.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;

import ru.pushapp.misteryofegypt.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, PauseFragment.OnPauseListener, ResultFragment.OnResultListener{

    boolean showPause = false;
    int countMoney = 0;
    int externalLife = 0;

    ImageButton pause;
    TextView currentScore;
    TextView currentCountOfLife;
    FrameLayout pauseLayout;

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pause = findViewById(R.id.btn_pause);
        pause.setOnClickListener(this);

        currentScore = findViewById(R.id.coins_text);
        currentCountOfLife = findViewById(R.id.life_text);

        pauseLayout = findViewById(R.id.pause_frame);

        mUnityPlayer = new UnityPlayer(this);
        mUnityPlayer.requestFocus();

        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);

        View playerView = mUnityPlayer.getView();
        FrameLayout layout = findViewById(R.id.unity_frame);
        layout.addView(playerView, 0);
    }

    private void checkExternalLife() {
        SharedPreferences sharedPreferences = getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        externalLife = sharedPreferences.getInt("life", 0);

        mUnityPlayer.UnitySendMessage("Player", "setExternalLife", String.valueOf(externalLife));
    }

    public void updateExternalLife(String msg) {
        externalLife = Integer.valueOf(msg);
    }

    public void updateMoney(String msg) {
        countMoney = Integer.valueOf(msg);
        currentScore.setText(msg);
    }

    public void updateLife(String msg) {

        int value = Integer.valueOf(msg);
        currentCountOfLife.setText(msg);
        if (value < 1) {
            mUnityPlayer.pause();

            ResultFragment resultFragment = new ResultFragment();
            resultFragment.setOnResultListener(this);

            Bundle bundle = new Bundle();
            bundle.putInt("countMoney", countMoney);
            resultFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.pause_frame, resultFragment);
            fragmentTransaction.commitNow();

            showPause = true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pause:
                if (!showPause) {
                    mUnityPlayer.pause();

                    PauseFragment pauseFragment= new PauseFragment();
                    pauseFragment.setOnPauseListener(this);

                    Bundle bundle = new Bundle();
                    bundle.putInt("externalLife", externalLife);
                    pauseFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.pause_frame, pauseFragment);
                    fragmentTransaction.commitNow();

                    showPause = true;
                } else {
                    mUnityPlayer.resume();
                    pauseLayout.removeAllViews();

                    showPause = false;
                }
                break;
        }
    }

    @Override
    public void continueGame() {
        showPause = false;
        pauseLayout.removeAllViews();
        mUnityPlayer.resume();
    }

    @Override
    public void playAgain() {
        showPause = false;
        pauseLayout.removeAllViews();
        mUnityPlayer.UnitySendMessage("Player", "setMoney", "0");
        mUnityPlayer.UnitySendMessage("Player", "restartGame", "");
        mUnityPlayer.resume();
    }






    @Override
    protected void onNewIntent(Intent intent) {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        Log.i("life", "onDestr");
        mUnityPlayer.destroy();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    protected void onPause() {
//        mUnityPlayer.pause();
        Log.i("life", "onPause");
        super.onPause();
    }

    // Resume Unity
    @Override
    protected void onResume() {
        Log.i("life", "onResume");
        checkExternalLife();
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override
    protected void onStart() {
        Log.i("life", "onStart");
        super.onStart();
        mUnityPlayer.start();
    }

    @Override
    protected void onStop() {
        Log.i("life", "onStop");
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }
}

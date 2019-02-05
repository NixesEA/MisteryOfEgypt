package ru.pushapp.misteryofegypt.start;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import ru.pushapp.misteryofegypt.R;

public class UnityPlayerActivity extends FragmentActivity {

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

    }


    @Override
    protected void onResume() {
        Log.i("TESTAPP","onResume = ");
        super.onResume();
    }

    public void setTextView(String msg) {
        return;
//        Log.i("TEST", "setTextView: " + msg);
    }
}

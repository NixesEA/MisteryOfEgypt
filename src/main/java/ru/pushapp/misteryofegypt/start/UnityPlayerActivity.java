package ru.pushapp.misteryofegypt.start;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import ru.pushapp.misteryofegypt.R;

public class UnityPlayerActivity extends FragmentActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    TextView coinsTV;
    TextView externalLifeTV;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        coinsTV = findViewById(R.id.count_coins);
        externalLifeTV = findViewById(R.id.count_life);
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        int userBalance = sharedPreferences.getInt("money", 0);
        int externalLife = sharedPreferences.getInt("life", 0);

        coinsTV.setText(String.valueOf(userBalance));
        externalLifeTV.setText(String.valueOf(externalLife));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        int userBalance = sharedPreferences.getInt("money", 0);
        int externalLife = sharedPreferences.getInt("life", 0);

        coinsTV.setText(String.valueOf(userBalance));
        externalLifeTV.setText(String.valueOf(externalLife));
    }
}

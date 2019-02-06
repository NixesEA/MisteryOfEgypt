package ru.pushapp.misteryofegypt.game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ru.pushapp.misteryofegypt.R;

public class PauseFragment extends Fragment implements View.OnClickListener {

    ImageButton continue_btn;
    ImageButton exit_btn;

    OnPauseListener callback;

    int countLastExternalLife = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause, container, false);

        continue_btn = view.findViewById(R.id.pause_continue);
        continue_btn.setOnClickListener(this);

        exit_btn = view.findViewById(R.id.pause_exit_menu);
        exit_btn.setOnClickListener(this);

        countLastExternalLife = getArguments().getInt("externalLife");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("life", countLastExternalLife);
        editor.commit();


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pause_continue:
                callback.continueGame();
                break;
            case R.id.pause_exit_menu:
                getActivity().finish();
                break;
        }
    }

    public void setOnPauseListener(Activity activity) {
        callback = (OnPauseListener) activity;
    }

    public interface OnPauseListener {
        void continueGame();
    }
}

package ru.pushapp.misteryofegypt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.navigation.Navigation;

public class PauseFragment extends Fragment implements View.OnClickListener {

    ImageButton continue_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause, container, false);

//        continue_btn = view.findViewById(R.id.pause_continue);
//        continue_btn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
//        Navigation.findNavController(view).navigate(R.id.action_pauseFragment2_to_leaderBoardsFragment);
    }
}

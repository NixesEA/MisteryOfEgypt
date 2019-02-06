package ru.pushapp.misteryofegypt.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.navigation.Navigation;
import ru.pushapp.misteryofegypt.R;
import ru.pushapp.misteryofegypt.game.GameActivity;

public class StartScreenFragment extends Fragment implements View.OnClickListener {

    ImageButton leaderBoards;
    ImageButton play;
    ImageButton shop;

    TextView coinsTV;
    TextView externalLifeTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        coinsTV = view.findViewById(R.id.count_coins);
        externalLifeTV = view.findViewById(R.id.count_life);

        play = view.findViewById(R.id.btn_play);
        play.setOnClickListener(this);

        leaderBoards = view.findViewById(R.id.btn_leaderboard);
        leaderBoards.setOnClickListener(this);

        shop = view.findViewById(R.id.btn_shop);
        shop.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        int userBalance = sharedPreferences.getInt("money", 0);
        int externalLife = sharedPreferences.getInt("life", 0);

        coinsTV.setText(String.valueOf(userBalance));
        externalLifeTV.setText(String.valueOf(externalLife));
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_leaderboard:
                Navigation.findNavController(view).navigate(R.id.action_startScreenFragment_to_leaderBoardsFragment);
                break;
            case R.id.btn_play:
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_shop:
                Navigation.findNavController(view).navigate(R.id.action_startScreenFragment_to_shopFragment);
                break;
        }
    }
}

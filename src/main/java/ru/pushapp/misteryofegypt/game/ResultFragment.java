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
import android.widget.TextView;

import ru.pushapp.misteryofegypt.R;

public class ResultFragment extends Fragment implements View.OnClickListener {

    ImageButton playAgain;
    ImageButton exit;
    TextView resultTv;

    int countMoney = 0;

    OnResultListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        playAgain = view.findViewById(R.id.btn_play_again);
        playAgain.setOnClickListener(this);

        exit = view.findViewById(R.id.btn_exit_menu_result);
        exit.setOnClickListener(this);

        countMoney = getArguments().getInt("countMoney");
        String result = "+ " + countMoney;
        resultTv = view.findViewById(R.id.result_count_coins);
        resultTv.setText(result);

        Log.i("TESTTAG", "onCreateView");


        return view;
    }

    private void saveResult() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        int money = sharedPreferences.getInt("money", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", money + countMoney);
        editor.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play_again:
                callback.playAgain();
                break;
            case R.id.btn_exit_menu_result:
                saveResult();
                getActivity().finish();
//                getActivity().onBackPressed();
                break;
        }
    }

    public void setOnResultListener(Activity activity) {
        callback = (OnResultListener) activity;
    }

    public interface OnResultListener {
        void playAgain();
    }
}

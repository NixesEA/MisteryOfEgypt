package ru.pushapp.misteryofegypt;

import android.app.Activity;
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

public class ResultFragment extends Fragment implements View.OnClickListener {

    ImageButton playAgain;
    ImageButton exit;
    TextView resultTv;

    OnResultListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        playAgain = view.findViewById(R.id.btn_play_again);
        playAgain.setOnClickListener(this);

        exit = view.findViewById(R.id.btn_exit_menu);
        exit.setOnClickListener(this);

        String result = "+ " +  getArguments().getInt("countMoney");
        resultTv = view.findViewById(R.id.result_count_coins);
        resultTv.setText(result);
        //todo store result

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play_again:
                callback.playAgain();
                break;
            case R.id.btn_exit_menu:
                getActivity().finish();
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

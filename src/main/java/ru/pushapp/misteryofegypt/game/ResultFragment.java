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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import ru.pushapp.misteryofegypt.R;
import ru.pushapp.misteryofegypt.start.LeaderUnit;

public class ResultFragment extends Fragment implements View.OnClickListener {

    ImageButton playAgain;
    ImageButton exit;
    TextView resultTv;
    EditText editNameLeader;

    int countMoney = 0;
    int countLastExternalLife = 0;

    OnResultListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        String userName = sharedPreferences.getString("userName", "LiveUser");
        editNameLeader = view.findViewById(R.id.edit_name_leader);
        editNameLeader.setText(userName);

        playAgain = view.findViewById(R.id.btn_play_again);
        playAgain.setOnClickListener(this);

        exit = view.findViewById(R.id.btn_exit_menu_result);
        exit.setOnClickListener(this);

        countMoney = getArguments().getInt("countMoney");
        countLastExternalLife = getArguments().getInt("externalLife");
        String result = "+ " + countMoney;
        resultTv = view.findViewById(R.id.result_count_coins);
        resultTv.setText(result);

        return view;
    }

    private void saveResult() {
        //save result
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        int money = sharedPreferences.getInt("money", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("money", money + countMoney);
        editor.putInt("life", countLastExternalLife);
        editor.commit();

        //save in leader board
        ArrayList<LeaderUnit> leaderList = getArrayList();
        if (leaderList == null){
            leaderList = new ArrayList<>();
            int listSize = leaderList.size();
            while (listSize < 5){
                leaderList.add(new LeaderUnit("User " + (5 - listSize),100 * (listSize + 1)));
                listSize++;
            }
        }


        String userName = editNameLeader.getText().toString();
        if (userName.equals("")) {
            userName = "LiveUser";
        }
        editor.putString("userName", userName);
        editor.commit();

        leaderList.add(new LeaderUnit(userName, countMoney));
        Collections.sort(leaderList);

        while (leaderList.size() > 5){
            leaderList.remove(5);
        }

        saveArrayList(leaderList);
    }

    /**
     *     Save and get ArrayList in SharedPreference
     */
    public void saveArrayList(ArrayList<LeaderUnit> list){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("leaderBoard", json);
        editor.commit();
    }

    public ArrayList<LeaderUnit> getArrayList(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("local", Context.MODE_MULTI_PROCESS);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("leaderBoard", null);
        Type type = new TypeToken<ArrayList<LeaderUnit>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void onClick(View view) {
        saveResult();
        switch (view.getId()) {
            case R.id.btn_play_again:
                callback.playAgain();
                break;
            case R.id.btn_exit_menu_result:
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

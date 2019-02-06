package ru.pushapp.misteryofegypt.start;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ru.pushapp.misteryofegypt.R;
import ru.pushapp.misteryofegypt.game.rvAdapter;

public class LeaderBoardsFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    ImageButton close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        recyclerView = view.findViewById(R.id.leaderboards_rv);
        close = view.findViewById(R.id.close_btn);
        close.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<LeaderUnit> leaderList = getArrayList();
        int listSize = 5;
        try{
            listSize = 5 - leaderList.size();
        }catch (NullPointerException ignored){
            leaderList = new ArrayList<>();
        }

        while (listSize > 0){
            leaderList.add(new LeaderUnit("User " + (6 - listSize),100 * listSize));
            listSize--;
        }
        saveArrayList(leaderList);

        rvAdapter adapter = new rvAdapter(getContext(), leaderList);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
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
}

package ru.pushapp.misteryofegypt;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

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

        ArrayList<LeaderModel> urlList = new ArrayList<>();
        urlList.clear();
        urlList.add(new LeaderModel(1,"name1",100));
        urlList.add(new LeaderModel(2,"name2",200));
        urlList.add(new LeaderModel(3,"name3",300));
        urlList.add(new LeaderModel(4,"name4",400));
        urlList.add(new LeaderModel(5,"name5",500));

        rvAdapter adapter = new rvAdapter(getContext(), urlList);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
    }
}

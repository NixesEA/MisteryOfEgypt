package ru.pushapp.misteryofegypt.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.pushapp.misteryofegypt.start.LeaderUnit;
import ru.pushapp.misteryofegypt.R;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.rvAdapterHolder> {


    class rvAdapterHolder extends RecyclerView.ViewHolder {

        public TextView number;
        public TextView name;
        public TextView score;

        rvAdapterHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.leader_number);
            name = itemView.findViewById(R.id.leader_name);
            score = itemView.findViewById(R.id.leaderboard_coins_text);

        }
    }


    private LayoutInflater inflater;
    private static ArrayList<LeaderUnit> list_items;

    public rvAdapter(Context context, ArrayList<LeaderUnit> items) {
        this.list_items = items;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public rvAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_leaderboard, parent, false);
        return new rvAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rvAdapterHolder holder, int position) {
        String number = position + 1 + ".";
        String name = list_items.get(position).getName();
        String score = list_items.get(position).getScore() + "";

        holder.number.setText(number);
        holder.name.setText(name);
        holder.score.setText(score);
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

}